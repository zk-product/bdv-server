package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.common.ApplicationContextProvider;
import cn.piesat.biserver.common.configuration.AssemblyIndexConfig;
import cn.piesat.biserver.common.configuration.DataConfig;
import cn.piesat.biserver.dao.AssemblyDataMapper;
import cn.piesat.biserver.dao.AssemblyMapper;
import cn.piesat.biserver.entity.AssemblyDataEntity;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;
import cn.piesat.biserver.enums.DataTypeEnum;
import cn.piesat.biserver.service.IAssembly;
import cn.piesat.biserver.service.IAssemblyCurrency;
import cn.piesat.biserver.util.AnalysisAssemblyDataUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2019/8/29 15:00
 */
@Service
public class AssemblyImpl implements IAssembly {
    @Autowired
    private AssemblyMapper mapper;
    @Autowired
    private AssemblyDataMapper dataMapper;
    @Autowired
    private DataConfig dataConfig;
    @Autowired
    private AssemblyIndexConfig indexConfig;
    @Autowired
    private ApplicationContextProvider provider;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AssemblyEntity addAssembly(AssemblyEntity assemblyEntity) {
        //是否根据收藏组件添加新组件标识
        boolean flag = false;
        if (assemblyEntity.getCollection() == 1) {
            flag = true;
        }
        /**
         * 添加组件时重置为0，防止根据收藏组件添加时，把收藏状态一同添加到组件
         */
        assemblyEntity.setCollection(0);
        int insert = mapper.insert(assemblyEntity);
        if (insert > 0) {
            AssemblyDataEntity dataEntity = null;
            if (flag) {
                dataEntity = addAssemblyDataByCollection(assemblyEntity.getId(), assemblyEntity.getAssemblyDataObj());
            } else {
                dataEntity = addAssemblyData(assemblyEntity.getId(), assemblyEntity.getAssemblyType(), assemblyEntity.getDataType());
            }

            if (dataEntity != null) {
                assemblyEntity.setAssemblyDataObj(dataEntity);
                return assemblyEntity;
            }
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AssemblyEntity addCurrencyAssembly(AssemblyEntity assemblyEntity) {
        //是否根据收藏组件添加新组件标识
        boolean flag = false;
        if (assemblyEntity.getCollection() == 1) {
            flag = true;
            //添加组件时重置为0，防止根据收藏组件添加时，把收藏状态一同添加到组件
            assemblyEntity.setCollection(0);
        }
        int insert = mapper.insert(assemblyEntity);
        if (insert > 0) {
            AssemblyCurrencyEntity currencyEntity = null;
            String assemblyId = assemblyEntity.getId();
            String assemblyType = assemblyEntity.getAssemblyType();
            Integer dataType = assemblyEntity.getDataType();
            if (flag) {
                //TODO 根据收藏组件添加新组件

            } else {
                // 使用aop代理，防止事务失效
                currencyEntity = ((IAssemblyCurrency) AopContext.currentProxy())
                        .addCurrencyAssemblyData(assemblyId, assemblyType, dataType);
            }
            if (currencyEntity == null) {
                currencyEntity = ((IAssemblyCurrency) AopContext.currentProxy())
                        .addCurrencyAssemblyDefaultData(assemblyEntity.getId(), assemblyEntity.getAssemblyCurrencyData());
            }
            assemblyEntity.setAssemblyCurrencyData(currencyEntity);
            return assemblyEntity;
        }
        return null;
    }

    @Override
    public AssemblyDataEntity addAssemblyData(String assemblyId, String assemblyType, int dataType) {
        AssemblyDataEntity dataEntity = new AssemblyDataEntity();
        String key = assemblyType;
        String data = dataConfig.getMaps().get(assemblyType);
        dataEntity.setAssemblyId(assemblyId);
        dataEntity.setDataType(dataType);
        dataEntity.setAssemblyData(data);
        dataEntity.setDataLegend(dataConfig.getMaps().get(key + "-legend"));
        dataEntity.setxMapping(dataConfig.getMaps().get(key + "-x"));
        dataEntity.setyMapping(dataConfig.getMaps().get(key + "-y"));
        int insert = dataMapper.insert(dataEntity);
        if (insert > 0) {
            return dataEntity;
        }
        return null;
    }

    /**
     * 根据收藏组件创建新组件
     *
     * @param assemblyId
     * @param dataEntity
     * @return
     */
    public AssemblyDataEntity addAssemblyDataByCollection(String assemblyId, AssemblyDataEntity dataEntity) {
        dataEntity.setAssemblyId(assemblyId);
        int insert = dataMapper.insert(dataEntity);
        if (insert > 0) {
            return dataEntity;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAssemblyData(AssemblyDataEntity entity) {
        //根据assemblyId和dataType，查看数据是否存在
        AssemblyDataEntity data = dataMapper.queryAssemblyData(entity);
        if (data != null) {
            entity.setDataId(data.getDataId());
            //根据dataId修改组件数据
            int update = dataMapper.updateByPrimaryKey(entity);
            if (update > 0) {
                return true;
            }
            return false;
        }
        //添加组件动态数据
        int insert = dataMapper.insert(entity);
        if (insert > 0) {
            AssemblyEntity assemblyEntity = new AssemblyEntity();
            assemblyEntity.setId(entity.getAssemblyId());
            assemblyEntity.setDataType(entity.getDataType());
            //修改组件绑定数据类型
            int i = mapper.updateByPrimaryKeySelective(assemblyEntity);
            if (i > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<AssemblyEntity> queryAssembly(AssemblyEntity entity) {
        List<AssemblyEntity> assemblyEntities = mapper.queryAssembly(entity);
        for (AssemblyEntity assemblyEntity : assemblyEntities) {
            AssemblyDataEntity dataEntity = assemblyEntity.getAssemblyDataObj();
            dealAssemblyAPIData(dataEntity);
        }
        return assemblyEntities;
    }

    @Override
    public List<AssemblyEntity> queryAssemblys(String templateId) {
        List<AssemblyEntity> assemblyEntities = mapper.queryAssemblyByScreenId(templateId);
        for (AssemblyEntity assemblyEntity : assemblyEntities) {
            String typePre = assemblyEntity.getAssemblyType().substring(0, 2);
            // 查询组件类型对应的查询类
            String className = indexConfig.getMaps().get(typePre).get("className");
            /**
             * 获取组件ID和数据类型，用于反射调用传值
             */
            String assemblyId = assemblyEntity.getId();
            Integer dataType = assemblyEntity.getDataType();

            try {
                Class<?> clazz = Class.forName(className);
                Object providerBean = provider.getBean(clazz);
                Method method = clazz.getMethod("queryAssemblyData", String.class,Integer.class);
                AssemblyCurrencyEntity result = (AssemblyCurrencyEntity) method.invoke(providerBean, assemblyId, dataType);
                // 处理API数据
                Method dealAPIDataMethod = clazz.getMethod("dealAPIData", AssemblyCurrencyEntity.class);
                dealAPIDataMethod.invoke(providerBean, result);
                // 存入组件父类中
                assemblyEntity.setAssemblyCurrencyData(result);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return assemblyEntities;
    }

    @Override
    public AssemblyDataEntity queryAssemblyData(AssemblyDataEntity entity) {
        AssemblyDataEntity dataEntity = dataMapper.queryAssemblyData(entity);
        return dealAssemblyAPIData(dataEntity);
    }

    /**
     * 处理API数据，根据x，y映射确定字段选中状态
     *
     * @param dataEntity
     * @return
     */
    @Deprecated
    public AssemblyDataEntity dealAssemblyAPIData(AssemblyDataEntity dataEntity) {
        // 判断是否为API数据，因为此方法只适合API处理
        if (dataEntity != null && dataEntity.getDataType() == DataTypeEnum.API.getIndex().intValue()) {
            String xMapping = dataEntity.getxMapping();
            String yMapping = dataEntity.getyMapping();
            /**
             *  定义X,Y映射字段集合
             */
            List<String> xMappingList = new ArrayList<>();
            List<String> yMappingList = new ArrayList<>();
            if (xMapping != null && !"".equals(xMapping)) {
                // 处理x映射集合
                xMappingList = AnalysisAssemblyDataUtil.dealArray(xMapping);
            }
            if (yMapping != null && !"".equals(yMapping)) {
                // 处理y映射集合
                yMappingList = AnalysisAssemblyDataUtil.dealArray(yMapping);
            }

            List<String> mappingList = new ArrayList<String>();
            /**
             * 无重复合并集合
             */
            xMappingList.removeAll(yMappingList);
            mappingList.addAll(xMappingList);
            mappingList.addAll(yMappingList);

            String assemblyData = dataEntity.getAssemblyData();
            List<String> outParams = AnalysisAssemblyDataUtil.extractJSONField(assemblyData, "outParams", new ArrayList<String>());
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (String outParam : outParams) {
                Map<String, Object> map = new HashMap<>();
                map.put("field", outParam);
                for (String param : mappingList) {
                    if (outParam.equals(param)) {
                        map.put("selected", true);
                        break;
                    }
                    map.put("selected", false);
                }
                mapList.add(map);
            }
            String dealAssemblyData = AnalysisAssemblyDataUtil.replaceJSONField(assemblyData, "outParams", JSON.toJSONString(mapList));
            dataEntity.setAssemblyData(dealAssemblyData);
        }
        return dataEntity;
    }

    @Override
    public boolean updateByPrimaryKey(AssemblyDataEntity entity) {
        int i = dataMapper.updateByPrimaryKey(entity);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAssemblyByIdSelective(AssemblyEntity entity) {
        int i = mapper.updateByPrimaryKeySelective(entity);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAssemblyById(String id) {
        int i = mapper.deleteAssemblyById(id);
        if (i > 0) {
            return true;
        }
        return false;
    }
}

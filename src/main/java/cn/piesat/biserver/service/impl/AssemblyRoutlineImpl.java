package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.common.configuration.DataConfig;
import cn.piesat.biserver.dao.AssemblyRoutlineMapper;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyRoutlineEntity;
import cn.piesat.biserver.enums.DataTypeEnum;
import cn.piesat.biserver.service.IAssemblyCurrency;
import cn.piesat.biserver.util.AnalysisAssemblyDataUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2020/1/2 14:22
 */
@Service
public class AssemblyRoutlineImpl extends AssemblyImpl implements IAssemblyCurrency<AssemblyRoutlineEntity> {
    @Autowired
    private AssemblyRoutlineMapper mapper;
    @Autowired
    private DataConfig dataConfig;

    @Override
    public AssemblyCurrencyEntity queryAssemblyData(String assemblyId, Integer dataType) {
        return mapper.queryRoutineAssemblyData(assemblyId, dataType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AssemblyCurrencyEntity addCurrencyAssemblyData(String assemblyId, String assemblyType, int dataType) {
        AssemblyRoutlineEntity dataEntity = new AssemblyRoutlineEntity();
        String key = assemblyType;
        String data = dataConfig.getMaps().get(key);
        dataEntity.setAssemblyId(assemblyId);
        dataEntity.setDataType(dataType);
        dataEntity.setAssemblyData(data);
        dataEntity.setDataLegend(dataConfig.getMaps().get(key + "-legend"));
        dataEntity.setxMapping(dataConfig.getMaps().get(key + "-x"));
        dataEntity.setyMapping(dataConfig.getMaps().get(key + "-y"));
        int insert = mapper.insert(dataEntity);
        if (insert > 0) {
            return dataEntity;
        }
        return null;
    }

    @Override
    public AssemblyEntity queryAssemblyById(String id) {
        return mapper.queryAssemblyById(id);
    }

    @Override
    public boolean updataAssemblyDataById(AssemblyRoutlineEntity currencyEntity) {
        boolean flag = false;
        int num = mapper.updataAssemblyDataByDataId(currencyEntity);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void dealAPIData(AssemblyRoutlineEntity dataEntity) {
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
    }
}

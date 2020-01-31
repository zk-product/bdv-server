package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.common.configuration.DataConfig;
import cn.piesat.biserver.dao.AssemblyTextMapper;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyTextEntity;
import cn.piesat.biserver.service.IAssemblyCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文本组件服务层
 * @author zk
 * @date 2020/1/8 14:44
 */
@Service
public class AssemblyTextImpl extends AssemblyImpl implements IAssemblyCurrency<AssemblyTextEntity> {

    @Autowired
    private AssemblyTextMapper mapper;
    @Autowired
    private DataConfig dataConfig;

    @Override
    public AssemblyCurrencyEntity queryAssemblyData(String assemblyId, Integer dataType) {
        return mapper.queryTextAssemblyData(assemblyId, dataType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AssemblyCurrencyEntity addCurrencyAssemblyData(String assemblyId, String assemblyType, int dataType) {
        AssemblyTextEntity dataEntity = new AssemblyTextEntity();
        String key = assemblyType;
        String data = dataConfig.getMaps().get(key);
        dataEntity.setAssemblyId(assemblyId);
        dataEntity.setDataType(dataType);
        dataEntity.setAssemblyData(data);
        dataEntity.setFontSize(Integer.valueOf(dataConfig.getMaps().get(key + "-size")));
        dataEntity.setFontColor(dataConfig.getMaps().get(key + "-color"));
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
    public boolean updataAssemblyDataById(AssemblyTextEntity currencyEntity) {
        boolean flag = false;
        int num = mapper.updataAssemblyDataByDataId(currencyEntity);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void dealAPIData(AssemblyTextEntity currencyEntity) {

    }
}

package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.common.configuration.DataConfig;
import cn.piesat.biserver.dao.AssemblyMapMapper;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyMapEntity;
import cn.piesat.biserver.service.IAssemblyCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zk
 * @date 2020/1/9 14:27
 */
@Service
public class AssemblyMapImpl extends AssemblyImpl implements IAssemblyCurrency<AssemblyMapEntity> {
    @Autowired
    private AssemblyMapMapper mapper;
    @Autowired
    private DataConfig dataConfig;

    @Override
    public AssemblyCurrencyEntity queryAssemblyData(String assemblyId, Integer dataType) {
        return mapper.queryMapAssemblyData(assemblyId, dataType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AssemblyCurrencyEntity addCurrencyAssemblyData(String assemblyId, String assemblyType, int dataType) {
        AssemblyMapEntity dataEntity = new AssemblyMapEntity();
        String key = assemblyType;
        String data = dataConfig.getMaps().get(key);
        dataEntity.setAssemblyId(assemblyId);
        dataEntity.setDataType(dataType);
        dataEntity.setAssemblyData(data);
        dataEntity.setDataLegend(dataConfig.getMaps().get(key + "-legend"));
        dataEntity.setxMapping(dataConfig.getMaps().get(key + "-x"));
        dataEntity.setyMapping(dataConfig.getMaps().get(key + "-y"));
        dataEntity.setvMapping(dataConfig.getMaps().get(key + "-v"));
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
    public boolean updataAssemblyDataById(AssemblyMapEntity currencyEntity) {
        boolean flag = false;
        int num = mapper.updataAssemblyDataByDataId(currencyEntity);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void dealAPIData(AssemblyMapEntity currencyEntity) {

    }
}

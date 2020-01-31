package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.dao.AssemblyPicMapper;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyPicEntity;
import cn.piesat.biserver.service.IAssemblyCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zk
 * @date 2020/1/10 14:14
 */
@Service
public class AssemblyPicImpl extends AssemblyImpl implements IAssemblyCurrency<AssemblyPicEntity> {
    @Autowired
    private AssemblyPicMapper mapper;

    @Override
    public AssemblyCurrencyEntity queryAssemblyData(String assemblyId, Integer dataType) {
        return mapper.queryPicAssemblyData(assemblyId, dataType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AssemblyCurrencyEntity addCurrencyAssemblyDefaultData(String assemblyId, AssemblyPicEntity dataEntity) {
        dataEntity.setAssemblyId(assemblyId);
        int insert = mapper.insert(dataEntity);
        if (insert > 0) {
            return dataEntity;
        }
        return null;
    }

    @Override
    public AssemblyCurrencyEntity addCurrencyAssemblyData(String assemblyId, String assemblyType, int dataType) {
        return null;
    }

    @Override
    public AssemblyEntity queryAssemblyById(String id) {
        return mapper.queryAssemblyById(id);
    }

    @Override
    public boolean updataAssemblyDataById(AssemblyPicEntity currencyEntity) {
        boolean flag = false;
        int num = mapper.updataAssemblyDataByDataId(currencyEntity);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void dealAPIData(AssemblyPicEntity currencyEntity) {

    }
}

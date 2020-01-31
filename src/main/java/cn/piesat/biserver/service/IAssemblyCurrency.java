package cn.piesat.biserver.service;

import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;

/**
 * @author zk
 * @date 2020/1/8 10:19
 */
public interface IAssemblyCurrency<T extends AssemblyCurrencyEntity> extends IAssembly {

    /**
     * 根据组件ID和数据类型查询组件数据
     * @param assemblyId
     * @param dataType
     * @return
     */
    AssemblyCurrencyEntity queryAssemblyData(String assemblyId, Integer dataType);

    /**
     * 新增组件数据
     * @param assemblyId
     * @param assemblyType
     * @param dataType
     * @return
     */
    AssemblyCurrencyEntity addCurrencyAssemblyData(String assemblyId, String assemblyType, int dataType);

    /**
     * 新增组件数据
     * 前端传递默认值时实现
     * @param entity
     * @return
     */
    default AssemblyCurrencyEntity addCurrencyAssemblyDefaultData(String assemblyId, T entity) {
        throw new RuntimeException("请为addCurrencyAssemblyDefaultData接口提供实现方法");
    }

    /**
     * 根据组件ID查询组件信息
     * @param id
     * @return
     */
    AssemblyEntity queryAssemblyById(String id);

    /**
     * 修改组件数据
     * @param currencyEntity
     * @return
     */
    boolean updataAssemblyDataById(T currencyEntity);

    /**
     * 处理API数据
     * @param currencyEntity
     */
    void dealAPIData(T currencyEntity);
}

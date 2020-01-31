package cn.piesat.biserver.service;

import cn.piesat.biserver.entity.AssemblyDataEntity;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;

import java.util.List;

/**
 * @author zk
 * @date 2019/8/29 14:31
 */
public interface IAssembly {
    /**
     * 添加组件
     * @param assemblyEntity
     * @return 组件id
     * @see  #addCurrencyAssembly(AssemblyEntity)
     */
    @Deprecated
    AssemblyEntity addAssembly(AssemblyEntity assemblyEntity);

    /**
     * 添加通用组件
     * @param assemblyEntity
     * @return
     */
    AssemblyEntity addCurrencyAssembly(AssemblyEntity assemblyEntity);

    /**
     * 添加组件数据
     * @param assemblyId 组件id
     * @param assemblyType
     * @param dataType 组件数据类型
     * @return 添加组件数据数量
     * @see IAssemblyCurrency#addCurrencyAssemblyData(String, String, int)
     */
    @Deprecated
    AssemblyDataEntity addAssemblyData(String assemblyId, String assemblyType, int dataType);

    /**
     * 添加组件动态数据
     * @param entity
     * @return
     */
    boolean addAssemblyData(AssemblyDataEntity entity);

    /**
     * 根据模板id或组件id查询组件
     * @param entity
     * @return
     * @see #queryAssemblys(String)
     * @see IAssemblyCurrency#queryAssemblyById(String)
     */
    @Deprecated
    List<AssemblyEntity> queryAssembly(AssemblyEntity entity);

    /**
     * 根据模板id查询组件（优化设计）
     * @param templateId
     * @return
     */
    List<AssemblyEntity> queryAssemblys(String templateId);

    /**
     * 根据数据ID/组件id/数据类型查询组件数据
     * @param entity
     * @return
     * @see IAssemblyCurrency#queryAssemblyData(String, Integer)
     */
    @Deprecated
    AssemblyDataEntity queryAssemblyData(AssemblyDataEntity entity);


    /**
     * 根据主键修改数据
     * @param entity
     * @return
     * @see IAssemblyCurrency#updataAssemblyDataById(AssemblyCurrencyEntity)
     */
    @Deprecated
    boolean updateByPrimaryKey(AssemblyDataEntity entity);

    /**
     * 根据主键修改部分组件属性
     * @param entity
     * @return
     */
    boolean updateAssemblyByIdSelective(AssemblyEntity entity);

    /**
     * 根据id删除组件及数据
     * @param id
     * @return
     */
    boolean deleteAssemblyById(String id);
}

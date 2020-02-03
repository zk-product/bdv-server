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
     * 添加组件通用部分
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
     * 根据模板id查询组件（优化设计）
     * @param templateId
     * @return
     */
    List<AssemblyEntity> queryAssemblys(String templateId);


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

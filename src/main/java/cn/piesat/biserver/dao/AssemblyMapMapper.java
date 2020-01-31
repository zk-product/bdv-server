package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyMapEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 地图组件仓储
 * @author zk
 * @date 2020/1/9 14:27
 */
@Repository
public interface AssemblyMapMapper {
    /**
     * 新增常规组件数据
     * @param entity
     * @return
     */
    int insert(AssemblyMapEntity entity);
    /**
     * 根据组件ID和数据类型查询组件数据
     * @param assemblyId
     * @param dataType
     * @return
     */
    AssemblyCurrencyEntity queryMapAssemblyData(@Param("assemblyId") String assemblyId, @Param("dataType") Integer dataType);

    /**
     * 根据组件ID查询组件信息
     * @param id
     * @return
     */
    AssemblyEntity queryAssemblyById(String id);

    /**
     * 根据ID修改组件数据
     * @param mapEntity
     * @return
     */
    int updataAssemblyDataByDataId(AssemblyMapEntity mapEntity);
}
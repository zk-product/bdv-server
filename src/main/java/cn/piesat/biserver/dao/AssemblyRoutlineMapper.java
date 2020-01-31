package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyRoutlineEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 常规组件仓储
 * @author zk
 * @date 2019/12/26 17:41
 */
@Repository
public interface AssemblyRoutlineMapper {
    /**
     * 新增常规组件数据
     * @param entity
     * @return
     */
    int insert(AssemblyRoutlineEntity entity);
    /**
     * 根据组件ID和数据类型查询组件数据
     * @param assemblyId
     * @param dataType
     * @return
     */
    AssemblyCurrencyEntity queryRoutineAssemblyData(@Param("assemblyId") String assemblyId, @Param("dataType") Integer dataType);

    /**
     * 根据组件ID查询组件信息
     * @param id
     * @return
     */
    AssemblyEntity queryAssemblyById(String id);

    /**
     * 根据ID修改组件数据
     * @param routlineEntity
     * @return
     */
    int updataAssemblyDataByDataId(AssemblyRoutlineEntity routlineEntity);
}

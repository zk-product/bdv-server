package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.AssemblyDataEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组件数据仓储
 * @author zk
 * @date 2020/1/28 21:34
 */
@Repository
public interface AssemblyDataMapper {

    /**
     * 添加组件数据
     * @param record
     * @return 添加数据数量
     */
    int insert(AssemblyDataEntity record);

    /**
     * 批量添加组件数据
     * @param record
     * @return 添加数据数量
     */
    int insertBatch(List<AssemblyDataEntity> record);


    /**
     * 根据数据ID/组件id/数据类型查询组件数据
     * @param entity
     * @return
     */
    AssemblyDataEntity queryAssemblyData(AssemblyDataEntity entity);

    /**
     *  根据组件多id查询组件数据
     * @param ids
     * @return
     */
    List<AssemblyDataEntity> queryAssemblyDataByIds(List<String> ids);

    /**
     * 根据主键修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(AssemblyDataEntity record);
}
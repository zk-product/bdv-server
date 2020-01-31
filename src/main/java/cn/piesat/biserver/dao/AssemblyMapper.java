package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.AssemblyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组件通用仓储
 * @author zk
 * @date 2019/8/29 14:03
 */
@Repository
public interface AssemblyMapper {

    /**
     * 添加组件
     * @param record
     * @return 添加数量
     */
    int insert(AssemblyEntity record);

    /**
     * 批量添加组件
     * @param list
     * @return 添加数量
     */
    int insertBatch(List<AssemblyEntity> list);

    /**
     * 根据模板id或组件id查询组件
     * @param entity
     * @return
     */
    List<AssemblyEntity> queryAssembly(AssemblyEntity entity);

    /**
     * 根据大屏ID查询组件
     * @param screenId 大屏ID
     * @return
     */
    List<AssemblyEntity> queryAssemblyByScreenId(String screenId);

    /**
     * 根据主键修改部分组件属性
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AssemblyEntity record);

    /**
     * 根据id删除组件及数据
     * @param id
     * @return
     */
    int deleteAssemblyById(String id);
}
package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.AssemblyIndexEntity;
import org.springframework.stereotype.Repository;

/**
 * 组件索引管理仓储
 * @author zk
 * @date 2020/1/28 21:34
 */
@Repository
public interface AssemblyIndexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssemblyIndexEntity record);

    int insertSelective(AssemblyIndexEntity record);

    /**
     * 根据组件类型查询组件对应的方法索引
     * @param assemblyType
     * @return
     */
    //TODO 此处后续变为redis支撑
    AssemblyIndexEntity queryAssemblyIndexByAssemblyType(String assemblyType);

    int updateByPrimaryKeySelective(AssemblyIndexEntity record);

    int updateByPrimaryKey(AssemblyIndexEntity record);
}
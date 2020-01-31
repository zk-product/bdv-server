package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.APIManagerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * API管理仓储
 * @author zk
 * @date 2020/1/28 21:34
 */
@Repository
public interface APIManagerMapper {
    /**
     * 根据id删除API
     * @param id
     * @return
     */
    int deleteAPIById(String id);

    /**
     * 新增API
     * @param record
     * @return
     */
    int insert(APIManagerEntity record);

    /**
     * 查询API列表
     * @param record
     * @return
     */
    List<APIManagerEntity> queryAPIByPaging(APIManagerEntity record);

    /**
     * 查询所有API
     * @return
     */
    List<APIManagerEntity> queryAllAPI();

    /**
     * 根据ID查询API详情
     * @param id
     * @return
     */
    APIManagerEntity queryAPIById(String id);

    int insertSelective(APIManagerEntity record);

    APIManagerEntity selectByPrimaryKey(String id);

    /**
     * 根据id修改API信息
     * @param record
     * @return
     */
    int updateAPIById(APIManagerEntity record);

    int updateByPrimaryKey(APIManagerEntity record);
}
package cn.piesat.biserver.service;

import cn.piesat.biserver.entity.APIManagerEntity;

import java.util.List;

/**
 * @author zk
 * @date 2019/9/27 14:48
 */
public interface IAPIManager {
    /**
     * 新增API
     * @param entity
     * @return
     */
    boolean addAPI(APIManagerEntity entity);

    /**
     * 分页查询API列表
     * @param entity
     * @return
     */
    List<APIManagerEntity> queryAPIByPaging(APIManagerEntity entity);

    /**
     * 查询所有API列表
     * @return
     */
    List<APIManagerEntity> queryAllAPI();

    /**
     * 根据id查询API详情
     * @param id
     * @return
     */
    APIManagerEntity queryAPIById(String id);

    /**
     * 根据id修改API信息
     * @param entity
     * @return
     */
    boolean updateAPIById(APIManagerEntity entity);

    /**
     * 根据id删除API
     * @param id
     * @return
     */
    boolean deleteAPIById(String id);

}

package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.APIParamsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * API参数仓储
 * @author zk
 * @date 2020/1/28 21:34
 */
@Repository
public interface APIParamsMapper {
    /**
     * 根据APIId删除参数
     * @param APIId
     * @return
     */
    int deleteParamById(String APIId);

    /**
     * 添加API参数
     * @param record
     * @return
     */
    int insertBatch(List<APIParamsEntity> record);

}
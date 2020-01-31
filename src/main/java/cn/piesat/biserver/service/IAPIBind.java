package cn.piesat.biserver.service;

import cn.piesat.biserver.entity.APIManagerEntity;

import java.util.List;

/**
 *
 * @author zk
 * @date 2019/10/16 11:07
 */
public interface IAPIBind {

    /**
     *  获取输出参数列表
     * @param entity
     * @return
     */
    List<String> getOutParam(APIManagerEntity entity);

    /**
     * 查询绑定映射关系可选字段
     * @param dataId
     * @return
     */
    List<String> optionalField(String dataId, String mappingField);
}

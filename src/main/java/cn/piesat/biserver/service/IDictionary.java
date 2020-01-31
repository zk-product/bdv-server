package cn.piesat.biserver.service;

import cn.piesat.biserver.entity.dictionary.DictionaryDataEntity;

import java.util.List;

/**
 * 字典接口
 *
 * @author zk
 * @date 2020/1/30 23:52
 */
public interface IDictionary {

    /**
     * 查询数据类型字典
     * @return
     */
    List<DictionaryDataEntity> queryDataTypeDic();
}

package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.dictionary.DictionaryDataEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典数据管理仓储
 * @author zk
 * @date 2020/1/28 21:34
 */
@Repository
public interface DictionaryDataMapper {

    /**
     * 查询字典数据
     * @param record
     * @return
     */
    List<DictionaryDataEntity> queryDic(DictionaryDataEntity record);
}
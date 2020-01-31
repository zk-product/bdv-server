package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.constant.StatisticsConstant;
import cn.piesat.biserver.dao.DictionaryDataMapper;
import cn.piesat.biserver.entity.dictionary.DictionaryDataEntity;
import cn.piesat.biserver.service.IDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典实现类
 *
 * @author zk
 * @date 2020/1/30 23:54
 */
@Service
public class DictionaryImpl implements IDictionary {

    @Autowired
    private DictionaryDataMapper dictionaryDataMapper;

    @Override
    public List<DictionaryDataEntity> queryDataTypeDic() {
        DictionaryDataEntity entity = new DictionaryDataEntity();
        entity.setDicId(StatisticsConstant.DIC_DATE_TYPE_ID);
        return dictionaryDataMapper.queryDic(entity);
    }
}

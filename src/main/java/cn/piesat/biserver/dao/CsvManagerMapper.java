package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.CsvManagerEntity;
import org.springframework.stereotype.Repository;

/**
 * CSV管理仓储
 * @author zk
 * @date 2020/1/28 21:34
 */
@Repository
public interface CsvManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(CsvManagerEntity record);

    int insertSelective(CsvManagerEntity record);

    /**
     * 根据id查询csv文件
     * @param id csv索引id
     * @return
     */
    CsvManagerEntity queryCsvById(String id);

    int updateByPrimaryKeySelective(CsvManagerEntity record);

    int updateByPrimaryKey(CsvManagerEntity record);

}
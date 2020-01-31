package cn.piesat.biserver.service;

import cn.piesat.biserver.entity.CsvManagerEntity;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2019/11/19 15:52
 */
public interface ICsvManager {
    /**
     * 添加CSV文件索引
     * @param entity
     * @return
     */
    boolean addCsvInfo(CsvManagerEntity entity);

    /**
     * 根据id查询csv文件
     * @param id csv索引id
     * @return
     */
    CsvManagerEntity queryCsvById(String id);

    /**
     * 解析excel并返回
     * @param id
     * @return
     */
    List<Map<String, String>> analysisExcel(String id);
}

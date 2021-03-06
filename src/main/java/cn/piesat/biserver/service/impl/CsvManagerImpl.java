package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.common.PathConfig;
import cn.piesat.biserver.dao.CsvManagerMapper;
import cn.piesat.biserver.entity.CsvManagerEntity;
import cn.piesat.biserver.enums.CsvStatusEnum;
import cn.piesat.biserver.listener.NoModelDataListener;
import cn.piesat.biserver.service.ICsvManager;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2019/11/19 15:54
 */
@Service
public class CsvManagerImpl implements ICsvManager {

    @Autowired
    private PathConfig pathConfig;
    @Autowired
    private CsvManagerMapper csvManagerMapper;

    @Override
    public boolean addCsvInfo(CsvManagerEntity entity) {
        int insert = csvManagerMapper.insert(entity);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public CsvManagerEntity queryCsvById(String id) {
        return csvManagerMapper.queryCsvById(id);
    }

    @Override
    public List<Map<String, String>> analysisExcel(String id) {
        /**
         * 1.根据id查询excel路径
         * 2.解析excel并返回List<Map<String, String>>
         */
        CsvManagerEntity csvManagerEntity = queryCsvById(id);
        if (csvManagerEntity == null) {
            return null;
        }
        String prePath = "";
        // 如果发布，增加发布路径前缀
        if (csvManagerEntity.getIsRelease() == CsvStatusEnum.OK.getKey()) {
            prePath += pathConfig.getReleasePath();
        }

        //获取相对路径
        String relativePath = csvManagerEntity.getPath();
        String filPath = prePath + relativePath;

        //解析excel
        NoModelDataListener noModelDataListener = new NoModelDataListener();
        EasyExcel.read(filPath, noModelDataListener).sheet().doRead();
        return noModelDataListener.getMaps();
    }
}

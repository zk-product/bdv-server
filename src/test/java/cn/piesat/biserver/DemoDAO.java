package cn.piesat.biserver;

import cn.piesat.biserver.dao.CsvManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zk
 * @date 2019/11/20 15:30
 */
@Service
public class DemoDAO {

    @Autowired
    private CsvManagerMapper csvManagerMapper;

    public void save(List<DemoData> list) {
    }
}

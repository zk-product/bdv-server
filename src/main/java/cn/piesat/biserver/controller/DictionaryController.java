package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.entity.dictionary.DictionaryDataEntity;
import cn.piesat.biserver.service.IDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 字典管理 controller层
 *
 * @author zk
 * @date 2020/1/30 23:45
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private IDictionary iDictionary;

    @RequestMapping("/queryDataTypeDic")
    public Response queryDataTypeDic() {
        Response response = Response.getInstance();
        List<DictionaryDataEntity> list = iDictionary.queryDataTypeDic();
        if (list != null) {
            response.setOk(0, null, "查询成功！", list);
        } else {
            response.setError(10000, null, "查询失败！");
        }
        return response;
    }
}

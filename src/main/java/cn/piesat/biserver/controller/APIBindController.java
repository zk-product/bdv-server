package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.entity.APIManagerEntity;
import cn.piesat.biserver.service.IAPIBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 绑定API数据 Controller层
 * @author zk
 * @date 2019/10/15 9:25
 */
@RestController
@RequestMapping("/APIBind")
public class APIBindController {

    @Autowired
    private IAPIBind iAPIBind;

    @RequestMapping("/getOutParam")
    public Response getOutParam(@RequestBody APIManagerEntity APIManagerEntity) {
        Response response = Response.getInstance();
        List<String> list = iAPIBind.getOutParam(APIManagerEntity);
        if (list.size() > 0) {
            response.setOk(0, null, "请求成功！", list);
        } else {
            response.setError(10000, null, "请求失败！");
        }
        return response;
    }

    @RequestMapping("/optionalField")
    public Response optionalField(@RequestBody Map<String, String> params) {
        Response response = Response.getInstance();
        List<String> list = iAPIBind.optionalField(params.get("dataId"), params.get("mappingField"));
        if (list != null && list.size() > 0) {
            response.setOk(0, null, "查询成功！", list);
        } else {
            response.setError(10000, null, "查询失败！");
        }
        return response;
    }

    @RequestMapping("/testAPI1")
    public Response testAPI1() {
        Response response = Response.getInstance();
        Map<String, Object> map = new HashMap<>();
        map.put("time", new String[]{"周一", "周二", "周三", "周四", "周五"});
        map.put("yxzl", new int[]{120, 132, 101, 134, 90, 230, 210});
        map.put("lmgg", new int[]{220, 182, 191, 234, 290, 330, 310});
        map.put("spgg", new int[]{150, 232, 201, 154, 190, 330, 410});
        map.put("zjfw", new int[]{320, 332, 301, 334, 390, 330, 320});
        response.setOk(0, null, "查询成功！", map);
        return response;
    }

    @RequestMapping("/testAPI2")
    public Response testAPI2(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            String key = stringObjectEntry.getKey();
            Object value = stringObjectEntry.getValue();
            System.out.println("服务端接收到参数key：" + key + ";值：" + value);
        }
        String token = request.getHeader("token");
        System.out.println("接收到的token值：" + token);
        Response response = Response.getInstance();
        Map<String, Object> map = new HashMap<>();
        map.put("time", new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"});
        map.put("zfl", new double[]{2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3});
        map.put("jyl", new double[]{2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3});
        map.put("wd", new double[]{2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2});
        response.setOk(0, null, "查询成功！", map);
        return response;
    }
}

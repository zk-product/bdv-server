package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.common.entity.PagingEntity;
import cn.piesat.biserver.entity.APIManagerEntity;
import cn.piesat.biserver.service.IAPIManager;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * API管理 Controller层
 *
 * @author zk
 * @date 2019/9/27 14:50
 */
@RestController
@RequestMapping("/APIManager")
public class APIManagerController {
    @Autowired
    private IAPIManager iAPIManager;

    @RequestMapping("/addAPI")
    public Response addAPI(@RequestBody @Validated APIManagerEntity entity, BindingResult bindingResult) {
        Response response = Response.getInstance();
        boolean b = iAPIManager.addAPI(entity);
        if (b) {
            response.setOk(0, null, "添加成功！", b);
        } else {
            response.setError(10000, null, "添加失败！");
        }
        return response;
    }

    @RequestMapping("/queryAPIByPaging")
    public Response queryAPIByPaging(@RequestBody @Validated(PagingEntity.QueryByPaging.class) APIManagerEntity entity,
                                     BindingResult bindingResult) {
        Response response = Response.getInstance();
        List<APIManagerEntity> list = iAPIManager.queryAPIByPaging(entity);
        if (list != null) {
            PageInfo<APIManagerEntity> pageInfo = new PageInfo<>(list);
            long total = pageInfo.getTotal();
            response.setOk(0, null, "查询成功！", list, total);
        } else {
            response.setError(10000, null, "查询失败！");
        }
        return response;
    }

    @RequestMapping("/queryAllAPI")
    public Response queryAllAPI() {
        Response response = Response.getInstance();
        List<APIManagerEntity> list = iAPIManager.queryAllAPI();
        if (list != null) {
            response.setOk(0, null, "查询成功！", list);
        } else {
            response.setError(10000, null, "查询失败！");
        }
        return response;
    }

    @RequestMapping("/queryAPIById")
    public Response queryAPIById(@RequestBody Map<String, String> params) {
        Response response = Response.getInstance();
        String id = params.get("id");
        APIManagerEntity APIManagerEntities = iAPIManager.queryAPIById(id);
        if (APIManagerEntities != null) {
            response.setOk(0, null, "查询成功！", APIManagerEntities);
        } else {
            response.setError(10000, null, "查询失败！");
        }
        return response;
    }

    @RequestMapping("/updateAPIById")
    public Response updateAPIById(@RequestBody @Validated(value = APIManagerEntity.UpdateAPIById.class) APIManagerEntity entity, BindingResult bindingResult) {
        Response response = Response.getInstance();
        boolean b = iAPIManager.updateAPIById(entity);
        if (b) {
            response.setOk(0, null, "修改成功！", b);
        } else {
            response.setError(10000, null, "修改失败！");
        }
        return response;
    }

    @RequestMapping("/deleteAPIById/{id}")
    public Response deleteAPIById(@RequestBody @PathVariable String id) {
        Response response = Response.getInstance();
        boolean b = iAPIManager.deleteAPIById(id);
        if (b) {
            response.setOk(0, null, "删除成功！", b);
        } else {
            response.setError(10000, null, "删除失败！");
        }
        return response;
    }
}

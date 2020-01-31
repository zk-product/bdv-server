package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.entity.AssemblyDataEntity;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.service.IAssembly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 组件通用管理 Controller层
 * @author zk
 * @date 2019/8/29 15:40
 */

@RestController
@RequestMapping("/assembly")
public class AssemblyController {
    @Autowired
    @Qualifier("assemblyImpl")
    private IAssembly iAssembly;

    @RequestMapping("/addAssembly")
    public Response addAssembly(@RequestBody @Validated AssemblyEntity assemblyEntity, BindingResult bindingResult) {
        Response response = Response.getInstance();
        assemblyEntity.setDegree(0);
        assemblyEntity.setCollection(0);
        assemblyEntity.setAssemblyTitle("自定义标题");
        AssemblyEntity entity = iAssembly.addAssembly(assemblyEntity);
        if (entity != null) {
            response.setOk(0, null, "添加成功！", entity);
        } else {
            response.setError(1000, null, "添加失败！");
        }
        return response;
    }
    @RequestMapping("/addAssemblyData")
    public Response addAssemblyData(@RequestBody AssemblyDataEntity entity) {
        Response response = Response.getInstance();
        boolean b = iAssembly.addAssemblyData(entity);
        if (b) {
            response.setOk(0, null, "添加动态数据成功！", entity.getDataId());
        } else {
            response.setError(10000, null, "添加动态数据失败！");
        }
        return response;
    }
    @RequestMapping("/queryAssembly")
    public Response queryAssembly(@RequestBody AssemblyEntity entity) {
        Response response = Response.getInstance();
        List<AssemblyEntity> list = iAssembly.queryAssembly(entity);
        if (list != null) {
            response.setOk(0, null, "查询成功！", list);
        } else {
            response.setError(10000, null, "未查询到组件信息！");
        }
        return response;
    }

    @RequestMapping("/queryAssemblys")
    public Response queryAssemblys(@RequestBody Map<String, String> map) {
        Response response = Response.getInstance();
        List<AssemblyEntity> list = iAssembly.queryAssemblys(map.get("templateId"));
        if (list != null) {
            response.setOk(0, null, "查询成功！", list);
        } else {
            response.setError(10000, null, "未查询到组件信息！");
        }
        return response;
    }

    @RequestMapping("/queryAssemblyData")
    public Response queryAssemblyData(@RequestBody AssemblyDataEntity entity) {
        Response response = Response.getInstance();
        AssemblyDataEntity dataEntity = iAssembly.queryAssemblyData(entity);
        if (dataEntity != null) {
            response.setOk(0, null, "查询组件数据成功！", dataEntity);
        } else {
            response.setError(10000, null, "未查询到组件数据！");
        }
        return response;
    }
    @RequestMapping("/updateByPrimaryKey")
    public Response updateByPrimaryKey(@RequestBody AssemblyDataEntity entity) {
        Response response = Response.getInstance();
        boolean b = iAssembly.updateByPrimaryKey(entity);
        if (b) {
            response.setOk(0, null, "修改成功！", b);
        } else {
            response.setError(1000, null, "修改失败！");
        }
        return response;
    }

    @RequestMapping("/updateAssemblyByIdSelective")
    public Response updateAssemblyByIdSelective(@RequestBody AssemblyEntity entity) {
        Response response = Response.getInstance();
        boolean b = iAssembly.updateAssemblyByIdSelective(entity);
        if (b) {
            response.setOk(0, null, "修改成功！", b);
        } else {
            response.setError(10000, null, "修改失败！");
        }
        return response;
    }

    @RequestMapping("/deleteAssemblyById/{id}")
    public Response deleteAssemblyById(@PathVariable String id) {
        Response response = Response.getInstance();
        boolean b = iAssembly.deleteAssemblyById(id);
        if (b) {
            response.setOk(0, null, "删除组件成功！",b);
        } else {
            response.setError(10000, null, "删除组件失败！");
        }
        return response;
    }

}

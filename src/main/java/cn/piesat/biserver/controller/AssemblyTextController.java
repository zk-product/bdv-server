package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyTextEntity;
import cn.piesat.biserver.service.IAssemblyCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 文本组件管理 Controller层
 * @author zk
 * @date 2020/1/6 15:07
 */
@RestController
@RequestMapping("/assemblyText")
public class AssemblyTextController extends AbstractAssemblyController<AssemblyTextEntity> {

    @Autowired
    @Qualifier("assemblyTextImpl")
    private IAssemblyCurrency iAssemblyCurrency;

    @Override
    @RequestMapping("/addAssembly")
    public Response addAssembly(@RequestBody @Validated AssemblyEntity assemblyEntity, BindingResult bindingResult) {
        return addAssembly(iAssemblyCurrency, assemblyEntity);
    }

    @Override
    @RequestMapping("/queryAssemblyById")
    public Response queryAssemblyById(@RequestBody Map<String, String> params) {
        return queryAssemblyById(iAssemblyCurrency, params);
    }

    @Override
    @RequestMapping("/updateTextAssemblyDataById")
    public Response updateAssemblyDataById(@RequestBody AssemblyTextEntity textEntity) {
        Response response = Response.getInstance();
        boolean b = iAssemblyCurrency.updataAssemblyDataById(textEntity);
        if (b) {
            response.setOk(0, null, "修改成功！", b);
        } else {
            response.setError(1000, null, "修改失败！");
        }
        return response;
    }
}

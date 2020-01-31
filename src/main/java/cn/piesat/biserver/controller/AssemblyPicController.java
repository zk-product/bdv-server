package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyPicEntity;
import cn.piesat.biserver.service.IAssemblyCurrency;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 图片组件管理 Controller层
 * @author zk
 * @date 2020/1/6 15:07
 */
@RestController
@RequestMapping("/assemblyPic")
public class AssemblyPicController extends AbstractAssemblyController<AssemblyPicEntity> {

    @Autowired
    @Qualifier("assemblyPicImpl")
    private IAssemblyCurrency iAssemblyCurrency;

    @Override
    @RequestMapping("/addAssembly")
    public Response addAssembly(@RequestBody @Validated AssemblyEntity assemblyEntity, BindingResult bindingResult) {
        return addAssembly(iAssemblyCurrency, assemblyEntity);
    }

    @Override
    @RequestMapping("/addAssemblyByDefault")
    public Response addAssemblyByDefault(@RequestBody Map<String, JSON> params, BindingResult bindingResult) {
        AssemblyEntity assemblyEntity = JSON.toJavaObject(params.get("assemblyEntity"), AssemblyEntity.class);
        AssemblyPicEntity picEntity = JSON.toJavaObject(params.get("picEntity"), AssemblyPicEntity.class);
        assemblyEntity.setAssemblyCurrencyData(picEntity);
        return addAssembly(iAssemblyCurrency, assemblyEntity);
    }

    @Override
    @RequestMapping("/queryAssemblyById")
    public Response queryAssemblyById(@RequestBody Map<String, String> params) {
        return queryAssemblyById(iAssemblyCurrency, params);
    }

    @Override
    @RequestMapping("/updatePicAssemblyDataById")
    public Response updateAssemblyDataById(@RequestBody AssemblyPicEntity picEntity) {
        Response response = Response.getInstance();
        boolean b = iAssemblyCurrency.updataAssemblyDataById(picEntity);
        if (b) {
            response.setOk(0, null, "修改成功！", b);
        } else {
            response.setError(1000, null, "修改失败！");
        }
        return response;
    }
}

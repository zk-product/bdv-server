package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;
import cn.piesat.biserver.service.IAssemblyCurrency;
import com.alibaba.fastjson.JSON;
import org.springframework.validation.BindingResult;

import java.util.Map;

/**
 * 所有组件controller的超类
 * 新增组件需要继承此类
 * @author zk
 * @date 2020/1/6 15:12
 */
public abstract class AbstractAssemblyController<T extends AssemblyCurrencyEntity> {

    /**
     * 新增组件公共方法
     * @param assemblyEntity
     * @param bindingResult
     * @return
     */
    public abstract Response addAssembly(AssemblyEntity assemblyEntity, BindingResult bindingResult);

    /**
     * 新增组件公共方法
     * 如果前端传组件数据默认值，需要实现此方法
     * @param params
     * @param bindingResult
     * @return
     */
    public Response addAssemblyByDefault(Map<String, JSON> params, BindingResult bindingResult) {
        return null;
    }
    /**
     * 根据组件ID查询组件信息
     * @param params
     * @return
     */
    public abstract Response queryAssemblyById(Map<String, String> params);

    /**
     * 修改组件数据
     * @param t 组件实体
     * @return
     */
    public abstract Response updateAssemblyDataById(T t);

    public Response addAssembly(IAssemblyCurrency iAssemblyCurrency, AssemblyEntity assemblyEntity) {
        Response response = Response.getInstance();
        assemblyEntity.setDegree(0);
        assemblyEntity.setCollection(0);
        assemblyEntity.setAssemblyTitle("自定义标题");
        AssemblyEntity entity = iAssemblyCurrency.addCurrencyAssembly(assemblyEntity);
        if (entity != null) {
            response.setOk(0, null, "添加常规组件成功！", entity);
        } else {
            response.setError(1000, null, "添加常规组件失败！");
        }
        return response;
    }

    public Response queryAssemblyById(IAssemblyCurrency iAssemblyCurrency, Map<String, String> params) {
        Response response = Response.getInstance();
        String id = params.get("id");
        AssemblyEntity assemblyEntity = iAssemblyCurrency.queryAssemblyById(id);
        if (assemblyEntity != null) {
            response.setOk(0, null, "查询组件信息成功！", assemblyEntity);
        } else {
            response.setError(10000, null, "查询组件信息失败");
        }
        return response;
    }

}

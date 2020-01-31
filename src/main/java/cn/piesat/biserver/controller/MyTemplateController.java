package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.common.UploadFile;
import cn.piesat.biserver.entity.MyTemplateEntity;
import cn.piesat.biserver.service.IMyTemplate;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 大屏管理 Controller层
 * @author zk
 * @date 2019/8/28 10:26
 */
@RestController
@RequestMapping("/myTemplate")
public class MyTemplateController {

    @Autowired
    private IMyTemplate iMyTemplate;
    @Autowired
    private UploadFile uploadFile;

    @RequestMapping("/queryTemplate")
    public Response queryTemplate(@RequestBody MyTemplateEntity myTemplateEntity) {
        Response response = Response.getInstance();
        if (myTemplateEntity.getTemplateType() == null) {
            myTemplateEntity.setTemplateType(0);
        }
        List<MyTemplateEntity> list = iMyTemplate.queryTemplate(myTemplateEntity);
        if (list != null) {
            PageInfo<MyTemplateEntity> pageInfo = new PageInfo<>(list);
            long total = pageInfo.getTotal();
            response.setOk(0, null, "查询成功！", list, total);
        } else {
            response.setError(10000, null, "查询失败！");
        }
        return response;
    }

    @RequestMapping("/queryTemplateById")
    public Response queryTemplateById(@RequestBody MyTemplateEntity myTemplateEntity) {
        Response response = Response.getInstance();
        MyTemplateEntity entity = iMyTemplate.queryTemplateById(myTemplateEntity);
        if (entity != null) {
            response.setOk(0, null, "查询成功！", entity);
        } else {
            response.setError(10000, null, "查询失败！");
        }
        return response;
    }

    @RequestMapping("/addMyTemplate")
    public Response addMyTemplate(@RequestBody MyTemplateEntity myTemplate, BindingResult bindingResult) {
        Response response = Response.getInstance();
        myTemplate.setTemplateType(0);
        myTemplate.setIsSecurity(0);
        myTemplate.setIsRelease(0);
        myTemplate.setBackgroundColor("#FFFFFF");
        myTemplate.setWidth("1920");
        myTemplate.setHeight("1080");
        MyTemplateEntity entity = iMyTemplate.addMyTemplate(myTemplate);
        if (entity != null) {
            response.setOk(0, null, "添加成功！", entity);
        } else {
            response.setError(10000, null, "添加失败！");
        }
        return response;
    }

    @RequestMapping("/addScreenByTemplate")
    public Response addScreenByTemplate(@RequestBody MyTemplateEntity myTemplate) {
        Response response = Response.getInstance();
        // 默认普通大屏
        myTemplate.setTemplateType(0);
        // 默认不加密
        myTemplate.setIsSecurity(0);
        // 默认不发布
        myTemplate.setIsRelease(0);
        String uuid = iMyTemplate.addScreenByTemplate(myTemplate.getId(),myTemplate.getName());
        if (uuid != null && !"".equals(uuid)) {
            response.setOk(0, null, "添加成功！", uuid);
        } else {
            response.setError(10000, null, "添加失败！");
        }
        return response;
    }

    @RequestMapping("/deleteTemplateById/{id}")
    public Response deleteTemplateById(@PathVariable String id) {
        Response response = Response.getInstance();
        boolean b = iMyTemplate.deleteTemplateById(id);
        if (b) {
            response.setOk(0, null, "删除大屏成功！", b);
        } else {
            response.setError(10000, null, "删除大屏失败！");
        }
        return response;
    }

    @RequestMapping("/updateTemplate")
    public Response updateTemplate(@RequestBody @Validated(value = {MyTemplateEntity.CollectionTemplate.class}) MyTemplateEntity entity, BindingResult bindingResult) {
        Response response = Response.getInstance();
        boolean b = iMyTemplate.updateTemplateById(entity);
        if (b) {
            response.setOk(0, null, "修改成功！", b);
        } else {
            response.setError(10000, null, "修改失败！");
        }
        return response;
    }

    @RequestMapping("/uploadCover")
    public Response uploadCover(@RequestParam("id") String id, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Response response = Response.getInstance();
        String filePath = iMyTemplate.uploadCover(id, file);
        if (!"".equals(filePath)) {
                response.setOk(0, null, "上传成功！", filePath);
        } else {
            response.setError(10000, null, "上传失败！");
        }

        return response;
    }
    @RequestMapping("/uploadAutoCover")
    public Response uploadCover(@RequestBody Map<String, String> params){
        Response response = Response.getInstance();
        String id = params.get("id");
        String file = params.get("file");

        String filePath = iMyTemplate.uploadCover(id, file);
        if (!"".equals(filePath)) {
            response.setOk(0, null, "上传成功！", filePath);
        } else {
            response.setError(10000, null, "上传失败！");
        }

        return response;
    }
    @RequestMapping("/uploadBackground")
    public Response uploadBackground(@RequestParam("id") String id, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Response response = Response.getInstance();
        String filePath = iMyTemplate.uploadBackground(id, file);
        if (!"".equals(filePath)) {
            response.setOk(0, null, "上传成功！", filePath);
        } else {
            response.setError(10000, null, "上传失败！");
        }

        return response;
    }

}

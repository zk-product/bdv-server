package cn.piesat.biserver.service;

import cn.piesat.biserver.entity.MyTemplateEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zk
 * @date 2019/8/28 11:25
 */
public interface IMyTemplate {
    /**
     * 查询我的模板
     * @param myTemplateEntity
     * @return
     */
    List<MyTemplateEntity> queryTemplate(MyTemplateEntity myTemplateEntity);

    /**
     * 根据id查询大屏
     * @param myTemplateEntity
     * @return
     */
    MyTemplateEntity queryTemplateById(MyTemplateEntity myTemplateEntity);

    /**
     * 添加我的模板
     * @param myTemplate
     * @return 模板id
     */
    MyTemplateEntity addMyTemplate(MyTemplateEntity myTemplate);

    /**
     * 根据模板id删除模板及组件等数据
     * @param id
     * @return
     */
    boolean deleteTemplateById(String id);

    /**
     * 修改大屏
     * @param entity
     * @return
     */
    boolean updateTemplateById(MyTemplateEntity entity);

    /**
     *  根据模板添加大屏
     * @param id
     * @return
     */
    String addScreenByTemplate(String id, String name);

    /**
     * 上传大屏封面
     * @param id
     * @param file
     * @return
     */
    String uploadCover(String id, MultipartFile file);

    /**
     * 基于base64上传大屏封面
     * @param id 大屏ID
     * @param file 文件前缀 + base64码
     * @return
     */
    String uploadCover(String id, String file);

    /**
     * 修改大屏背景图
     * @param id
     * @param file
     * @return
     */
    String uploadBackground(String id, MultipartFile file);
}

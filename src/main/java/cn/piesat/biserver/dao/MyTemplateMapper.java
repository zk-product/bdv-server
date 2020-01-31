package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.MyTemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 大屏管理仓储
 * @author zk
 * @date 2018/8/28 09:21
 */
@Repository
public interface MyTemplateMapper {

    /**
     * 查询我的大屏
     * @param myTemplateEntity
     * @return
     */
    List<MyTemplateEntity> queryTemplate(MyTemplateEntity myTemplateEntity);

    /**
     * 根据id查询我的大屏
     * @param id
     * @return
     */
    MyTemplateEntity queryTemplateById(String id);
    /**
     * 添加模板
     * @param record
     * @return 添加的数量
     */
    int insert(MyTemplateEntity record);

    /**
     * 修改大屏类型，收藏大屏为我的模板
     * @param record
     * @return
     */
    int updateTemplateById(MyTemplateEntity record);

    /**
     * 根据模板id删除模板及组件等数据
     * @param id
     * @return
     */
    int deleteTemplateById(String id);
}
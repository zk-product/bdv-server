package cn.piesat.biserver.entity;

import cn.piesat.biserver.common.entity.PagingEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 大屏实体
 * @author zk
 * @date 2018/8/28 09:21
 */

public class MyTemplateEntity extends PagingEntity {

    public interface CollectionTemplate{}
    public interface AddScreenByTemplate{}
    /**
     * 模板id
     */
    @NotBlank(groups = {CollectionTemplate.class}, message = "id不能为空")
    @NotBlank(groups = {AddScreenByTemplate.class}, message = "id不能为空")
    private String id;

    /**
     * 模板名称
     */
    @NotBlank(message = "模板名称不能为空")
    @NotBlank(groups = {AddScreenByTemplate.class}, message = "模板名称不能为空")
    private String name;

    /**
     * 大屏类型
     */
    private Integer templateType;
    /**
     * 封面地址
     */
    private String picAddr;


    /**
     * 模板顺序
     */
    private Integer templateOrder;

    /**
     * 是否加密
     */
    @NotNull(message = "是否加密不能为空")
    private Integer isSecurity;

    /**
     * 密码
     */
    private String password;

    /**
     *  是否发布
     */
    private Integer isRelease;

    /**
     * 大屏宽度，默认宽度1920
     */
    private String width;

    /**
     * 大屏高度，默认高度1080
     */
    private String height;

    /**
     * 背景色，默认颜色白色
     */
    private String backgroundColor;

    /**
     * 背景图片
     */
    private String backgroundPic;

    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 备注
     */
    private String mark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getPicAddr() {
        return picAddr;
    }

    public void setPicAddr(String picAddr) {
        this.picAddr = picAddr == null ? null : picAddr.trim();
    }

    public Integer getTemplateOrder() {
        return templateOrder;
    }

    public void setTemplateOrder(Integer templateOrder) {
        this.templateOrder = templateOrder;
    }

    public Integer getIsSecurity() {
        return isSecurity;
    }

    public void setIsSecurity(Integer isSecurity) {
        this.isSecurity = isSecurity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(Integer isRelease) {
        this.isRelease = isRelease;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBackgroundPic() {
        return backgroundPic;
    }

    public void setBackgroundPic(String backgroundPic) {
        this.backgroundPic = backgroundPic;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }
}
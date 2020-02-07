package cn.piesat.biserver.entity;

import cn.piesat.biserver.entity.assembly.AssemblyCurrencyEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 组件通用实体
 * @author zk
 * @date 2019/8/29 14:03
 */
public class AssemblyEntity {
    public interface ById{};
    /**
     * 主键id
     */
    @NotBlank(groups = ById.class, message = "id不能为空")
    private String id;

    /**
     * 模板id（外键）
     */
    @NotBlank(message = "模板id不能为空")
    private String screenId;

    /**
     * 组件标题
     */
    private String assemblyTitle;

    /**
     * 组件名称
     */
    @NotBlank(message = "组件名称不能为空")
    private String assemblyName;

    /**
     * 组件类型
     * 0，常规组件；
     * 1，地图组件；
     * 2，素材组件
     */
    @NotBlank(message = "组件类型不能为空")
    private String assemblyType;

    /**
     * 数据类型
     * 0，无数据；
     * 1，静态数据；
     * 2，API
     */
    @NotNull(message = "数据类型不能为空")
    private Integer dataType;

    /**
     * 图形斜度，默认为0
     */
    private Integer degree;

    /**
     * 左上角横坐标
     */
    @NotNull(message = "左上角横坐标不能为空")
    private Integer x;

    /**
     * 左上角纵坐标
     */
    @NotNull(message = "左上角纵坐标不能为空")
    private Integer y;

    /**
     * 图形宽度
     */
    @NotNull(message = "图形宽度不能为空")
    private Integer width;

    /**
     * 图形高度
     */
    @NotNull(message = "图形高度不能为空")
    private Integer height;

    /**
     *  是否收藏，默认不收藏
     */
    private Integer collection;

    @Deprecated
    private AssemblyDataEntity assemblyDataObj;

    private AssemblyCurrencyEntity assemblyCurrencyData;

    public AssemblyCurrencyEntity getAssemblyCurrencyData() {
        return assemblyCurrencyData;
    }

    public void setAssemblyCurrencyData(AssemblyCurrencyEntity assemblyCurrencyData) {
        this.assemblyCurrencyData = assemblyCurrencyData;
    }


    @Deprecated
    public AssemblyDataEntity getAssemblyDataObj() {
        return assemblyDataObj;
    }

    @Deprecated
    public void setAssemblyDataObj(AssemblyDataEntity assemblyDataObj) {
        this.assemblyDataObj = assemblyDataObj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getAssemblyTitle() {
        return assemblyTitle;
    }

    public void setAssemblyTitle(String assemblyTitle) {
        this.assemblyTitle = assemblyTitle;
    }

    public String getAssemblyName() {
        return assemblyName;
    }

    public void setAssemblyName(String assemblyName) {
        this.assemblyName = assemblyName == null ? null : assemblyName.trim();
    }

    public String getAssemblyType() {
        return assemblyType;
    }

    public void setAssemblyType(String assemblyType) {
        this.assemblyType = assemblyType;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }
}
package cn.piesat.biserver.entity;

import javax.validation.constraints.NotBlank;

/**
 * 组件数据实体
 * @author zk
 * @date 2019/8/29 14:03
 */
public class AssemblyDataEntity {
    public interface QueryAllAPI{}
    /**
     *  数据ID
     */
    private String dataId;

    /**
     *  组件ID
     */
    @NotBlank(groups = {QueryAllAPI.class}, message = "组件id不能为空")
    private String assemblyId;

    /**
     *  数据类型
     */
    private Integer dataType;

    /**
     *  图例
     */
    private String dataLegend;

    /**
     * 数据
     */
    private String assemblyData;

    /**
     *  x轴映射
     */
    private String xMapping;

    /**
     *  y轴映射
     */
    private String yMapping;

    /**
     * 刷新频率
     * 0：不刷新
     */
    private Integer refresh;

    /**
     * 刷新频率
     */
    private String frequency;

    /**
     * 频率单位
     */
    private String frequencyUnit;

    /**
     *  加载限制
     */
    private Integer loadingLimit;

    public String getDataLegend() {
        return dataLegend;
    }

    public void setDataLegend(String dataLegend) {
        this.dataLegend = dataLegend;
    }

    public String getxMapping() {
        return xMapping;
    }

    public void setxMapping(String xMapping) {
        this.xMapping = xMapping;
    }

    public String getyMapping() {
        return yMapping;
    }

    public void setyMapping(String yMapping) {
        this.yMapping = yMapping;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId == null ? null : assemblyId.trim();
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getAssemblyData() {
        return assemblyData;
    }

    public void setAssemblyData(String assemblyData) {
        this.assemblyData = assemblyData == null ? null : assemblyData.trim();
    }

    public Integer getRefresh() {
        return refresh;
    }

    public void setRefresh(Integer refresh) {
        this.refresh = refresh;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }

    public Integer getLoadingLimit() {
        return loadingLimit;
    }

    public void setLoadingLimit(Integer loadingLimit) {
        this.loadingLimit = loadingLimit;
    }
}
package cn.piesat.biserver.entity.assembly;

/**
 * 常规组件实体类
 * @author zk
 * @date 2020/1/2 17:44
 */
public class AssemblyRoutlineEntity extends AssemblyCurrencyEntity {
    /**
     *  图例
     */
    private String dataLegend;

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

package cn.piesat.biserver.entity.assembly;

/**
 * 地图组件实体
 * @author zk
 * @date 2020/1/28 23:27
 */
public class AssemblyMapEntity extends AssemblyCurrencyEntity{

    private String dataLegend;

    private String xMapping;

    private String yMapping;

    private String vMapping;

    private Integer refresh;

    private Integer frequency;

    private String frequencyUnit;

    private Integer loadingLimit;

    public String getDataLegend() {
        return dataLegend;
    }

    public void setDataLegend(String dataLegend) {
        this.dataLegend = dataLegend == null ? null : dataLegend.trim();
    }

    public String getxMapping() {
        return xMapping;
    }

    public void setxMapping(String xMapping) {
        this.xMapping = xMapping == null ? null : xMapping.trim();
    }

    public String getyMapping() {
        return yMapping;
    }

    public void setyMapping(String yMapping) {
        this.yMapping = yMapping == null ? null : yMapping.trim();
    }

    public String getvMapping() {
        return vMapping;
    }

    public void setvMapping(String vMapping) {
        this.vMapping = vMapping == null ? null : vMapping.trim();
    }

    public Integer getRefresh() {
        return refresh;
    }

    public void setRefresh(Integer refresh) {
        this.refresh = refresh;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit == null ? null : frequencyUnit.trim();
    }

    public Integer getLoadingLimit() {
        return loadingLimit;
    }

    public void setLoadingLimit(Integer loadingLimit) {
        this.loadingLimit = loadingLimit;
    }

}
package cn.piesat.biserver.entity.assembly;

/**
 * 组件数据通用实体类
 * @author zk
 * @date 2020/1/2 18:02
 */
public class AssemblyCurrencyEntity {
    /**
     *  数据ID
     */
    private String dataId;

    /**
     *  组件ID
     */
    private String assemblyId;

    /**
     *  数据类型
     */
    private Integer dataType;

    /**
     * 数据
     */
    private String assemblyData;

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
        this.assemblyId = assemblyId;
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
        this.assemblyData = assemblyData;
    }
}

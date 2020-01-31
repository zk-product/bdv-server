package cn.piesat.biserver.entity.statistics;

/**
 * 我的组件统计实体
 * @author zk
 * @date 2019/9/20 15:08
 */
public class MyAssemblyEntity {
    /**
     * 组件数量
     */
    private int assemblyNum;
    /**
     * 组件类型
     */
    private String assemblyType;

    public int getAssemblyNum() {
        return assemblyNum;
    }

    public void setAssemblyNum(int assemblyNum) {
        this.assemblyNum = assemblyNum;
    }

    public String getAssemblyType() {
        return assemblyType;
    }

    public void setAssemblyType(String assemblyType) {
        this.assemblyType = assemblyType;
    }
}

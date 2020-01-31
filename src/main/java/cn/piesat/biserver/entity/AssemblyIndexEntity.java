package cn.piesat.biserver.entity;

/**
 * 组件索引实体
 * @author zk
 * @date 2020/1/28 23:31
 */
public class AssemblyIndexEntity {
    private Integer id;

    private String assemblyName;

    private String assemblyType;

    private String className;

    private String methodName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.assemblyType = assemblyType == null ? null : assemblyType.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }
}
package cn.piesat.biserver.entity;

/**
 * CSV管理实体
 * @author zk
 * @date 2020/1/28 23:32
 */
public class CsvManagerEntity {
    private String id;

    private String name;

    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}
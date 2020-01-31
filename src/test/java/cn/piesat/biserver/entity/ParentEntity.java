package cn.piesat.biserver.entity;

/**
 * @author zk
 * @date 2019/12/16 17:27
 */
public class ParentEntity {
    private Integer id;
    private String name;
    private ChildEntity childEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChildEntity getChildEntity() {
        return childEntity;
    }

    public void setChildEntity(ChildEntity childEntity) {
        this.childEntity = childEntity;
    }

    @Override
    public String toString() {
        return "ParentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", childEntity=" + childEntity +
                '}';
    }
}

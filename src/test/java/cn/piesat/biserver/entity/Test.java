package cn.piesat.biserver.entity;

/**
 * @author zk
 * @date 2019/12/16 17:38
 */
public class Test {
    public static void main(String[] args) {
        ParentEntity p = new ParentEntity();
        p.setId(1);
        p.setName("parent");
        ChildEntity1 c1 = new ChildEntity1();
        c1.setDataId(11);
        c1.setAge(20);
        c1.setName("c1");
        c1.setAddr("aaa");
        p.setChildEntity(c1);
        ChildEntity1 childEntity = (ChildEntity1) p.getChildEntity();
        childEntity.getAddr();
        System.out.println(p);
    }
}

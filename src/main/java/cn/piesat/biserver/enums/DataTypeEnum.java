package cn.piesat.biserver.enums;

/**
 * 组件数据类型枚举
 * @author zk
 * @date 2019/10/18 14:25
 */
public enum  DataTypeEnum {
    JSON(1),
    API(2),
    CSV(3),
    MYSQL(4);

    private final Integer index;
    DataTypeEnum(int index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}

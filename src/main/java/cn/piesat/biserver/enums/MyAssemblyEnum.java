package cn.piesat.biserver.enums;

/**
 * 我的组件枚举
 * @author zk
 * @date 2019/9/20 13:25
 */
public enum MyAssemblyEnum {
    /**
     * 常规组件
     */
    ROUTINE("routine", "01"),
    /**
     * 空间组件
     */
    SPACE("space", "02");

    private final String key;
    private final String value;

    MyAssemblyEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}

package cn.piesat.biserver.enums;

/**
 * 模板统计枚举
 * @author zk
 * @date 2019/9/20 10:43
 */
public enum TemplateEnum {
    /**
     * 系统模板
     */
    SYSTEM_TEMPLATE("systemTemplate", 2),
    /**
     * 我的模板
     */
    MY_TEMPLATE("myTemplate", 1);

    private final String key;
    private final int value;

    TemplateEnum(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

}

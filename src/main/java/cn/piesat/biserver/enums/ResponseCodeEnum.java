package cn.piesat.biserver.enums;

/**
 * 响应代号枚举
 * @author zk
 * @date 2019/10/17 9:39
 */
public enum ResponseCodeEnum {
    OK(0, "成功"),
    FAILED(10000, "失败");

    private final Integer key;
    private final String value;

    ResponseCodeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

package cn.piesat.biserver.enums;

/**
 * 请求类型枚举
 * @author zk
 * @date 2019/10/15 14:46
 */
public enum RequestTypeEnum {
    GET(0, "get"),
    POST(1, "post");

    private final int key;
    private final String value;
    RequestTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

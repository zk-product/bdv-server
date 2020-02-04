package cn.piesat.biserver.enums;

/**
 * CSV文件发布状态枚举
 *
 * @author zk
 * @date 2020/2/4 20:24
 */
public enum CsvStatusEnum {
    NO(0, "未发布"),
    OK(1, "发布");

    private final int key;
    private final String value;

    CsvStatusEnum(int key, String value) {
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

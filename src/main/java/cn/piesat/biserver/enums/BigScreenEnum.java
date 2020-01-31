package cn.piesat.biserver.enums;

/**
 * 大屏发布状态枚举
 * @author zk
 * @date 2019/9/20 10:43
 */
public enum BigScreenEnum {
    /**
     * 未发布
     */
    RELEASE_NO("releaseNo", 0),
    /**
     * 已发布
     */
    RELEASE_OK("releaseOk", 1);

    private final String key;
    private final int value;

    BigScreenEnum(String key, int value) {
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

package cn.piesat.biserver.common;

import java.util.Map;

/**
 * 自定义异常
 * 方便组织异常字段格式
 * @author zk
 * @date 2019/8/28 17:55
 */
public class MyException extends RuntimeException {
    private Map<String, String> map;

    public MyException(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}

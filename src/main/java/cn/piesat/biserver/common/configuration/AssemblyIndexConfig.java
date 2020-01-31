package cn.piesat.biserver.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 组件索引配置
 * 用于反射查找类
 * @author zk
 * @date 2019/12/30 10:39
 */
@Component
@ConfigurationProperties(prefix = "index-collection")
public class AssemblyIndexConfig {
    private Map<String, Map<String, String>> maps;

    public Map<String, Map<String, String>> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Map<String, String>> maps) {
        this.maps = maps;
    }
}

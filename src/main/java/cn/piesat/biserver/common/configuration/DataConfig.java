package cn.piesat.biserver.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 组件默认数据配置
 * @author zk
 * @date 2019/9/2 15:56
 */
@Component
@ConfigurationProperties(prefix = "data-collection")
public class DataConfig {
    private Map<String,String> maps;

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }
}

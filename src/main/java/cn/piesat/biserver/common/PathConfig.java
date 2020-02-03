package cn.piesat.biserver.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 将字节流写入文件
 * @author zk
 * @date 2019/9/30 16:04
 */
@Component
public class PathConfig {
    /**
     * 文件发布目录地址
     */
    @Value("${web.releasePath}")
    private String releasePath;

    public String getReleasePath() {
        return releasePath;
    }
}

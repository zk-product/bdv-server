package cn.piesat.biserver.util;

import cn.piesat.biserver.common.PathConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件操作工具类
 *
 * @author zk
 * @date 2020/2/3 18:42
 */
@Component
public class FileUtil {

    @Autowired
    private PathConfig pathConfig;

    /**
     * 文件发布目录地址
     */
    private static String releasePath;

    /**
     * 初始化参数
     *  @PostConstruct 在@Autowired之后自动执行
     */
    @PostConstruct
    public void init() {
        releasePath = this.pathConfig.getReleasePath();
    }
    /**
     *  写文件
     * @param filePath 文件路径
     * @param bytes 字节流
     * @param release 是否发布
     * @return
     */
    public static String writeFile(String filePath, byte[] bytes, boolean release) {
        if (release) {
            filePath += releasePath + filePath;
        }
        Path path = Paths.get(filePath);
        boolean exists = Files.exists(path);
        if (!exists) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}

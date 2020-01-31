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
public class UploadFile {
    @Value("${web.upload-path}")
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    /**
     *  写文件
     * @param filePath
     * @param bytes
     * @return
     */
    public String writeFile(String filePath, byte[] bytes) {
        Path path = Paths.get(uploadPath + filePath);
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
        return uploadPath + filePath;
    }
}

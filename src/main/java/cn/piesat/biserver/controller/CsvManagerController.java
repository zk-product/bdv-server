package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.constant.StatisticsConstant;
import cn.piesat.biserver.entity.CsvManagerEntity;
import cn.piesat.biserver.service.ICsvManager;
import cn.piesat.biserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 绑定CSV数据 Controller层
 * @author zk
 * @date 2019/11/19 15:41
 */
@RestController
@RequestMapping("/CSVManager")
public class CsvManagerController {

    @Autowired
    private ICsvManager iCsvManager;
    @Value("${web.csvPath}")
    private String csvPath;

    @RequestMapping("/uploadCSV")
    public Response uploadCover(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Response response = Response.getInstance();
        String fileName = file.getOriginalFilename();
        String filePath = csvPath + StatisticsConstant.CSV_PATH + fileName;
        try {
            FileUtil.writeFile(filePath, file.getBytes(), false);
            CsvManagerEntity entity = new CsvManagerEntity();
            entity.setName(fileName);
            entity.setPath(filePath);
            boolean b = iCsvManager.addCsvInfo(entity);
            if (b) {
                response.setOk(0, null, "上传成功！", b);
            } else {
                response.setError(10000, null, "上传失败！");
            }

        } catch (IOException e) {
            e.printStackTrace();
            response.setError(10000, null, "上传失败！");
        }
        return response;
    }

    @RequestMapping("/analysisExcel")
    public Response analysisExcel(@RequestBody Map<String, String> params) {
        Response response = Response.getInstance();
        List<Map<String, String>> maps = iCsvManager.analysisExcel(params.get("id"));
        if (maps != null) {
            response.setOk(0, null, "解析成功！", maps);
        } else {
            response.setError(10000, null, "解析失败！");
        }
        return response;
    }
}

package cn.piesat.biserver.controller;

import cn.piesat.biserver.common.Response;
import cn.piesat.biserver.service.IStatisticsAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 统计管理 Controller层
 * @author zk
 * @date 2019/9/19 17:00
 */
@RestController
@RequestMapping("/StatisticsAnalysis")
public class StatisticsAnalysisController {
    @Autowired
    private IStatisticsAnalysis isa;
    @RequestMapping("/bigScreenStatistic")
    public Response bigScreenStatistic() {
        Response response = Response.getInstance();
        Map<String, Integer> map = isa.bigScreenStatistic();
        response.setOk(0, null, "大屏统计成功",map);
        return response;
    }

    @RequestMapping("/templateStatistic")
    public Response templateStatistic() {
        Response response = Response.getInstance();
        Map<String, Integer> map = isa.templateStatistic();
        response.setOk(0, null, "模板统计成功",map);
        return response;
    }

    @RequestMapping("/myAssemblyStatistic")
    public Response myAssemblyStatistic() {
        Response response = Response.getInstance();
        Map<String, Integer> map = isa.myAssemblyStatistic();
        response.setOk(0, null, "我的组件统计成功",map);
        return response;
    }
}

package cn.piesat.biserver.service;

import cn.piesat.biserver.entity.statistics.BigScreenEntity;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2019/9/19 16:42
 */
public interface IStatisticsAnalysis {

    /**
     * 我的可视化统计
     * @return
     */
    Map<String, Integer> bigScreenStatistic();

    /**
     * 模板统计
     * @return
     */
    Map<String,Integer> templateStatistic();

    /**
     * 我的组件统计
     * @return
     */
    Map<String, Integer> myAssemblyStatistic();
}

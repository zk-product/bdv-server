package cn.piesat.biserver.dao;

import cn.piesat.biserver.entity.statistics.MyAssemblyEntity;
import cn.piesat.biserver.entity.statistics.BigScreenEntity;
import cn.piesat.biserver.entity.statistics.TemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 统计仓储
 * @author zk
 * @date 2019/9/19 14:26
 */
@Repository
public interface StatisticsAnalysisMapper {
    /**
     * 我的可视化统计
     * @return
     */
    List<BigScreenEntity> bigScreenStatistic();

    /**
     * 模板统计
     * @return
     */
    List<TemplateEntity> templateStatistic();

    /**
     * 我的组件统计
     * @return
     */
    List<MyAssemblyEntity> myAssemblyStatistic();

}

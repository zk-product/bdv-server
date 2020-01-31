package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.constant.StatisticsConstant;
import cn.piesat.biserver.dao.StatisticsAnalysisMapper;
import cn.piesat.biserver.entity.statistics.MyAssemblyEntity;
import cn.piesat.biserver.entity.statistics.BigScreenEntity;
import cn.piesat.biserver.entity.statistics.TemplateEntity;
import cn.piesat.biserver.enums.BigScreenEnum;
import cn.piesat.biserver.enums.MyAssemblyEnum;
import cn.piesat.biserver.enums.TemplateEnum;
import cn.piesat.biserver.service.IStatisticsAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.piesat.biserver.constant.StatisticsConstant.MY_ASSEMBLY_COUNT;

/**
 * @author zk
 * @date 2019/9/19 16:43
 */
@Service
public class StatisticsAnalysisImpl implements IStatisticsAnalysis {
    @Autowired
    private StatisticsAnalysisMapper sam;

    @Override
    public Map<String, Integer> bigScreenStatistic() {
        List<BigScreenEntity> bigScreenEntities = sam.bigScreenStatistic();
        Map<String,Integer> map = new HashMap<>(3);
        int releaseCount = 0;
        for (BigScreenEntity entity : bigScreenEntities) {
            for (BigScreenEnum bigScreenEnum : BigScreenEnum.values()) {
                if (entity.getReleaseStatus() == bigScreenEnum.getValue()) {
                    releaseCount += entity.getReleaseNum();
                    map.put(bigScreenEnum.getKey(), entity.getReleaseNum());
                }
            }
        }
        map.put(StatisticsConstant.RELEASE_COUNT, releaseCount);
        return map;
    }

    @Override
    public Map<String, Integer> templateStatistic() {
        List<TemplateEntity> templateEntities = sam.templateStatistic();
        int templateCount = 0;
        Map<String, Integer> map = new HashMap<>(3);
        for (TemplateEntity templateEntity : templateEntities) {
            for (TemplateEnum templateEnum : TemplateEnum.values()) {
                if (templateEntity.getTemplateType() == templateEnum.getValue()) {
                    templateCount += templateEntity.getTemplateNum();
                    map.put(templateEnum.getKey(), templateEntity.getTemplateNum());
                }
            }
        }
        map.put(StatisticsConstant.TEMPLATE_COUNT, templateCount);
        return map;
    }

    @Override
    public Map<String, Integer> myAssemblyStatistic() {
        List<MyAssemblyEntity> assemblyEntities = sam.myAssemblyStatistic();
        int myAssemblyCount = 0;
        Map<String, Integer> map = new HashMap<>(3);
        for (MyAssemblyEntity assemblyEntity : assemblyEntities) {
            for (MyAssemblyEnum e : MyAssemblyEnum.values()) {
                if (e.getValue().equals(assemblyEntity.getAssemblyType())) {
                    myAssemblyCount += assemblyEntity.getAssemblyNum();
                    map.put(e.getKey(),assemblyEntity.getAssemblyNum());
                }
            }
        }
        map.put(StatisticsConstant.MY_ASSEMBLY_COUNT, myAssemblyCount);
        return map;
    }

}

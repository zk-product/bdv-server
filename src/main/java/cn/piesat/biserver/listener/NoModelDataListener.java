package cn.piesat.biserver.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CSV数据处理监听
 * 阿里EasyExcel
 * @author zk
 * @date 2019/12/5 14:19
 */
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {

    private Map<Integer, String> headMap;
    private List<Map<String, String>> maps = new ArrayList<>();

    public List<Map<String, String>> getMaps() {
        return maps;
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        Map<String, String> headDataMap = new HashMap<>();
        if (headMap != null) {
            for (Map.Entry<Integer, String> entry : data.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                String headDataMapKey = headMap.get(key);
                headDataMap.put(headDataMapKey, value);
            }
        }
        maps.add(headDataMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.headMap = headMap;
    }
}

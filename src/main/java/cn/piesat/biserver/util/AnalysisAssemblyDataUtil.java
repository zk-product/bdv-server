package cn.piesat.biserver.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author zk
 * @date 2019/10/23 14:40
 */
public class AnalysisAssemblyDataUtil {

    /**
     * 提取json字段值
     * @param json
     * @param field
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T extractJSONField(String json, String field, T t) {
        if (json == null || "".equals(json)) {
            return t;
        }
        JSONObject jsonObject = JSON.parseObject(json);
        if (!jsonObject.containsKey(field)) {
            return t;
        }
        return (T) jsonObject.get(field);
    }

    public static String replaceJSONField(String json, String field, String desc) {
        if (json == null || "".equals(json)) {
            return json;
        }
        JSONObject jsonObject = JSON.parseObject(json);
        if (!jsonObject.containsKey(field)) {
            return null;
        }
        jsonObject.replace(field, desc);
        return jsonObject.toString();
    }

    /**
     * 处理带 “[]” 的字符串，变为List<String>
     *
     * @param array
     * @return
     */
    public static List<String> dealArray(String array) {
        String substring = array.substring(1, array.length() - 1);
        substring = substring.replaceAll("\"", "");
        String[] split = substring.split(",");
        return new ArrayList<>(asList(split));
    }
}

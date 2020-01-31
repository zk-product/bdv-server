package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.constant.StatisticsConstant;
import cn.piesat.biserver.dao.AssemblyDataMapper;
import cn.piesat.biserver.entity.APIManagerEntity;
import cn.piesat.biserver.entity.APIParamsEntity;
import cn.piesat.biserver.entity.AssemblyDataEntity;
import cn.piesat.biserver.enums.RequestTypeEnum;
import cn.piesat.biserver.enums.ResponseCodeEnum;
import cn.piesat.biserver.service.IAPIBind;
import cn.piesat.biserver.util.AnalysisAssemblyDataUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2019/10/16 11:12
 */
@Service
public class APIBindImpl implements IAPIBind {
    @Autowired
    private RestTemplate httpclientTemplate;
    @Autowired
    private AssemblyDataMapper dataMapper;

    @Override
    public List<String> getOutParam(APIManagerEntity entity) {
        List<String> paramsList = new ArrayList<String>();
        // 请求接口
        APIResponseEntity response = getAPIResponse(entity);
        if (response.getCode().equals(ResponseCodeEnum.OK.getKey())) {
            JSONArray data = (JSONArray) response.getData();
            JSONObject o = (JSONObject) data.get(0);
            for (String s : o.keySet()) {
                paramsList.add(s);
            }
        }
        return paramsList;
    }

    @Override
    public List<String> optionalField(String dataId, String mappingField) {
        AssemblyDataEntity entity = new AssemblyDataEntity();
        entity.setDataId(dataId);
        AssemblyDataEntity dataEntity = dataMapper.queryAssemblyData(entity);
        /**
         *  解析数据库中存储的组件数据信息
         */
        String assemblyData = dataEntity.getAssemblyData();

        List<String> outParams = AnalysisAssemblyDataUtil.extractJSONField(assemblyData, "outParams",
                new ArrayList<String>());
        if (outParams == null) {
            return null;
        }
        List<String> mappings = new ArrayList<>();
        // 根据选择的映射，去除另一个映射选择的字段，把字段筛选出来
        if ("xMapping".equals(mappingField)) {
            String yMapping = dataEntity.getyMapping();
            if (yMapping != null && !"".equals(yMapping)) {
                mappings = AnalysisAssemblyDataUtil.dealArray(yMapping);
            }
        }
        if ("yMapping".equals(mappingField)) {
            String xMapping = dataEntity.getxMapping();
            if (xMapping != null && !"".equals(xMapping)) {
                mappings = AnalysisAssemblyDataUtil.dealArray(xMapping);
            }
        }
        // 去除已选字段
        outParams.removeAll(mappings);
        return outParams;
    }

    public APIResponseEntity getAPIResponse(APIManagerEntity entity) {
        HttpHeaders headers = new HttpHeaders();

        // 获取要请求的url地址
        String url = entity.getUrl();
        // 获取要请求的参数列表
        List<APIParamsEntity> APIParamsEntity = entity.getApiParamsEntity();
        Map<String, Object> paramMap = new HashMap<>();
        for (APIParamsEntity paramsEntity : APIParamsEntity) {
            String APIParam = paramsEntity.getApiParam();
            String APIParamVal = paramsEntity.getApiParamVal();
            paramMap.put(APIParam, APIParamVal);
        }

        // 拼接参数
        StringBuilder params = new StringBuilder();
        // 是否token验证
        Integer authentication = entity.getAuthentication();
        //是否要放入头信息
        Integer header = entity.getHeader();
        if (authentication == 1 && header == 1) {
            String tokenKey = entity.getTokenKey();
            String tokenValue = entity.getTokenValue();
            headers.add(tokenKey, tokenValue);
        }
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(paramMap, headers);
        ResponseEntity<String> result = null;
        //请求类型
        Integer typeId = entity.getTypeId();
        /**
         *  GET请求与POST请求拼接token方式不同
         *  GET请求：http://ip:port/biserver?token={token} 采用占位方式
         *  POST请求：http://ip:port/biserver?token=tokenVal 直接传值方式
         *  由于方式不同，导致GET请求和POST请求需要单独处理token拼接
         */
        if (typeId == RequestTypeEnum.GET.getKey()) {
            if (authentication == 1 && header == 0) {
                String tokenKey = entity.getTokenKey();
                String tokenValue = entity.getTokenValue();
                params.append("?")
                        .append(tokenKey)
                        .append("={")
                        .append(tokenKey)
                        .append("}");
                paramMap.put(tokenKey, tokenValue);
            }
            for (APIParamsEntity paramsEntity : APIParamsEntity) {
                String APIParam = paramsEntity.getApiParam();
                /**
                 * 判断是否已经拼接token
                 * 如果已经拼接token，后续开头拼接“&”
                 * 如果未拼接token，后续开头拼接“？”
                 */
                if (params.length() > 0) {
                    params.append("&");
                } else {
                    params.append("?");
                }
                params.append(APIParam)
                        .append("={")
                        .append(APIParam)
                        .append("}");
            }
            // 拼接url和参数
            url += params.toString();
            result = httpclientTemplate.getForEntity(url, String.class, paramMap);
        } else if (typeId == RequestTypeEnum.POST.getKey()) {
            if (authentication == 1 && header == 0) {
                String tokenKey = entity.getTokenKey();
                String tokenValue = entity.getTokenValue();
                params.append("?")
                        .append(tokenKey)
                        .append("=")
                        .append(tokenValue);
            }
            result = httpclientTemplate.postForEntity(url, httpEntity, String.class);
        } else {
        }
        if (result.getStatusCode().value() == StatisticsConstant.STATUS_CODE_OK) {
            return JSON.parseObject(result.getBody(), new TypeReference<APIResponseEntity>() {
            });
        }
        return null;
    }

    private static class APIResponseEntity {
        private String msg;
        private Integer code;
        private Object data;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}


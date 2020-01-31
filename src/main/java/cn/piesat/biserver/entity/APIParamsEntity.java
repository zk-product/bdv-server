package cn.piesat.biserver.entity;

/**
 * API参数管理实体
 * @author zk
 * @date 2019/9/27 10:37
 */
public class APIParamsEntity {
    private String paramId;

    private String apiId;

    private String apiParam;

    private String paramAlias;

    private String apiParamVal;

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiParam() {
        return apiParam;
    }

    public void setApiParam(String apiParam) {
        this.apiParam = apiParam;
    }

    public String getParamAlias() {
        return paramAlias;
    }

    public void setParamAlias(String paramAlias) {
        this.paramAlias = paramAlias == null ? null : paramAlias.trim();
    }

    public String getApiParamVal() {
        return apiParamVal;
    }

    public void setApiParamVal(String apiParamVal) {
        this.apiParamVal = apiParamVal;
    }
}
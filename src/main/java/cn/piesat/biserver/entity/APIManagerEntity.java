package cn.piesat.biserver.entity;

import cn.piesat.biserver.common.entity.PagingEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * API管理实体
 * @author zk
 * @date 2019/9/27 10:37
 */
public class APIManagerEntity extends PagingEntity {
    public interface UpdateAPIById{}

    @NotBlank(groups = {UpdateAPIById.class}, message = "id不能为空")
    private String id;

    @NotBlank(message = "API名称不能为空")
    private String apiName;

    @NotBlank(message = "url不能为空")
    private String url;

    @NotNull(message = "请求类型id不能为空")
    private Integer typeId;

    @NotBlank(message = "请求类型名称不能为空")
    private String requestType;

    @NotNull(message = "是否认证不能为空")
    private Integer authentication;

    private String tokenKey;

    private String tokenValue;

    private Integer header;

    private String updateTime;

    private List<APIParamsEntity> apiParamsEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    public Integer getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Integer authentication) {
        this.authentication = authentication;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey == null ? null : tokenKey.trim();
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue == null ? null : tokenValue.trim();
    }

    public Integer getHeader() {
        return header;
    }

    public void setHeader(Integer header) {
        this.header = header;
    }


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<APIParamsEntity> getApiParamsEntity() {
        return apiParamsEntity;
    }

    public void setApiParamsEntity(List<APIParamsEntity> apiParamsEntity) {
        this.apiParamsEntity = apiParamsEntity;
    }
}
package cn.piesat.biserver.common.entity;

import com.alibaba.fastjson.annotation.JSONType;

import javax.validation.constraints.NotNull;

/**
 * 分页查询实体
 * 非分页查询，不返回页码和条数字段
 * @author zk
 * @date 2019/9/6 9:21
 */

@JSONType(ignores = {"pageIndex","pageSize"})
public class PagingEntity {
    public interface QueryByPaging{}

    /**
     * 页码
     */
    @NotNull(groups = QueryByPaging.class, message = "页码不能为空")
    private Integer pageIndex;
    /**
     * 每页条数
     */
    @NotNull(groups = QueryByPaging.class, message = "页容量不能为空")
    private Integer pageSize;


    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

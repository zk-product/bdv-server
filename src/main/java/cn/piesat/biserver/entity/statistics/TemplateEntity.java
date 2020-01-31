package cn.piesat.biserver.entity.statistics;

/**
 * 模板统计实体
 * @author zk
 * @date 2019/9/20 9:31
 */
public class TemplateEntity {
    /**
     * 模板数量
     */
    private int templateNum;
    /**
     * 模板类型
     */
    private int templateType;

    public int getTemplateNum() {
        return templateNum;
    }

    public void setTemplateNum(int templateNum) {
        this.templateNum = templateNum;
    }

    public int getTemplateType() {
        return templateType;
    }

    public void setTemplateType(int templateType) {
        this.templateType = templateType;
    }
}

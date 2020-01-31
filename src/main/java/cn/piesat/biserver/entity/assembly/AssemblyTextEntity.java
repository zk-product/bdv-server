package cn.piesat.biserver.entity.assembly;

/**
 * 文本组件实体
 * @author zk
 * @date 2020/1/8 15:11
 */
public class AssemblyTextEntity extends AssemblyCurrencyEntity {

    private Integer fontSize;

    private String fontColor;

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor == null ? null : fontColor.trim();
    }

}
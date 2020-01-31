package cn.piesat.biserver.entity.dictionary;

/**
 * 字典数据实体
 * @author zk
 * @date 2020/1/28 23:29
 */
public class DictionaryDataEntity {
    private Integer id;

    private Integer dicId;

    private String dicDataVal;

    private String dicDataName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDicId() {
        return dicId;
    }

    public void setDicId(Integer dicId) {
        this.dicId = dicId;
    }

    public String getDicDataVal() {
        return dicDataVal;
    }

    public void setDicDataVal(String dicDataVal) {
        this.dicDataVal = dicDataVal == null ? null : dicDataVal.trim();
    }

    public String getDicDataName() {
        return dicDataName;
    }

    public void setDicDataName(String dicDataName) {
        this.dicDataName = dicDataName == null ? null : dicDataName.trim();
    }
}
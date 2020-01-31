package cn.piesat.biserver.entity.statistics;

/**
 * 大屏统计实体
 * @author zk
 * @date 2019/9/19 14:20
 */
public class BigScreenEntity {
    private int releaseNum;
    private int releaseStatus;

    public int getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(int releaseNum) {
        this.releaseNum = releaseNum;
    }

    public int getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(int releaseStatus) {
        this.releaseStatus = releaseStatus;
    }
}

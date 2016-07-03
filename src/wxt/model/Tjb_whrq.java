package wxt.model;

/**
 * Created by wuxiutong on 15/5/10.
 */
public class Tjb_whrq {
    private String gsdm;//公司代码
    private String gsmc;//公司名称
    private String gmrq;//购买日期
    private String scwhrq; //首次维护日期
    private String whjzrq; //维护截止日期
    private String updateDate; //修改日期
    private String lastCwkpd;//最后更新维护截止日期的单据号

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getLastCwkpd() {
        return lastCwkpd;
    }

    public void setLastCwkpd(String lastCwkpd) {
        this.lastCwkpd = lastCwkpd;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public String getGsmc() {
        return gsmc;
    }

    public void setGsmc(String gsmc) {
        this.gsmc = gsmc;
    }

    public String getGmrq() {
        return gmrq;
    }

    public void setGmrq(String gmrq) {
        this.gmrq = gmrq;
    }

    public String getScwhrq() {
        return scwhrq;
    }

    public void setScwhrq(String scwhrq) {
        this.scwhrq = scwhrq;
    }

    public String getWhjzrq() {
        return whjzrq;
    }

    public void setWhjzrq(String whjzrq) {
        this.whjzrq = whjzrq;
    }
}

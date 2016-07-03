package wxt.model;

/**
 * Created by wuxiutong on 2015/8/18.
 */
public class CMS_RPT_WHRQ {
    String khdm  = new String();//客户代码
    String khmc  = new String();//客户名称
    String qsrq  = new String();//起始日期
    String jzrq  = new String();//截至日期
    String updateDjh  = new String();//最后更行日期的单据号
    String updateUser  = new String();//更新操作的人
    String lastUpdate  = new String();//最后更新的日期

    public String getKhdm() {
        return khdm;
    }

    public void setKhdm(String khdm) {
        this.khdm = khdm;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getQsrq() {
        return qsrq;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public String getUpdateDjh() {
        return updateDjh;
    }

    public void setUpdateDjh(String updateDjh) {
        this.updateDjh = updateDjh;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

package wxt.model;

/**
 * Created by admin on 2015/3/12.
 */
public class Khxx {
    private  String khdm; //客户代码
    private String khmc;  //客户名称
    private String gzdz;  //工作地址
    private String bgdh;  //工作电话
    private String ps;  //备注
    private String khjl;  //客户经理
    private String ssdq;  //所属地区
    private String xsgs;  //所属地区
    private String khlx;  //客户类型

    public String getWhrq() {
        return whrq;
    }

    public void setWhrq(String whrq) {
        this.whrq = whrq;
    }

    private String whrq;  //客户维护截止日期
    private String zt;  //客户状态   分为 1普通客户 2密切客户 3关系脱离

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getXsgs() {
        return xsgs;
    }

    public void setXsgs(String xsgs) {
        this.xsgs = xsgs;
    }

    public String getSsdq() {
        return ssdq;
    }

    public void setSsdq(String ssdq) {
        this.ssdq = ssdq;
    }

    public String getKhlx() {
        return khlx;
    }

    public void setKhlx(String khlx) {
        this.khlx = khlx;
    }

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

    public String getGzdz() {
        return gzdz;
    }

    public void setGzdz(String gzdz) {
        this.gzdz = gzdz;
    }

    public String getBgdh() {
        return bgdh;
    }

    public void setBgdh(String bgdh) {
        this.bgdh = bgdh;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getKhjl() {
        return khjl;
    }

    public void setKhjl(String khjl) {
        this.khjl = khjl;
    }
}

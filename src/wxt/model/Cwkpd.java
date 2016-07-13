package wxt.model;

/**
 * Created by wuxiutong on 15/5/9.
 */
public class Cwkpd {
    //票据号
    private int id;
    //销售公司
    private String gsdm;
    private String gsmc;
    //开票金额
    private double je;
    //票据类型
    private String djlx;
    private double nx;
    //开票日期
    private String kprq;
    //其实日期
    private String qsrq;
    //截至日期
    private String jzrq;
    //开普票人
    private String kpr;
    private String kprxm;
    //录入人
    private String lrr;
    private String lrrxm;
    //审核人
    private String shr;
    private String shrxm;
    //状态
    private String zt;
    //收款人
    private String skr;
    private String skrxm;
    //备注
    private String ps;

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getShrxm() {
        return shrxm;
    }

    public void setShrxm(String shrxm) {
        this.shrxm = shrxm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getJe() {
        return je;
    }

    public void setJe(double je) {
        this.je = je;
    }

    public String getDjlx() {
        return djlx;
    }

    public void setDjlx(String djlx) {
        this.djlx = djlx;
    }

    public double getNx() {
        return nx;
    }

    public void setNx(double nx) {
        this.nx = nx;
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
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

    public String getKpr() {
        return kpr;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr;
    }

    public String getKprxm() {
        return kprxm;
    }

    public void setKprxm(String kprxm) {
        this.kprxm = kprxm;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getLrrxm() {
        return lrrxm;
    }

    public void setLrrxm(String lrrxm) {
        this.lrrxm = lrrxm;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getSkr() {
        return skr;
    }

    public void setSkr(String skr) {
        this.skr = skr;
    }

    public String getSkrxm() {
        return skrxm;
    }

    public void setSkrxm(String skrxm) {
        this.skrxm = skrxm;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}

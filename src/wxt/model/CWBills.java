package wxt.model;

/**
 * Created by wuxiutong on 15/8/16.
 */
public class CWBills {
    private int id;
    private String khdm;//客户代码
    private String khmc;
    private String gsdm;//开票单位
    private String gsmc;
    private double je;
    private String djlx;//单据类型
    private String fylx;//费用类型
    private int nx;//维护年限
    private String kjnd;//维护年限
    private String kprq;//开票日期
    private String qsrq;//起始日期
    private String jzrq;//结束日期
    private String kpr;//开票人
    private String kprxm;//开票人姓名
    private String lrr;//录入人
    private String lrrxm;//录入人姓名
    private String shr;//审核人
    private String shrxm;
    private String skr;//收款人
    private String skrxm;
    private String zt;//单据状态
    private String fph;//发票号
    private String lrrq;//录入日期
    private String ps;//备注

    public String getKjnd() {
        return kjnd;
    }

    public void setKjnd(String kjnd) {
        this.kjnd = kjnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFylx() {
        return fylx;
    }

    public void setFylx(String fylx) {
        this.fylx = fylx;
    }

    public int getNx() {
        return nx;
    }

    public void setNx(int nx) {
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

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getFph() {
        return fph;
    }

    public void setFph(String fph) {
        this.fph = fph;
    }

    public String getLrrq() {
        return lrrq;
    }

    public void setLrrq(String lrrq) {
        this.lrrq = lrrq;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}

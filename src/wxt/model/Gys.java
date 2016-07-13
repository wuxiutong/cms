package wxt.model;

/**
 * Created by wuxiutong on 2015-04-16.
 */
public class Gys {
    private String gysdm; //供应商代码
    private String gysmc;//供应商名称
    private String address;//供应商地址
    private String ps;//备注

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getGysdm() {
        return gysdm;
    }

    public void setGysdm(String gysdm) {
        this.gysdm = gysdm;
    }

    public String getGysmc() {
        return gysmc;
    }

    public void setGysmc(String gysmc) {
        this.gysmc = gysmc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

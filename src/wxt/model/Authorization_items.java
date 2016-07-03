package wxt.model;

/**
 * Created by wuxiutong on 15/8/29.
 */
public class Authorization_items {
    String gndm; //功能代码
    String parentGndm; //父功能代码
    String gnmc; //功能名称
    String gnxm;//功能项目,对应功能模块的action
    String ps;//备注

    public String getGndm() {
        return gndm;
    }

    public void setGndm(String gndm) {
        this.gndm = gndm;
    }

    public String getParentGndm() {
        return parentGndm;
    }

    public void setParentGndm(String parentGndm) {
        this.parentGndm = parentGndm;
    }

    public String getGnmc() {
        return gnmc;
    }

    public void setGnmc(String gnmc) {
        this.gnmc = gnmc;
    }

    public String getGnxm() {
        return gnxm;
    }

    public void setGnxm(String gnxm) {
        this.gnxm = gnxm;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}

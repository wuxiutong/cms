package wxt.model;

/**
 * Created by wuxiutong on 15/8/29.
 */
public class Role {
    String id;//角色代码
    String mc;//角色名称
    String qx;//角色权限,角色权限将是用英文状态下地逗号分隔开
    String ps;//备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}

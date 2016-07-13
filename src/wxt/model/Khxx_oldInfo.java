package wxt.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by wuxiutong on 15/9/5.
 */
public class Khxx_oldInfo implements Serializable{
        private String updateTime;
        private  String khdm; //客户代码
        private String khmc;  //客户名称
        private String gzdz;  //工作地址
        private String bgdh;  //工作电话
        private String ps;  //备注
        private String khjl;  //客户经理
        private String ssdq;  //所属地区
        private String xsgs;  //所属地区
        private String khlx;  //客户类型
        private String zt;  //客户状态   分为 1普通客户 2密切客户 3关系脱离
        private String updateType;  //更新记录
        private String updateUser; //更新人员

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getZt() {
            return zt;
        }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Khxx_oldInfo khxx_oldInfo = (Khxx_oldInfo) o;
        return Objects.equals(khdm, khxx_oldInfo.khdm) &&
                Objects.equals(updateTime, khxx_oldInfo.updateTime);
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public void setZt(String zt) {
            this.zt = zt;
        }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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



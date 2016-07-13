package wxt.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by wuxiutong on 15/4/27.
 */
public class Khxx_soft_oldInfo implements Serializable{
    private String updateTime;
    private String softID;
    private String khdm;
    private String khmc;
    private String gysDm;
    private String gysMc;
    private String verDm;
    private String verMc;
    private String modelDm;
    private String modelMc;
    private String yhs;
    private String gmrq;
    private String ps;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Khxx_soft_oldInfo khxx_soft = (Khxx_soft_oldInfo) o;
        return Objects.equals(updateTime, khxx_soft.updateTime) &&
                Objects.equals(softID, khxx_soft.softID) &&
                Objects.equals(khdm, khxx_soft.khdm) &&
                Objects.equals(gysDm, khxx_soft.gysDm) &&
                Objects.equals(verDm, khxx_soft.verDm) &&
                Objects.equals(modelDm, khxx_soft.modelDm);
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    @Override
    public int hashCode() {
        return Objects.hash(updateTime, khdm);
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSoftID() {
        return softID;
    }

    public void setSoftID(String softID) {
        this.softID = softID;
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

    public String getGysDm() {
        return gysDm;
    }

    public void setGysDm(String gysDm) {
        this.gysDm = gysDm;
    }

    public String getGysMc() {
        return gysMc;
    }

    public void setGysMc(String gysMc) {
        this.gysMc = gysMc;
    }

    public String getVerDm() {
        return verDm;
    }

    public void setVerDm(String verDm) {
        this.verDm = verDm;
    }

    public String getVerMc() {
        return verMc;
    }

    public void setVerMc(String verMc) {
        this.verMc = verMc;
    }

    public String getModelDm() {
        return modelDm;
    }

    public void setModelDm(String modelDm) {
        this.modelDm = modelDm;
    }

    public String getModelMc() {
        return modelMc;
    }

    public void setModelMc(String modelMc) {
        this.modelMc = modelMc;
    }

    public String getYhs() {
        return yhs;
    }

    public void setYhs(String yhs) {
        this.yhs = yhs;
    }

    public String getGmrq() {
        return gmrq;
    }

    public void setGmrq(String gmrq) {
        this.gmrq = gmrq;
    }
}

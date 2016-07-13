package wxt.model;

/**
 * Created by wuxiutong on 15/5/16.
 */
public class User {
    private String userID;
    private String userName;
    private String email;
    private String password;
    private String ssrole;
    private String ps;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsrole() {
        return ssrole;
    }

    public void setSsrole(String ssrole) {
        this.ssrole = ssrole;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}

package Model;

public class BalanceModel {


    Integer intId;
    Integer boolCash;
    String strName;
    String strBankAccountNo;
    Double dblAmount;
    String strCreatedAt;
    Integer boolDeleted;
    String strDeletedAt;
    Integer intUserId;

    public BalanceModel(){}

    public Integer getIntId() {
        return intId;
    }

    public void setIntId(Integer intId) {
        this.intId = intId;
    }

    public Integer getBoolCash() {
        return boolCash;
    }

    public void setBoolCash(Integer boolCash) {
        this.boolCash = boolCash;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrBankAccountNo() {
        return strBankAccountNo;
    }

    public void setStrBankAccountNo(String strBankAccountNo) {
        this.strBankAccountNo = strBankAccountNo;
    }

    public Double getDblAmount() {
        return dblAmount;
    }

    public void setDblAmount(Double dblAmount) {
        this.dblAmount = dblAmount;
    }

    public String getStrCreatedAt() {
        return strCreatedAt;
    }

    public void setStrCreatedAt(String strCreatedAt) {
        this.strCreatedAt = strCreatedAt;
    }

    public Integer getBoolDeleted() {
        return boolDeleted;
    }

    public void setBoolDeleted(Integer boolDeleted) {
        this.boolDeleted = boolDeleted;
    }

    public String getStrDeletedAt() {
        return strDeletedAt;
    }

    public void setStrDeletedAt(String strDeletedAt) {
        this.strDeletedAt = strDeletedAt;
    }

    public Integer getIntUserId() {
        return intUserId;
    }

    public void setIntUserId(Integer intUserId) {
        this.intUserId = intUserId;
    }
}

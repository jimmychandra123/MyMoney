package Model;

public class OutcomeModel {
    Integer intId;
    String strDate;
    String strDescription;
    Double dblAmount;
    String strCreatedAt;
    Integer IntOutComeTypeId;
    Integer IntBalanceId;
    Integer intUserId;

    String strOutcomeType;
    String strBalance;


    public OutcomeModel(){}
    
    public String getStrOutcomeType() {
        return strOutcomeType;
    }

    public void setStrOutcomeType(String strOutcomeType) {
        this.strOutcomeType = strOutcomeType;
    }

    public String getStrBalance() {
        return strBalance;
    }

    public void setStrBalance(String strBalance) {
        this.strBalance = strBalance;
    }


    public Integer getIntId() {
        return intId;
    }

    public void setIntId(Integer intId) {
        this.intId = intId;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
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

    public Integer getIntOutComeTypeId() {
        return IntOutComeTypeId;
    }

    public void setIntOutComeTypeId(Integer intOutComeTypeId) {
        IntOutComeTypeId = intOutComeTypeId;
    }

    public Integer getIntBalanceId() {
        return IntBalanceId;
    }

    public void setIntBalanceId(Integer intBalanceId) {
        IntBalanceId = intBalanceId;
    }

    public Integer getIntUserId() {
        return intUserId;
    }

    public void setIntUserId(Integer intUserId) {
        this.intUserId = intUserId;
    }
}

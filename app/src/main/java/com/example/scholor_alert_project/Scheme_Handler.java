package com.example.scholor_alert_project;

public class Scheme_Handler {
    String  schemeName,schemeDesc,org,orgLink;
    String income_req,percentage_req,field,combo;

    public Scheme_Handler() {
    }

    @Override
    public String toString() {
        return "Scheme_Handler{" +
                "schemeName='" + schemeName + '\'' +
                ", schemeDesc='" + schemeDesc + '\'' +
                ", org='" + org + '\'' +
                ", orgLink='" + orgLink + '\'' +
                ", income_req='" + income_req + '\'' +
                ", percentage_req='" + percentage_req + '\'' +
                ", field='" + field + '\'' +

                '}';
    }

    public Scheme_Handler(String schemeName, String schemeDesc, String org, String orgLink, String income_req, String percentage_req, String field) {
        this.schemeName = schemeName;
        this.schemeDesc = schemeDesc;
        this.org = org;
        this.orgLink = orgLink;
        this.income_req = income_req;
        this.percentage_req = percentage_req;
        this.field = field;
     //   this.combo=combo;
    }

//    public String getCombo() {
//        return combo;
//    }

//    public void setCombo(String combo) {
//        this.combo = combo;
//    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeDesc() {
        return schemeDesc;
    }

    public void setSchemeDesc(String schemeDesc) {
        this.schemeDesc = schemeDesc;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getOrgLink() {
        return orgLink;
    }

    public void setOrgLink(String orgLink) {
        this.orgLink = orgLink;
    }

    public String getIncome_req() {
        return income_req;
    }

    public void setIncome_req(String income_req) {
        this.income_req = income_req;
    }

    public String getPercentage_req() {
        return percentage_req;
    }

    public void setPercentage_req(String percentage_req) {
        this.percentage_req = percentage_req;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}

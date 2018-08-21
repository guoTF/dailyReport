package com.seda.dailyReport.model;

import java.io.Serializable;

public class PerformanceResult implements Serializable {
    private String id;

    private String userId;

    private String appraisalMonth;

    private Integer item;

    private Integer result;

    private String resultUserId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppraisalMonth() {
        return appraisalMonth;
    }

    public void setAppraisalMonth(String appraisalMonth) {
        this.appraisalMonth = appraisalMonth;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getResultUserId() {
        return resultUserId;
    }

    public void setResultUserId(String resultUserId) {
        this.resultUserId = resultUserId;
    }
}
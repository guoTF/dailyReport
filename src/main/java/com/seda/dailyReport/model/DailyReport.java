package com.seda.dailyReport.model;

import java.io.Serializable;
import java.util.Date;

public class DailyReport implements Serializable {
    private String id;

    private String userId;

    private Integer reportNum;

    private String reportProjectName;

    private String reportContent;

    private Double reportTime;

    private String reportTimestr;

    private String reportTypeId;

    private String reportGoal;

    private String remark;

    private String reportDay;

    private Date createDate;

    private Date updateDate;

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

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public String getReportProjectName() {
        return reportProjectName;
    }

    public void setReportProjectName(String reportProjectName) {
        this.reportProjectName = reportProjectName;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Double getReportTime() {
        return reportTime;
    }

    public void setReportTime(Double reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportTimestr() {
        return reportTimestr;
    }

    public void setReportTimestr(String reportTimestr) {
        this.reportTimestr = reportTimestr;
    }

    public String getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(String reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public String getReportGoal() {
        return reportGoal;
    }

    public void setReportGoal(String reportGoal) {
        this.reportGoal = reportGoal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReportDay() {
        return reportDay;
    }

    public void setReportDay(String reportDay) {
        this.reportDay = reportDay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
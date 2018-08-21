package com.seda.dailyReport.model;

import java.io.Serializable;

public class PerformanceAppraisal implements Serializable {
    private String id;

    private String userid;

    private String appraisalMonth;

    private String projectName;

    private String taskId;

    private String concreteFunction;

    private String manHour;

    private String performance;

    private String overtim;

    private String createDate;

    private String createBy;

    private String updateDate;

    private String updateBy;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAppraisalMonth() {
        return appraisalMonth;
    }

    public void setAppraisalMonth(String appraisalMonth) {
        this.appraisalMonth = appraisalMonth;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getConcreteFunction() {
        return concreteFunction;
    }

    public void setConcreteFunction(String concreteFunction) {
        this.concreteFunction = concreteFunction;
    }

    public String getManHour() {
        return manHour;
    }

    public void setManHour(String manHour) {
        this.manHour = manHour;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getOvertim() {
        return overtim;
    }

    public void setOvertim(String overtim) {
        this.overtim = overtim;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
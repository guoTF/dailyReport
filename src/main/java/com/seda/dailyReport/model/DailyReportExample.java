package com.seda.dailyReport.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DailyReportExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andReportNumIsNull() {
            addCriterion("report_num is null");
            return (Criteria) this;
        }

        public Criteria andReportNumIsNotNull() {
            addCriterion("report_num is not null");
            return (Criteria) this;
        }

        public Criteria andReportNumEqualTo(Integer value) {
            addCriterion("report_num =", value, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportNumNotEqualTo(Integer value) {
            addCriterion("report_num <>", value, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportNumGreaterThan(Integer value) {
            addCriterion("report_num >", value, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("report_num >=", value, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportNumLessThan(Integer value) {
            addCriterion("report_num <", value, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportNumLessThanOrEqualTo(Integer value) {
            addCriterion("report_num <=", value, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportNumIn(List<Integer> values) {
            addCriterion("report_num in", values, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportNumNotIn(List<Integer> values) {
            addCriterion("report_num not in", values, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportNumBetween(Integer value1, Integer value2) {
            addCriterion("report_num between", value1, value2, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportNumNotBetween(Integer value1, Integer value2) {
            addCriterion("report_num not between", value1, value2, "reportNum");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameIsNull() {
            addCriterion("report_project_name is null");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameIsNotNull() {
            addCriterion("report_project_name is not null");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameEqualTo(String value) {
            addCriterion("report_project_name =", value, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameNotEqualTo(String value) {
            addCriterion("report_project_name <>", value, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameGreaterThan(String value) {
            addCriterion("report_project_name >", value, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("report_project_name >=", value, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameLessThan(String value) {
            addCriterion("report_project_name <", value, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameLessThanOrEqualTo(String value) {
            addCriterion("report_project_name <=", value, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameLike(String value) {
            addCriterion("report_project_name like", value, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameNotLike(String value) {
            addCriterion("report_project_name not like", value, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameIn(List<String> values) {
            addCriterion("report_project_name in", values, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameNotIn(List<String> values) {
            addCriterion("report_project_name not in", values, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameBetween(String value1, String value2) {
            addCriterion("report_project_name between", value1, value2, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportProjectNameNotBetween(String value1, String value2) {
            addCriterion("report_project_name not between", value1, value2, "reportProjectName");
            return (Criteria) this;
        }

        public Criteria andReportContentIsNull() {
            addCriterion("report_content is null");
            return (Criteria) this;
        }

        public Criteria andReportContentIsNotNull() {
            addCriterion("report_content is not null");
            return (Criteria) this;
        }

        public Criteria andReportContentEqualTo(String value) {
            addCriterion("report_content =", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentNotEqualTo(String value) {
            addCriterion("report_content <>", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentGreaterThan(String value) {
            addCriterion("report_content >", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentGreaterThanOrEqualTo(String value) {
            addCriterion("report_content >=", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentLessThan(String value) {
            addCriterion("report_content <", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentLessThanOrEqualTo(String value) {
            addCriterion("report_content <=", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentLike(String value) {
            addCriterion("report_content like", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentNotLike(String value) {
            addCriterion("report_content not like", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentIn(List<String> values) {
            addCriterion("report_content in", values, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentNotIn(List<String> values) {
            addCriterion("report_content not in", values, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentBetween(String value1, String value2) {
            addCriterion("report_content between", value1, value2, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentNotBetween(String value1, String value2) {
            addCriterion("report_content not between", value1, value2, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNull() {
            addCriterion("report_time is null");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNotNull() {
            addCriterion("report_time is not null");
            return (Criteria) this;
        }

        public Criteria andReportTimeEqualTo(Double value) {
            addCriterion("report_time =", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotEqualTo(Double value) {
            addCriterion("report_time <>", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThan(Double value) {
            addCriterion("report_time >", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThanOrEqualTo(Double value) {
            addCriterion("report_time >=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThan(Double value) {
            addCriterion("report_time <", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThanOrEqualTo(Double value) {
            addCriterion("report_time <=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeIn(List<Double> values) {
            addCriterion("report_time in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotIn(List<Double> values) {
            addCriterion("report_time not in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeBetween(Double value1, Double value2) {
            addCriterion("report_time between", value1, value2, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotBetween(Double value1, Double value2) {
            addCriterion("report_time not between", value1, value2, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimestrIsNull() {
            addCriterion("report_timeStr is null");
            return (Criteria) this;
        }

        public Criteria andReportTimestrIsNotNull() {
            addCriterion("report_timeStr is not null");
            return (Criteria) this;
        }

        public Criteria andReportTimestrEqualTo(String value) {
            addCriterion("report_timeStr =", value, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrNotEqualTo(String value) {
            addCriterion("report_timeStr <>", value, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrGreaterThan(String value) {
            addCriterion("report_timeStr >", value, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrGreaterThanOrEqualTo(String value) {
            addCriterion("report_timeStr >=", value, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrLessThan(String value) {
            addCriterion("report_timeStr <", value, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrLessThanOrEqualTo(String value) {
            addCriterion("report_timeStr <=", value, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrLike(String value) {
            addCriterion("report_timeStr like", value, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrNotLike(String value) {
            addCriterion("report_timeStr not like", value, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrIn(List<String> values) {
            addCriterion("report_timeStr in", values, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrNotIn(List<String> values) {
            addCriterion("report_timeStr not in", values, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrBetween(String value1, String value2) {
            addCriterion("report_timeStr between", value1, value2, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTimestrNotBetween(String value1, String value2) {
            addCriterion("report_timeStr not between", value1, value2, "reportTimestr");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdIsNull() {
            addCriterion("report_type_id is null");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdIsNotNull() {
            addCriterion("report_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdEqualTo(String value) {
            addCriterion("report_type_id =", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdNotEqualTo(String value) {
            addCriterion("report_type_id <>", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdGreaterThan(String value) {
            addCriterion("report_type_id >", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("report_type_id >=", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdLessThan(String value) {
            addCriterion("report_type_id <", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdLessThanOrEqualTo(String value) {
            addCriterion("report_type_id <=", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdLike(String value) {
            addCriterion("report_type_id like", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdNotLike(String value) {
            addCriterion("report_type_id not like", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdIn(List<String> values) {
            addCriterion("report_type_id in", values, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdNotIn(List<String> values) {
            addCriterion("report_type_id not in", values, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdBetween(String value1, String value2) {
            addCriterion("report_type_id between", value1, value2, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdNotBetween(String value1, String value2) {
            addCriterion("report_type_id not between", value1, value2, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportGoalIsNull() {
            addCriterion("report_goal is null");
            return (Criteria) this;
        }

        public Criteria andReportGoalIsNotNull() {
            addCriterion("report_goal is not null");
            return (Criteria) this;
        }

        public Criteria andReportGoalEqualTo(String value) {
            addCriterion("report_goal =", value, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalNotEqualTo(String value) {
            addCriterion("report_goal <>", value, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalGreaterThan(String value) {
            addCriterion("report_goal >", value, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalGreaterThanOrEqualTo(String value) {
            addCriterion("report_goal >=", value, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalLessThan(String value) {
            addCriterion("report_goal <", value, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalLessThanOrEqualTo(String value) {
            addCriterion("report_goal <=", value, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalLike(String value) {
            addCriterion("report_goal like", value, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalNotLike(String value) {
            addCriterion("report_goal not like", value, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalIn(List<String> values) {
            addCriterion("report_goal in", values, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalNotIn(List<String> values) {
            addCriterion("report_goal not in", values, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalBetween(String value1, String value2) {
            addCriterion("report_goal between", value1, value2, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andReportGoalNotBetween(String value1, String value2) {
            addCriterion("report_goal not between", value1, value2, "reportGoal");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andReportDayIsNull() {
            addCriterion("report_day is null");
            return (Criteria) this;
        }

        public Criteria andReportDayIsNotNull() {
            addCriterion("report_day is not null");
            return (Criteria) this;
        }

        public Criteria andReportDayEqualTo(String value) {
            addCriterion("report_day =", value, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayNotEqualTo(String value) {
            addCriterion("report_day <>", value, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayGreaterThan(String value) {
            addCriterion("report_day >", value, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayGreaterThanOrEqualTo(String value) {
            addCriterion("report_day >=", value, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayLessThan(String value) {
            addCriterion("report_day <", value, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayLessThanOrEqualTo(String value) {
            addCriterion("report_day <=", value, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayLike(String value) {
            addCriterion("report_day like", value, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayNotLike(String value) {
            addCriterion("report_day not like", value, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayIn(List<String> values) {
            addCriterion("report_day in", values, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayNotIn(List<String> values) {
            addCriterion("report_day not in", values, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayBetween(String value1, String value2) {
            addCriterion("report_day between", value1, value2, "reportDay");
            return (Criteria) this;
        }

        public Criteria andReportDayNotBetween(String value1, String value2) {
            addCriterion("report_day not between", value1, value2, "reportDay");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
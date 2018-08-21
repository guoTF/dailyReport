package com.seda.dailyReport.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DailyPlanExample() {
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

        public Criteria andPlanNumIsNull() {
            addCriterion("plan_num is null");
            return (Criteria) this;
        }

        public Criteria andPlanNumIsNotNull() {
            addCriterion("plan_num is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNumEqualTo(Integer value) {
            addCriterion("plan_num =", value, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanNumNotEqualTo(Integer value) {
            addCriterion("plan_num <>", value, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanNumGreaterThan(Integer value) {
            addCriterion("plan_num >", value, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_num >=", value, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanNumLessThan(Integer value) {
            addCriterion("plan_num <", value, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanNumLessThanOrEqualTo(Integer value) {
            addCriterion("plan_num <=", value, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanNumIn(List<Integer> values) {
            addCriterion("plan_num in", values, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanNumNotIn(List<Integer> values) {
            addCriterion("plan_num not in", values, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanNumBetween(Integer value1, Integer value2) {
            addCriterion("plan_num between", value1, value2, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanNumNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_num not between", value1, value2, "planNum");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameIsNull() {
            addCriterion("plan_project_name is null");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameIsNotNull() {
            addCriterion("plan_project_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameEqualTo(String value) {
            addCriterion("plan_project_name =", value, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameNotEqualTo(String value) {
            addCriterion("plan_project_name <>", value, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameGreaterThan(String value) {
            addCriterion("plan_project_name >", value, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("plan_project_name >=", value, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameLessThan(String value) {
            addCriterion("plan_project_name <", value, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameLessThanOrEqualTo(String value) {
            addCriterion("plan_project_name <=", value, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameLike(String value) {
            addCriterion("plan_project_name like", value, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameNotLike(String value) {
            addCriterion("plan_project_name not like", value, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameIn(List<String> values) {
            addCriterion("plan_project_name in", values, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameNotIn(List<String> values) {
            addCriterion("plan_project_name not in", values, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameBetween(String value1, String value2) {
            addCriterion("plan_project_name between", value1, value2, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanProjectNameNotBetween(String value1, String value2) {
            addCriterion("plan_project_name not between", value1, value2, "planProjectName");
            return (Criteria) this;
        }

        public Criteria andPlanContentIsNull() {
            addCriterion("plan_content is null");
            return (Criteria) this;
        }

        public Criteria andPlanContentIsNotNull() {
            addCriterion("plan_content is not null");
            return (Criteria) this;
        }

        public Criteria andPlanContentEqualTo(String value) {
            addCriterion("plan_content =", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentNotEqualTo(String value) {
            addCriterion("plan_content <>", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentGreaterThan(String value) {
            addCriterion("plan_content >", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentGreaterThanOrEqualTo(String value) {
            addCriterion("plan_content >=", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentLessThan(String value) {
            addCriterion("plan_content <", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentLessThanOrEqualTo(String value) {
            addCriterion("plan_content <=", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentLike(String value) {
            addCriterion("plan_content like", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentNotLike(String value) {
            addCriterion("plan_content not like", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentIn(List<String> values) {
            addCriterion("plan_content in", values, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentNotIn(List<String> values) {
            addCriterion("plan_content not in", values, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentBetween(String value1, String value2) {
            addCriterion("plan_content between", value1, value2, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentNotBetween(String value1, String value2) {
            addCriterion("plan_content not between", value1, value2, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanGoalIsNull() {
            addCriterion("plan_goal is null");
            return (Criteria) this;
        }

        public Criteria andPlanGoalIsNotNull() {
            addCriterion("plan_goal is not null");
            return (Criteria) this;
        }

        public Criteria andPlanGoalEqualTo(String value) {
            addCriterion("plan_goal =", value, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalNotEqualTo(String value) {
            addCriterion("plan_goal <>", value, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalGreaterThan(String value) {
            addCriterion("plan_goal >", value, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalGreaterThanOrEqualTo(String value) {
            addCriterion("plan_goal >=", value, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalLessThan(String value) {
            addCriterion("plan_goal <", value, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalLessThanOrEqualTo(String value) {
            addCriterion("plan_goal <=", value, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalLike(String value) {
            addCriterion("plan_goal like", value, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalNotLike(String value) {
            addCriterion("plan_goal not like", value, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalIn(List<String> values) {
            addCriterion("plan_goal in", values, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalNotIn(List<String> values) {
            addCriterion("plan_goal not in", values, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalBetween(String value1, String value2) {
            addCriterion("plan_goal between", value1, value2, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanGoalNotBetween(String value1, String value2) {
            addCriterion("plan_goal not between", value1, value2, "planGoal");
            return (Criteria) this;
        }

        public Criteria andPlanTimeIsNull() {
            addCriterion("plan_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanTimeIsNotNull() {
            addCriterion("plan_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTimeEqualTo(Double value) {
            addCriterion("plan_time =", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotEqualTo(Double value) {
            addCriterion("plan_time <>", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeGreaterThan(Double value) {
            addCriterion("plan_time >", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeGreaterThanOrEqualTo(Double value) {
            addCriterion("plan_time >=", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeLessThan(Double value) {
            addCriterion("plan_time <", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeLessThanOrEqualTo(Double value) {
            addCriterion("plan_time <=", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeIn(List<Double> values) {
            addCriterion("plan_time in", values, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotIn(List<Double> values) {
            addCriterion("plan_time not in", values, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeBetween(Double value1, Double value2) {
            addCriterion("plan_time between", value1, value2, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotBetween(Double value1, Double value2) {
            addCriterion("plan_time not between", value1, value2, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanDayIsNull() {
            addCriterion("plan_day is null");
            return (Criteria) this;
        }

        public Criteria andPlanDayIsNotNull() {
            addCriterion("plan_day is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDayEqualTo(String value) {
            addCriterion("plan_day =", value, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayNotEqualTo(String value) {
            addCriterion("plan_day <>", value, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayGreaterThan(String value) {
            addCriterion("plan_day >", value, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayGreaterThanOrEqualTo(String value) {
            addCriterion("plan_day >=", value, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayLessThan(String value) {
            addCriterion("plan_day <", value, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayLessThanOrEqualTo(String value) {
            addCriterion("plan_day <=", value, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayLike(String value) {
            addCriterion("plan_day like", value, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayNotLike(String value) {
            addCriterion("plan_day not like", value, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayIn(List<String> values) {
            addCriterion("plan_day in", values, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayNotIn(List<String> values) {
            addCriterion("plan_day not in", values, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayBetween(String value1, String value2) {
            addCriterion("plan_day between", value1, value2, "planDay");
            return (Criteria) this;
        }

        public Criteria andPlanDayNotBetween(String value1, String value2) {
            addCriterion("plan_day not between", value1, value2, "planDay");
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
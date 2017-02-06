package com.rapd4j.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TExceptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TExceptionExample() {
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

        public Criteria andEidIsNull() {
            addCriterion("eid is null");
            return (Criteria) this;
        }

        public Criteria andEidIsNotNull() {
            addCriterion("eid is not null");
            return (Criteria) this;
        }

        public Criteria andEidEqualTo(String value) {
            addCriterion("eid =", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotEqualTo(String value) {
            addCriterion("eid <>", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThan(String value) {
            addCriterion("eid >", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThanOrEqualTo(String value) {
            addCriterion("eid >=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThan(String value) {
            addCriterion("eid <", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThanOrEqualTo(String value) {
            addCriterion("eid <=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLike(String value) {
            addCriterion("eid like", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotLike(String value) {
            addCriterion("eid not like", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidIn(List<String> values) {
            addCriterion("eid in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotIn(List<String> values) {
            addCriterion("eid not in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidBetween(String value1, String value2) {
            addCriterion("eid between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotBetween(String value1, String value2) {
            addCriterion("eid not between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEResourceIsNull() {
            addCriterion("e_resource is null");
            return (Criteria) this;
        }

        public Criteria andEResourceIsNotNull() {
            addCriterion("e_resource is not null");
            return (Criteria) this;
        }

        public Criteria andEResourceEqualTo(String value) {
            addCriterion("e_resource =", value, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceNotEqualTo(String value) {
            addCriterion("e_resource <>", value, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceGreaterThan(String value) {
            addCriterion("e_resource >", value, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceGreaterThanOrEqualTo(String value) {
            addCriterion("e_resource >=", value, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceLessThan(String value) {
            addCriterion("e_resource <", value, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceLessThanOrEqualTo(String value) {
            addCriterion("e_resource <=", value, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceLike(String value) {
            addCriterion("e_resource like", value, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceNotLike(String value) {
            addCriterion("e_resource not like", value, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceIn(List<String> values) {
            addCriterion("e_resource in", values, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceNotIn(List<String> values) {
            addCriterion("e_resource not in", values, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceBetween(String value1, String value2) {
            addCriterion("e_resource between", value1, value2, "eResource");
            return (Criteria) this;
        }

        public Criteria andEResourceNotBetween(String value1, String value2) {
            addCriterion("e_resource not between", value1, value2, "eResource");
            return (Criteria) this;
        }

        public Criteria andECreatedIsNull() {
            addCriterion("e_created is null");
            return (Criteria) this;
        }

        public Criteria andECreatedIsNotNull() {
            addCriterion("e_created is not null");
            return (Criteria) this;
        }

        public Criteria andECreatedEqualTo(Date value) {
            addCriterion("e_created =", value, "eCreated");
            return (Criteria) this;
        }

        public Criteria andECreatedNotEqualTo(Date value) {
            addCriterion("e_created <>", value, "eCreated");
            return (Criteria) this;
        }

        public Criteria andECreatedGreaterThan(Date value) {
            addCriterion("e_created >", value, "eCreated");
            return (Criteria) this;
        }

        public Criteria andECreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("e_created >=", value, "eCreated");
            return (Criteria) this;
        }

        public Criteria andECreatedLessThan(Date value) {
            addCriterion("e_created <", value, "eCreated");
            return (Criteria) this;
        }

        public Criteria andECreatedLessThanOrEqualTo(Date value) {
            addCriterion("e_created <=", value, "eCreated");
            return (Criteria) this;
        }

        public Criteria andECreatedIn(List<Date> values) {
            addCriterion("e_created in", values, "eCreated");
            return (Criteria) this;
        }

        public Criteria andECreatedNotIn(List<Date> values) {
            addCriterion("e_created not in", values, "eCreated");
            return (Criteria) this;
        }

        public Criteria andECreatedBetween(Date value1, Date value2) {
            addCriterion("e_created between", value1, value2, "eCreated");
            return (Criteria) this;
        }

        public Criteria andECreatedNotBetween(Date value1, Date value2) {
            addCriterion("e_created not between", value1, value2, "eCreated");
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
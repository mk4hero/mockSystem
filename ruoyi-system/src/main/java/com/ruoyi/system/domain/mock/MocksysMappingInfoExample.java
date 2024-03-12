package com.ruoyi.system.domain.mock;

import java.util.ArrayList;
import java.util.List;

public class MocksysMappingInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public MocksysMappingInfoExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeIsNull() {
            addCriterion("source_busi_code is null");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeIsNotNull() {
            addCriterion("source_busi_code is not null");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeEqualTo(String value) {
            addCriterion("source_busi_code =", value, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeNotEqualTo(String value) {
            addCriterion("source_busi_code <>", value, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeGreaterThan(String value) {
            addCriterion("source_busi_code >", value, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeGreaterThanOrEqualTo(String value) {
            addCriterion("source_busi_code >=", value, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeLessThan(String value) {
            addCriterion("source_busi_code <", value, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeLessThanOrEqualTo(String value) {
            addCriterion("source_busi_code <=", value, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeLike(String value) {
            addCriterion("source_busi_code like", value, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeNotLike(String value) {
            addCriterion("source_busi_code not like", value, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeIn(List<String> values) {
            addCriterion("source_busi_code in", values, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeNotIn(List<String> values) {
            addCriterion("source_busi_code not in", values, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeBetween(String value1, String value2) {
            addCriterion("source_busi_code between", value1, value2, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceBusiCodeNotBetween(String value1, String value2) {
            addCriterion("source_busi_code not between", value1, value2, "sourceBusiCode");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeIsNull() {
            addCriterion("source_msg_type is null");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeIsNotNull() {
            addCriterion("source_msg_type is not null");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeEqualTo(String value) {
            addCriterion("source_msg_type =", value, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeNotEqualTo(String value) {
            addCriterion("source_msg_type <>", value, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeGreaterThan(String value) {
            addCriterion("source_msg_type >", value, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("source_msg_type >=", value, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeLessThan(String value) {
            addCriterion("source_msg_type <", value, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeLessThanOrEqualTo(String value) {
            addCriterion("source_msg_type <=", value, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeLike(String value) {
            addCriterion("source_msg_type like", value, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeNotLike(String value) {
            addCriterion("source_msg_type not like", value, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeIn(List<String> values) {
            addCriterion("source_msg_type in", values, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeNotIn(List<String> values) {
            addCriterion("source_msg_type not in", values, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeBetween(String value1, String value2) {
            addCriterion("source_msg_type between", value1, value2, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceMsgTypeNotBetween(String value1, String value2) {
            addCriterion("source_msg_type not between", value1, value2, "sourceMsgType");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameIsNull() {
            addCriterion("source_field_Name is null");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameIsNotNull() {
            addCriterion("source_field_Name is not null");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameEqualTo(String value) {
            addCriterion("source_field_Name =", value, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameNotEqualTo(String value) {
            addCriterion("source_field_Name <>", value, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameGreaterThan(String value) {
            addCriterion("source_field_Name >", value, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("source_field_Name >=", value, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameLessThan(String value) {
            addCriterion("source_field_Name <", value, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameLessThanOrEqualTo(String value) {
            addCriterion("source_field_Name <=", value, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameLike(String value) {
            addCriterion("source_field_Name like", value, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameNotLike(String value) {
            addCriterion("source_field_Name not like", value, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameIn(List<String> values) {
            addCriterion("source_field_Name in", values, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameNotIn(List<String> values) {
            addCriterion("source_field_Name not in", values, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameBetween(String value1, String value2) {
            addCriterion("source_field_Name between", value1, value2, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNameNotBetween(String value1, String value2) {
            addCriterion("source_field_Name not between", value1, value2, "sourceFieldName");
            return (Criteria) this;
        }

        public Criteria andSourceNumberIsNull() {
            addCriterion("source_number is null");
            return (Criteria) this;
        }

        public Criteria andSourceNumberIsNotNull() {
            addCriterion("source_number is not null");
            return (Criteria) this;
        }

        public Criteria andSourceNumberEqualTo(Integer value) {
            addCriterion("source_number =", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberNotEqualTo(Integer value) {
            addCriterion("source_number <>", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberGreaterThan(Integer value) {
            addCriterion("source_number >", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_number >=", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberLessThan(Integer value) {
            addCriterion("source_number <", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberLessThanOrEqualTo(Integer value) {
            addCriterion("source_number <=", value, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberIn(List<Integer> values) {
            addCriterion("source_number in", values, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberNotIn(List<Integer> values) {
            addCriterion("source_number not in", values, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberBetween(Integer value1, Integer value2) {
            addCriterion("source_number between", value1, value2, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourceNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("source_number not between", value1, value2, "sourceNumber");
            return (Criteria) this;
        }

        public Criteria andSourcePathIsNull() {
            addCriterion("source_path is null");
            return (Criteria) this;
        }

        public Criteria andSourcePathIsNotNull() {
            addCriterion("source_path is not null");
            return (Criteria) this;
        }

        public Criteria andSourcePathEqualTo(String value) {
            addCriterion("source_path =", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotEqualTo(String value) {
            addCriterion("source_path <>", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathGreaterThan(String value) {
            addCriterion("source_path >", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathGreaterThanOrEqualTo(String value) {
            addCriterion("source_path >=", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathLessThan(String value) {
            addCriterion("source_path <", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathLessThanOrEqualTo(String value) {
            addCriterion("source_path <=", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathLike(String value) {
            addCriterion("source_path like", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotLike(String value) {
            addCriterion("source_path not like", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathIn(List<String> values) {
            addCriterion("source_path in", values, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotIn(List<String> values) {
            addCriterion("source_path not in", values, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathBetween(String value1, String value2) {
            addCriterion("source_path between", value1, value2, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotBetween(String value1, String value2) {
            addCriterion("source_path not between", value1, value2, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourceSignIsNull() {
            addCriterion("source_sign is null");
            return (Criteria) this;
        }

        public Criteria andSourceSignIsNotNull() {
            addCriterion("source_sign is not null");
            return (Criteria) this;
        }

        public Criteria andSourceSignEqualTo(String value) {
            addCriterion("source_sign =", value, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignNotEqualTo(String value) {
            addCriterion("source_sign <>", value, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignGreaterThan(String value) {
            addCriterion("source_sign >", value, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignGreaterThanOrEqualTo(String value) {
            addCriterion("source_sign >=", value, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignLessThan(String value) {
            addCriterion("source_sign <", value, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignLessThanOrEqualTo(String value) {
            addCriterion("source_sign <=", value, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignLike(String value) {
            addCriterion("source_sign like", value, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignNotLike(String value) {
            addCriterion("source_sign not like", value, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignIn(List<String> values) {
            addCriterion("source_sign in", values, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignNotIn(List<String> values) {
            addCriterion("source_sign not in", values, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignBetween(String value1, String value2) {
            addCriterion("source_sign between", value1, value2, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceSignNotBetween(String value1, String value2) {
            addCriterion("source_sign not between", value1, value2, "sourceSign");
            return (Criteria) this;
        }

        public Criteria andSourceLocationIsNull() {
            addCriterion("source_location is null");
            return (Criteria) this;
        }

        public Criteria andSourceLocationIsNotNull() {
            addCriterion("source_location is not null");
            return (Criteria) this;
        }

        public Criteria andSourceLocationEqualTo(Integer value) {
            addCriterion("source_location =", value, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLocationNotEqualTo(Integer value) {
            addCriterion("source_location <>", value, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLocationGreaterThan(Integer value) {
            addCriterion("source_location >", value, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLocationGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_location >=", value, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLocationLessThan(Integer value) {
            addCriterion("source_location <", value, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLocationLessThanOrEqualTo(Integer value) {
            addCriterion("source_location <=", value, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLocationIn(List<Integer> values) {
            addCriterion("source_location in", values, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLocationNotIn(List<Integer> values) {
            addCriterion("source_location not in", values, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLocationBetween(Integer value1, Integer value2) {
            addCriterion("source_location between", value1, value2, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLocationNotBetween(Integer value1, Integer value2) {
            addCriterion("source_location not between", value1, value2, "sourceLocation");
            return (Criteria) this;
        }

        public Criteria andSourceLengthIsNull() {
            addCriterion("source_length is null");
            return (Criteria) this;
        }

        public Criteria andSourceLengthIsNotNull() {
            addCriterion("source_length is not null");
            return (Criteria) this;
        }

        public Criteria andSourceLengthEqualTo(Integer value) {
            addCriterion("source_length =", value, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLengthNotEqualTo(Integer value) {
            addCriterion("source_length <>", value, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLengthGreaterThan(Integer value) {
            addCriterion("source_length >", value, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_length >=", value, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLengthLessThan(Integer value) {
            addCriterion("source_length <", value, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLengthLessThanOrEqualTo(Integer value) {
            addCriterion("source_length <=", value, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLengthIn(List<Integer> values) {
            addCriterion("source_length in", values, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLengthNotIn(List<Integer> values) {
            addCriterion("source_length not in", values, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLengthBetween(Integer value1, Integer value2) {
            addCriterion("source_length between", value1, value2, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("source_length not between", value1, value2, "sourceLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountIsNull() {
            addCriterion("source_loop_count is null");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountIsNotNull() {
            addCriterion("source_loop_count is not null");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountEqualTo(Integer value) {
            addCriterion("source_loop_count =", value, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountNotEqualTo(Integer value) {
            addCriterion("source_loop_count <>", value, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountGreaterThan(Integer value) {
            addCriterion("source_loop_count >", value, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_loop_count >=", value, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountLessThan(Integer value) {
            addCriterion("source_loop_count <", value, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountLessThanOrEqualTo(Integer value) {
            addCriterion("source_loop_count <=", value, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountIn(List<Integer> values) {
            addCriterion("source_loop_count in", values, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountNotIn(List<Integer> values) {
            addCriterion("source_loop_count not in", values, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountBetween(Integer value1, Integer value2) {
            addCriterion("source_loop_count between", value1, value2, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopCountNotBetween(Integer value1, Integer value2) {
            addCriterion("source_loop_count not between", value1, value2, "sourceLoopCount");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthIsNull() {
            addCriterion("source_loop_length is null");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthIsNotNull() {
            addCriterion("source_loop_length is not null");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthEqualTo(Integer value) {
            addCriterion("source_loop_length =", value, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthNotEqualTo(Integer value) {
            addCriterion("source_loop_length <>", value, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthGreaterThan(Integer value) {
            addCriterion("source_loop_length >", value, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_loop_length >=", value, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthLessThan(Integer value) {
            addCriterion("source_loop_length <", value, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthLessThanOrEqualTo(Integer value) {
            addCriterion("source_loop_length <=", value, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthIn(List<Integer> values) {
            addCriterion("source_loop_length in", values, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthNotIn(List<Integer> values) {
            addCriterion("source_loop_length not in", values, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthBetween(Integer value1, Integer value2) {
            addCriterion("source_loop_length between", value1, value2, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andSourceLoopLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("source_loop_length not between", value1, value2, "sourceLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeIsNull() {
            addCriterion("target_busi_code is null");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeIsNotNull() {
            addCriterion("target_busi_code is not null");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeEqualTo(String value) {
            addCriterion("target_busi_code =", value, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeNotEqualTo(String value) {
            addCriterion("target_busi_code <>", value, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeGreaterThan(String value) {
            addCriterion("target_busi_code >", value, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeGreaterThanOrEqualTo(String value) {
            addCriterion("target_busi_code >=", value, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeLessThan(String value) {
            addCriterion("target_busi_code <", value, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeLessThanOrEqualTo(String value) {
            addCriterion("target_busi_code <=", value, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeLike(String value) {
            addCriterion("target_busi_code like", value, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeNotLike(String value) {
            addCriterion("target_busi_code not like", value, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeIn(List<String> values) {
            addCriterion("target_busi_code in", values, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeNotIn(List<String> values) {
            addCriterion("target_busi_code not in", values, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeBetween(String value1, String value2) {
            addCriterion("target_busi_code between", value1, value2, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetBusiCodeNotBetween(String value1, String value2) {
            addCriterion("target_busi_code not between", value1, value2, "targetBusiCode");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeIsNull() {
            addCriterion("target_msg_type is null");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeIsNotNull() {
            addCriterion("target_msg_type is not null");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeEqualTo(String value) {
            addCriterion("target_msg_type =", value, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeNotEqualTo(String value) {
            addCriterion("target_msg_type <>", value, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeGreaterThan(String value) {
            addCriterion("target_msg_type >", value, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("target_msg_type >=", value, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeLessThan(String value) {
            addCriterion("target_msg_type <", value, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeLessThanOrEqualTo(String value) {
            addCriterion("target_msg_type <=", value, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeLike(String value) {
            addCriterion("target_msg_type like", value, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeNotLike(String value) {
            addCriterion("target_msg_type not like", value, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeIn(List<String> values) {
            addCriterion("target_msg_type in", values, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeNotIn(List<String> values) {
            addCriterion("target_msg_type not in", values, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeBetween(String value1, String value2) {
            addCriterion("target_msg_type between", value1, value2, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetMsgTypeNotBetween(String value1, String value2) {
            addCriterion("target_msg_type not between", value1, value2, "targetMsgType");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameIsNull() {
            addCriterion("target_field_Name is null");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameIsNotNull() {
            addCriterion("target_field_Name is not null");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameEqualTo(String value) {
            addCriterion("target_field_Name =", value, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameNotEqualTo(String value) {
            addCriterion("target_field_Name <>", value, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameGreaterThan(String value) {
            addCriterion("target_field_Name >", value, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("target_field_Name >=", value, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameLessThan(String value) {
            addCriterion("target_field_Name <", value, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameLessThanOrEqualTo(String value) {
            addCriterion("target_field_Name <=", value, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameLike(String value) {
            addCriterion("target_field_Name like", value, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameNotLike(String value) {
            addCriterion("target_field_Name not like", value, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameIn(List<String> values) {
            addCriterion("target_field_Name in", values, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameNotIn(List<String> values) {
            addCriterion("target_field_Name not in", values, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameBetween(String value1, String value2) {
            addCriterion("target_field_Name between", value1, value2, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNameNotBetween(String value1, String value2) {
            addCriterion("target_field_Name not between", value1, value2, "targetFieldName");
            return (Criteria) this;
        }

        public Criteria andTargetNumberIsNull() {
            addCriterion("target_number is null");
            return (Criteria) this;
        }

        public Criteria andTargetNumberIsNotNull() {
            addCriterion("target_number is not null");
            return (Criteria) this;
        }

        public Criteria andTargetNumberEqualTo(Integer value) {
            addCriterion("target_number =", value, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetNumberNotEqualTo(Integer value) {
            addCriterion("target_number <>", value, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetNumberGreaterThan(Integer value) {
            addCriterion("target_number >", value, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("target_number >=", value, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetNumberLessThan(Integer value) {
            addCriterion("target_number <", value, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetNumberLessThanOrEqualTo(Integer value) {
            addCriterion("target_number <=", value, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetNumberIn(List<Integer> values) {
            addCriterion("target_number in", values, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetNumberNotIn(List<Integer> values) {
            addCriterion("target_number not in", values, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetNumberBetween(Integer value1, Integer value2) {
            addCriterion("target_number between", value1, value2, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("target_number not between", value1, value2, "targetNumber");
            return (Criteria) this;
        }

        public Criteria andTargetPathIsNull() {
            addCriterion("target_path is null");
            return (Criteria) this;
        }

        public Criteria andTargetPathIsNotNull() {
            addCriterion("target_path is not null");
            return (Criteria) this;
        }

        public Criteria andTargetPathEqualTo(String value) {
            addCriterion("target_path =", value, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathNotEqualTo(String value) {
            addCriterion("target_path <>", value, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathGreaterThan(String value) {
            addCriterion("target_path >", value, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathGreaterThanOrEqualTo(String value) {
            addCriterion("target_path >=", value, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathLessThan(String value) {
            addCriterion("target_path <", value, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathLessThanOrEqualTo(String value) {
            addCriterion("target_path <=", value, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathLike(String value) {
            addCriterion("target_path like", value, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathNotLike(String value) {
            addCriterion("target_path not like", value, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathIn(List<String> values) {
            addCriterion("target_path in", values, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathNotIn(List<String> values) {
            addCriterion("target_path not in", values, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathBetween(String value1, String value2) {
            addCriterion("target_path between", value1, value2, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetPathNotBetween(String value1, String value2) {
            addCriterion("target_path not between", value1, value2, "targetPath");
            return (Criteria) this;
        }

        public Criteria andTargetSignIsNull() {
            addCriterion("target_sign is null");
            return (Criteria) this;
        }

        public Criteria andTargetSignIsNotNull() {
            addCriterion("target_sign is not null");
            return (Criteria) this;
        }

        public Criteria andTargetSignEqualTo(String value) {
            addCriterion("target_sign =", value, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignNotEqualTo(String value) {
            addCriterion("target_sign <>", value, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignGreaterThan(String value) {
            addCriterion("target_sign >", value, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignGreaterThanOrEqualTo(String value) {
            addCriterion("target_sign >=", value, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignLessThan(String value) {
            addCriterion("target_sign <", value, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignLessThanOrEqualTo(String value) {
            addCriterion("target_sign <=", value, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignLike(String value) {
            addCriterion("target_sign like", value, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignNotLike(String value) {
            addCriterion("target_sign not like", value, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignIn(List<String> values) {
            addCriterion("target_sign in", values, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignNotIn(List<String> values) {
            addCriterion("target_sign not in", values, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignBetween(String value1, String value2) {
            addCriterion("target_sign between", value1, value2, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetSignNotBetween(String value1, String value2) {
            addCriterion("target_sign not between", value1, value2, "targetSign");
            return (Criteria) this;
        }

        public Criteria andTargetLocationIsNull() {
            addCriterion("target_location is null");
            return (Criteria) this;
        }

        public Criteria andTargetLocationIsNotNull() {
            addCriterion("target_location is not null");
            return (Criteria) this;
        }

        public Criteria andTargetLocationEqualTo(Integer value) {
            addCriterion("target_location =", value, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLocationNotEqualTo(Integer value) {
            addCriterion("target_location <>", value, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLocationGreaterThan(Integer value) {
            addCriterion("target_location >", value, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLocationGreaterThanOrEqualTo(Integer value) {
            addCriterion("target_location >=", value, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLocationLessThan(Integer value) {
            addCriterion("target_location <", value, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLocationLessThanOrEqualTo(Integer value) {
            addCriterion("target_location <=", value, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLocationIn(List<Integer> values) {
            addCriterion("target_location in", values, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLocationNotIn(List<Integer> values) {
            addCriterion("target_location not in", values, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLocationBetween(Integer value1, Integer value2) {
            addCriterion("target_location between", value1, value2, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLocationNotBetween(Integer value1, Integer value2) {
            addCriterion("target_location not between", value1, value2, "targetLocation");
            return (Criteria) this;
        }

        public Criteria andTargetLengthIsNull() {
            addCriterion("target_length is null");
            return (Criteria) this;
        }

        public Criteria andTargetLengthIsNotNull() {
            addCriterion("target_length is not null");
            return (Criteria) this;
        }

        public Criteria andTargetLengthEqualTo(Integer value) {
            addCriterion("target_length =", value, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLengthNotEqualTo(Integer value) {
            addCriterion("target_length <>", value, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLengthGreaterThan(Integer value) {
            addCriterion("target_length >", value, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("target_length >=", value, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLengthLessThan(Integer value) {
            addCriterion("target_length <", value, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLengthLessThanOrEqualTo(Integer value) {
            addCriterion("target_length <=", value, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLengthIn(List<Integer> values) {
            addCriterion("target_length in", values, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLengthNotIn(List<Integer> values) {
            addCriterion("target_length not in", values, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLengthBetween(Integer value1, Integer value2) {
            addCriterion("target_length between", value1, value2, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("target_length not between", value1, value2, "targetLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountIsNull() {
            addCriterion("target_loop_count is null");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountIsNotNull() {
            addCriterion("target_loop_count is not null");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountEqualTo(Integer value) {
            addCriterion("target_loop_count =", value, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountNotEqualTo(Integer value) {
            addCriterion("target_loop_count <>", value, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountGreaterThan(Integer value) {
            addCriterion("target_loop_count >", value, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("target_loop_count >=", value, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountLessThan(Integer value) {
            addCriterion("target_loop_count <", value, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountLessThanOrEqualTo(Integer value) {
            addCriterion("target_loop_count <=", value, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountIn(List<Integer> values) {
            addCriterion("target_loop_count in", values, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountNotIn(List<Integer> values) {
            addCriterion("target_loop_count not in", values, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountBetween(Integer value1, Integer value2) {
            addCriterion("target_loop_count between", value1, value2, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopCountNotBetween(Integer value1, Integer value2) {
            addCriterion("target_loop_count not between", value1, value2, "targetLoopCount");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthIsNull() {
            addCriterion("target_loop_length is null");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthIsNotNull() {
            addCriterion("target_loop_length is not null");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthEqualTo(Integer value) {
            addCriterion("target_loop_length =", value, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthNotEqualTo(Integer value) {
            addCriterion("target_loop_length <>", value, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthGreaterThan(Integer value) {
            addCriterion("target_loop_length >", value, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("target_loop_length >=", value, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthLessThan(Integer value) {
            addCriterion("target_loop_length <", value, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthLessThanOrEqualTo(Integer value) {
            addCriterion("target_loop_length <=", value, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthIn(List<Integer> values) {
            addCriterion("target_loop_length in", values, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthNotIn(List<Integer> values) {
            addCriterion("target_loop_length not in", values, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthBetween(Integer value1, Integer value2) {
            addCriterion("target_loop_length between", value1, value2, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andTargetLoopLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("target_loop_length not between", value1, value2, "targetLoopLength");
            return (Criteria) this;
        }

        public Criteria andActionIsNull() {
            addCriterion("`action` is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("`action` is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addCriterion("`action` =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addCriterion("`action` <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addCriterion("`action` >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addCriterion("`action` >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addCriterion("`action` <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addCriterion("`action` <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLike(String value) {
            addCriterion("`action` like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotLike(String value) {
            addCriterion("`action` not like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addCriterion("`action` in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addCriterion("`action` not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addCriterion("`action` between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addCriterion("`action` not between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andOperateIsNull() {
            addCriterion("operate is null");
            return (Criteria) this;
        }

        public Criteria andOperateIsNotNull() {
            addCriterion("operate is not null");
            return (Criteria) this;
        }

        public Criteria andOperateEqualTo(String value) {
            addCriterion("operate =", value, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateNotEqualTo(String value) {
            addCriterion("operate <>", value, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateGreaterThan(String value) {
            addCriterion("operate >", value, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateGreaterThanOrEqualTo(String value) {
            addCriterion("operate >=", value, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateLessThan(String value) {
            addCriterion("operate <", value, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateLessThanOrEqualTo(String value) {
            addCriterion("operate <=", value, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateLike(String value) {
            addCriterion("operate like", value, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateNotLike(String value) {
            addCriterion("operate not like", value, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateIn(List<String> values) {
            addCriterion("operate in", values, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateNotIn(List<String> values) {
            addCriterion("operate not in", values, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateBetween(String value1, String value2) {
            addCriterion("operate between", value1, value2, "operate");
            return (Criteria) this;
        }

        public Criteria andOperateNotBetween(String value1, String value2) {
            addCriterion("operate not between", value1, value2, "operate");
            return (Criteria) this;
        }

        public Criteria andParameterIsNull() {
            addCriterion("`parameter` is null");
            return (Criteria) this;
        }

        public Criteria andParameterIsNotNull() {
            addCriterion("`parameter` is not null");
            return (Criteria) this;
        }

        public Criteria andParameterEqualTo(String value) {
            addCriterion("`parameter` =", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(String value) {
            addCriterion("`parameter` <>", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(String value) {
            addCriterion("`parameter` >", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(String value) {
            addCriterion("`parameter` >=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(String value) {
            addCriterion("`parameter` <", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(String value) {
            addCriterion("`parameter` <=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLike(String value) {
            addCriterion("`parameter` like", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotLike(String value) {
            addCriterion("`parameter` not like", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterIn(List<String> values) {
            addCriterion("`parameter` in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotIn(List<String> values) {
            addCriterion("`parameter` not in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterBetween(String value1, String value2) {
            addCriterion("`parameter` between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotBetween(String value1, String value2) {
            addCriterion("`parameter` not between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andRemark1IsNull() {
            addCriterion("remark1 is null");
            return (Criteria) this;
        }

        public Criteria andRemark1IsNotNull() {
            addCriterion("remark1 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark1EqualTo(String value) {
            addCriterion("remark1 =", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotEqualTo(String value) {
            addCriterion("remark1 <>", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1GreaterThan(String value) {
            addCriterion("remark1 >", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1GreaterThanOrEqualTo(String value) {
            addCriterion("remark1 >=", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1LessThan(String value) {
            addCriterion("remark1 <", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1LessThanOrEqualTo(String value) {
            addCriterion("remark1 <=", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1Like(String value) {
            addCriterion("remark1 like", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotLike(String value) {
            addCriterion("remark1 not like", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1In(List<String> values) {
            addCriterion("remark1 in", values, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotIn(List<String> values) {
            addCriterion("remark1 not in", values, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1Between(String value1, String value2) {
            addCriterion("remark1 between", value1, value2, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotBetween(String value1, String value2) {
            addCriterion("remark1 not between", value1, value2, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNull() {
            addCriterion("remark2 is null");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNotNull() {
            addCriterion("remark2 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark2EqualTo(String value) {
            addCriterion("remark2 =", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotEqualTo(String value) {
            addCriterion("remark2 <>", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThan(String value) {
            addCriterion("remark2 >", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThanOrEqualTo(String value) {
            addCriterion("remark2 >=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThan(String value) {
            addCriterion("remark2 <", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThanOrEqualTo(String value) {
            addCriterion("remark2 <=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Like(String value) {
            addCriterion("remark2 like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotLike(String value) {
            addCriterion("remark2 not like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2In(List<String> values) {
            addCriterion("remark2 in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotIn(List<String> values) {
            addCriterion("remark2 not in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Between(String value1, String value2) {
            addCriterion("remark2 between", value1, value2, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotBetween(String value1, String value2) {
            addCriterion("remark2 not between", value1, value2, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark3IsNull() {
            addCriterion("remark3 is null");
            return (Criteria) this;
        }

        public Criteria andRemark3IsNotNull() {
            addCriterion("remark3 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark3EqualTo(String value) {
            addCriterion("remark3 =", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotEqualTo(String value) {
            addCriterion("remark3 <>", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3GreaterThan(String value) {
            addCriterion("remark3 >", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3GreaterThanOrEqualTo(String value) {
            addCriterion("remark3 >=", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3LessThan(String value) {
            addCriterion("remark3 <", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3LessThanOrEqualTo(String value) {
            addCriterion("remark3 <=", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3Like(String value) {
            addCriterion("remark3 like", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotLike(String value) {
            addCriterion("remark3 not like", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3In(List<String> values) {
            addCriterion("remark3 in", values, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotIn(List<String> values) {
            addCriterion("remark3 not in", values, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3Between(String value1, String value2) {
            addCriterion("remark3 between", value1, value2, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotBetween(String value1, String value2) {
            addCriterion("remark3 not between", value1, value2, "remark3");
            return (Criteria) this;
        }
    }

    /**
     */
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
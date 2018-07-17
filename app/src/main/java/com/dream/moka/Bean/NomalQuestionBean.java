package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13 0013.
 * 常见问题
 */

public class NomalQuestionBean {

    /**
     * question : [{"updateDate":"2017-11-06 10:47:05","answer":"在订单完成后的15个工作日内","question":"开纸质发票是什么时候寄出？","id":"07cbc995c5444bb69e2157be9d1bb36a","isNewRecord":false,"sort":2,"delFlag":"0","questionTypeId":"6bfa573d0415460aad1fe18d496b4bf2","remarks":"","createDate":"2017-11-06 10:46:58","status":0},{"updateDate":"2017-11-06 10:45:50","answer":"在确认下单的时候需要选择申请发票的类型，分为电子发票和增值税专用发票","question":"发票怎么开？","id":"8e7cd6b672b640bf89bac26b9a156164","isNewRecord":false,"sort":1,"delFlag":"0","questionTypeId":"6bfa573d0415460aad1fe18d496b4bf2","remarks":"","createDate":"2017-11-06 10:45:50","status":0}]
     * questionType : {"updateDate":"2018-02-27 11:38:02","name":"发票","id":"6bfa573d0415460aad1fe18d496b4bf2","isNewRecord":false,"delFlag":"0","remarks":"","createDate":"2017-11-06 10:33:47","status":1}
     */

    private QuestionTypeBean questionType;
    private List<QuestionBean> question;

    public QuestionTypeBean getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionTypeBean questionType) {
        this.questionType = questionType;
    }

    public List<QuestionBean> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionBean> question) {
        this.question = question;
    }

    public static class QuestionTypeBean {
        /**
         * updateDate : 2018-02-27 11:38:02
         * name : 发票
         * id : 6bfa573d0415460aad1fe18d496b4bf2
         * isNewRecord : false
         * delFlag : 0
         * remarks :
         * createDate : 2017-11-06 10:33:47
         * status : 1
         */

        private String updateDate;
        private String name;
        private String id;
        private boolean isNewRecord;
        private String delFlag;
        private String remarks;
        private String createDate;
        private String status;

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class QuestionBean {
        /**
         * updateDate : 2017-11-06 10:47:05
         * answer : 在订单完成后的15个工作日内
         * question : 开纸质发票是什么时候寄出？
         * id : 07cbc995c5444bb69e2157be9d1bb36a
         * isNewRecord : false
         * sort : 2
         * delFlag : 0
         * questionTypeId : 6bfa573d0415460aad1fe18d496b4bf2
         * remarks :
         * createDate : 2017-11-06 10:46:58
         * status : 0
         */

        private String updateDate;
        private String answer;
        private String question;
        private String id;
        private boolean isNewRecord;
        private String sort;
        private String delFlag;
        private String questionTypeId;
        private String remarks;
        private String createDate;
        private String status;

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getQuestionTypeId() {
            return questionTypeId;
        }

        public void setQuestionTypeId(String questionTypeId) {
            this.questionTypeId = questionTypeId;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

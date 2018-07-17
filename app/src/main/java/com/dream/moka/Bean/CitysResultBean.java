package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class CitysResultBean {

    private List<ListHotBean> listHot;
    private List<ListBean> list;

    public List<ListHotBean> getListHot() {
        return listHot;
    }

    public void setListHot(List<ListHotBean> listHot) {
        this.listHot = listHot;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListHotBean {
        /**
         * id : 1
         * status : 0
         * initials : s
         * name : 深圳
         * isNewRecord : false
         * remarks :
         * isHot : 1
         * createDate : 2018-01-11 14:25:17
         * updateDate :
         * delFlag : 0
         */

        private String id;
        private int status;
        private String initials;
        private String name;
        private boolean isNewRecord;
        private String remarks;
        private String isHot;
        private String createDate;
        private String updateDate;
        private String delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getInitials() {
            return initials;
        }

        public void setInitials(String initials) {
            this.initials = initials;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getIsHot() {
            return isHot;
        }

        public void setIsHot(String isHot) {
            this.isHot = isHot;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }

    public static class ListBean {
        /**
         * id : 1
         * status : 0
         * initials : s
         * name : 深圳
         * isNewRecord : false
         * remarks :
         * isHot : 1
         * createDate : 2018-01-11 14:25:17
         * updateDate :
         * delFlag : 0
         */

        private String id;
        private int status;
        private String initials;
        private String name;
        private boolean isNewRecord;
        private String remarks;
        private String isHot;
        private String createDate;
        private String updateDate;
        private String delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getInitials() {
            return initials;
        }

        public void setInitials(String initials) {
            this.initials = initials;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getIsHot() {
            return isHot;
        }

        public void setIsHot(String isHot) {
            this.isHot = isHot;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}

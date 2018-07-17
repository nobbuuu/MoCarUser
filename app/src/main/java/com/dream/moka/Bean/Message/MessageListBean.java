package com.dream.moka.Bean.Message;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1 0001.
 */
public class MessageListBean {

    /**
     * startItem : 0
     * total : 7
     * pageSize : 10
     * list : [{"id":"4f72563838534b56932e67dfc2c4dcfb","messageTitle":"尊敬的用户、您有一个保养推单、请查看！","status":0,"isNewRecord":false,"remarks":"","messageType":1,"messageId":"61d5734fa5164d349b93da4ddcd64b50","createDate":"2018-01-29 11:29:24","updateDate":"2018-01-29 11:29:24","delFlag":"0"},{"id":"e822d9162017485aa9ea6a2bb9b7304a","messageTitle":"尊敬的用户、您有一个保养推单、请查看！","status":0,"isNewRecord":false,"remarks":"","messageType":1,"messageId":"5bec4b7b38344c9da02a1e2391dd8764","createDate":"2018-01-29 11:28:41","updateDate":"2018-01-29 11:28:41","delFlag":"0"},{"id":"5a56c9753ef043799abb627b04a2f036","messageTitle":"尊敬的用户、您有一个保养推单、请查看！","status":0,"isNewRecord":false,"remarks":"","messageType":1,"messageId":"403d9837d79b4d06928d38e8eae6b164","createDate":"2018-01-29 11:24:27","updateDate":"2018-01-29 11:24:27","delFlag":"0"},{"id":"2b30766ebc144e869c7bdcaed0d56b38","messageTitle":"尊敬的用户、您有一个保养推单、请查看！","status":0,"isNewRecord":false,"remarks":"","messageType":1,"messageId":"e14ccb5ce41c422ba5d3eb3eaacb0641","createDate":"2018-01-29 11:21:47","updateDate":"2018-01-29 11:21:47","delFlag":"0"},{"id":"66a6eb6f754d481eac673f75e347a4ee","messageTitle":"尊敬的用户、您有一个保养推单、请查看！","status":0,"isNewRecord":false,"remarks":"","messageType":1,"messageId":"34f35586a8504434af1fc31dcf33b08d","createDate":"2018-01-29 11:19:57","updateDate":"2018-01-29 11:19:57","delFlag":"0"},{"id":"bf084d45bec945f182e5d157032e352e","messageTitle":"尊敬的用户、您有一个保养推单、请查看！","status":0,"isNewRecord":false,"remarks":"","messageType":1,"messageId":"61c2a3107d324c45b69320a8569a6339","createDate":"2018-01-29 11:14:44","updateDate":"2018-01-29 11:14:44","delFlag":"0"},{"id":"94eb3d901b7544eda99b2d7763f90a58","messageTitle":"尊敬的用户、您有一个保养推单、请查看！","status":0,"isNewRecord":false,"remarks":"","messageType":1,"messageId":"9e9e0ca2dfe24fa7b6711504a4f9d4f4","createDate":"2018-01-29 11:13:17","updateDate":"2018-01-29 11:13:17","delFlag":"0"}]
     * totalPage : 1
     * pageNum : 1
     */

    private int startItem;
    private int total;
    private int pageSize;
    private int totalPage;
    private int pageNum;
    private List<ListBean> list;

    public int getStartItem() {
        return startItem;
    }

    public void setStartItem(int startItem) {
        this.startItem = startItem;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 4f72563838534b56932e67dfc2c4dcfb
         * messageTitle : 尊敬的用户、您有一个保养推单、请查看！
         * status : 0
         * isNewRecord : false
         * remarks :
         * messageType : 1
         * messageId : 61d5734fa5164d349b93da4ddcd64b50
         * createDate : 2018-01-29 11:29:24
         * updateDate : 2018-01-29 11:29:24
         * delFlag : 0
         */

        private String id;
        private String messageTitle;
        private String status;
        private boolean isNewRecord;
        private String remarks;
        private String messageType;
        private String messageId;
        private String createDate;
        private String updateDate;
        private String delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessageTitle() {
            return messageTitle;
        }

        public void setMessageTitle(String messageTitle) {
            this.messageTitle = messageTitle;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
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

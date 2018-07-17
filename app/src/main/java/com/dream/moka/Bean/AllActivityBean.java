package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13 0013.
 * 分页获取所有活动
 */

public class AllActivityBean {
    /**
     * total : 2
     * startItem : 0
     * totalPage : 1
     * pageSize : 10
     * list : [{"updateDate":"2017-11-06 18:34:05","isNewRecord":false,"pic":"/mocar/userfiles/1/_thumbs/images/market/activity/2017/11/bglogo.png","delFlag":"0","title":"最新图文详情","type":1,"content":"&lt;p&gt;\r\n\t&lt;img alt=&quot;&quot; src=&quot;/mocar/userfiles/1/images/car/carBrand/2017/11/timg%20(1).jpg&quot; style=&quot;width: 759px; height: 287px;&quot; /&gt;&lt;/p&gt;","linkAddr":"","id":"a17383e00d66445aa0870c2bb56e5621","isHot":1,"remarks":"","createDate":"2017-11-06 18:34:05","status":1},{"updateDate":"2017-11-06 18:32:14","isNewRecord":false,"pic":"/mocar/userfiles/1/_thumbs/images/market/activity/2017/11/bglogo.png","delFlag":"0","title":"","type":0,"content":"","linkAddr":"www.baidu.com","id":"302d97101cf64690be516bd8a0401bd4","isHot":0,"remarks":"","createDate":"2017-11-06 18:32:14","status":1}]
     * pageNum : 1
     */

    private int total;
    private int startItem;
    private int totalPage;
    private int pageSize;
    private int pageNum;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartItem() {
        return startItem;
    }

    public void setStartItem(int startItem) {
        this.startItem = startItem;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
         * updateDate : 2017-11-06 18:34:05
         * isNewRecord : false
         * pic : /mocar/userfiles/1/_thumbs/images/market/activity/2017/11/bglogo.png
         * delFlag : 0
         * title : 最新图文详情
         * type : 1
         * content : &lt;p&gt;
         &lt;img alt=&quot;&quot; src=&quot;/mocar/userfiles/1/images/car/carBrand/2017/11/timg%20(1).jpg&quot; style=&quot;width: 759px; height: 287px;&quot; /&gt;&lt;/p&gt;
         * linkAddr :
         * id : a17383e00d66445aa0870c2bb56e5621
         * isHot : 1
         * remarks :
         * createDate : 2017-11-06 18:34:05
         * status : 1
         */

        private String updateDate;
        private boolean isNewRecord;
        private String pic;
        private String delFlag;
        private String title;
        private int type;
        private String content;
        private String linkAddr;
        private String id;
        private int isHot;
        private String remarks;
        private String createDate;
        private int status;

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLinkAddr() {
            return linkAddr;
        }

        public void setLinkAddr(String linkAddr) {
            this.linkAddr = linkAddr;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}

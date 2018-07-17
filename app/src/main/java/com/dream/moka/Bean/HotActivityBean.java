package com.dream.moka.Bean;

/**
 * Created by Administrator on 2017/12/13 0013.
 * 热门活动
 */

public class HotActivityBean {
    /**
     * updateDate : 2017-11-06 18:34:05
     * id : a17383e00d66445aa0870c2bb56e5621
     * isNewRecord : false
     * pic : /mocar/userfiles/1/_thumbs/images/market/activity/2017/11/bglogo.png
     * delFlag : 0
     * title : 最新图文详情
     * type : 1
     * isHot : 1
     * linkAddr :
     * remarks :
     * createDate : 2017-11-06 18:34:05
     * status : 1
     */

    private String updateDate;
    private String id;
    private boolean isNewRecord;
    private String pic;
    private String delFlag;
    private String title;
    private int type;
    private int isHot;
    private String linkAddr;
    private String remarks;
    private String createDate;
    private String content;
    private int status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public String getLinkAddr() {
        return linkAddr;
    }

    public void setLinkAddr(String linkAddr) {
        this.linkAddr = linkAddr;
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

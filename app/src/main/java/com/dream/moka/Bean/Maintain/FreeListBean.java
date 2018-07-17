package com.dream.moka.Bean.Maintain;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7 0007.
 */
public class FreeListBean {

    /**
     * partIds : [{"partId":"b0","count":"2"},{"partId":"b1","count":"2"}]
     * serviceId : a0
     */

    private String serviceId;
    private List<PartIdsBean> partIds;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<PartIdsBean> getPartIds() {
        return partIds;
    }

    public void setPartIds(List<PartIdsBean> partIds) {
        this.partIds = partIds;
    }

    public static class PartIdsBean {
        /**
         * partId : b0
         * count : 2
         */

        private String partId;
        private String count;

        public String getPartId() {
            return partId;
        }

        public void setPartId(String partId) {
            this.partId = partId;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}

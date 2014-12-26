/**
 * WeiXin
 * @title QueryFollowerResult.java
 * @package com.chn.wx.vo.result
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月26日-上午9:37:26
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class QueryFollowerResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class QueryFollowerResult {

    @JSONField(name="total") private int total;
    @JSONField(name="count") private int count;
    @JSONField(name="data") private Data data;
    @JSONField(name="next_openid") private String nextOpenId;
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    public String[] getOpenIds() {
        
        return data == null ? null : data.openIds;
    }

    public String getNextOpenId() {
        return nextOpenId;
    }

    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }

    public static class Data {
        
        @JSONField(name="openid") private String[] openIds;

        public String[] getOpenIds() {
            return openIds;
        }

        public void setOpenIds(String[] openIds) {
            this.openIds = openIds;
        }
        
    }
}

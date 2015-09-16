/**
 * WeiXin
 * @title Group.java
 * @package com.chn.wx.vo
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月23日-下午6:13:18
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class Group
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class Group {

    @JSONField(name="id") private String id;
    @JSONField(name="name") private String name;
    @JSONField(name="count") private Integer count;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    
}

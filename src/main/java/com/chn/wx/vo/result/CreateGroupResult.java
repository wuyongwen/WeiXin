/**
 * WeiXin
 * @title CreateGroupResult.java
 * @package com.chn.wx.vo
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月23日-下午6:10:20
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.chn.wx.vo.Group;

/**
 * @class CreateGroupResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class CreateGroupResult extends BasicResult {

    private static final long serialVersionUID = -3426481289162868652L;
    
    @JSONField(name="group") private Group group;
    
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    
}

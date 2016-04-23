package com.spring.bently.wx.service.impl;

import com.spring.bently.wx.service.ISendMessageService;
import com.spring.bently.wx.utils.StringUtils;
import com.spring.bently.wx.utils.WeixiProperty;
import com.spring.bently.wx.utils.WeixinPropertiesUtils;
import com.spring.bently.wx.utils.httptool.CustomHttpsConnection;
import com.spring.bently.wx.utils.httptool.HttpConnectionCommon;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by wgq on 16-4-16.
 */
@Service
public class SendTextMessageService implements ISendMessageService {

    /**
     * 发送数据
     * @param map
     * @return
     */
    @Override
    public String sendMessage(Map<String,Object> map) {
        if(map.get("json") != null) {
            return sendJsonMessageByGroup(map.get("json").toString()) ;
        }
        if(map.get("group") != null && (Boolean)map.get("group")) {
            return sendMessageByGroup(map) ;
        }if(map.get("openid") != null && (Boolean)map.get("openid")) {
            return null ;
        }

        return sendMessageByGroup(map);
    }

    /**
     * group_id  分组id   不传返回null
     * context   发送内容  不传返回null
     * is_to_all 是否全发 true的情况下可以不指定group_id  默认false
     * @param map
     * @return
     */
    private String sendMessageByGroup(Map<String,Object> map) {

        if(map.get("group_id") == null || map.get("context") == null) {
            return null ;
        }
        String context = map.get("context").toString() ;
        int group_id = Integer.parseInt(map.get("group_id").toString()) ;
        boolean is_to_all = false ;
        if(map.get("is_to_all") != null) {
            is_to_all = (Boolean)map.get("is_to_all") ;
        }

        String postUrl = StringUtils.replaceEach(WeixinPropertiesUtils.getProperties("group_send_message_url"), WeixiProperty.ACCESSTOKEN) ;
        CustomHttpsConnection connection = new CustomHttpsConnection(new HttpConnectionCommon(postUrl,"POST")) ;
        String responseMsg = connection.httpsClient(genarateJson(context,group_id,is_to_all)) ;

        /**
         * {
         "errcode":0,
         "errmsg":"send job submission success",
         "msg_id":34182,
         "msg_data_id": 206227730
         }
         */

        return responseMsg ;
    }

    //直接传Json字符串
    private String sendJsonMessageByGroup(String json) {
        if(json == null) {
            return null ;
        }
        String postUrl = StringUtils.replaceEach(WeixinPropertiesUtils.getProperties("group_send_message_url"), WeixiProperty.ACCESSTOKEN) ;
        CustomHttpsConnection connection = new CustomHttpsConnection(new HttpConnectionCommon(postUrl,"POST")) ;
        String responseMsg = connection.httpsClient(json) ;
        return responseMsg ;
    }

    private String genarateJson(String context,int group_id,boolean is_to_all) {
        StringBuilder builder = new StringBuilder("{") ;
        builder.append("\"filter\":{") ;
        builder.append("\"is_to_all\":").append(is_to_all).append(",") ;
        builder.append("\"group_id\":").append(group_id) ;
        builder.append("},") ;
        builder.append("\"text\":{") ;
        builder.append("\"content\":\"").append(context+"\"") ;
        builder.append("},") ;
        builder.append("\"msgtype\":\"text\"") ;
        builder.append("}");

        /** 示例数据
         * {
         "filter":{
         "is_to_all":false,
         "group_id":2
         },
         "text":{
         "content":"CONTENT"
         },
         "msgtype":"text"
         }
         */
        return builder.toString() ;
    }

}

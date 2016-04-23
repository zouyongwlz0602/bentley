package com.spring.bently.wx.controller.member;

import com.spring.bently.manager.dao.MemberDao;
import com.spring.bently.manager.model.Member;
import com.spring.bently.wx.controller.common.CommonController;
import com.spring.bently.wx.utils.WeixinPropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wgq on 16-4-11.
 */
@Controller
@RequestMapping("/wx/member/recharge")
public class RechargeController extends CommonController {

    private static Logger log = LoggerFactory.getLogger(RechargeController.class) ;

    @Autowired
    private MemberDao memberDao ;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String view(HttpServletRequest request, HttpSession session,ModelMap model) {
        Map userinfoMap = this.setUserInfoSession(request,session) ;

        if(userinfoMap == null) {
            return "error" ;
        }
        //log.info("userinfoMap = " + userinfoMap.toString());
        //ret.put("url", url);
        //ret.put("jsapi_ticket", jsapi_ticket);
        //ret.put("nonceStr", nonce_str);
        //ret.put("timestamp", timestamp);
        //ret.put("signature", signature);

   /*     String url = request.getScheme()+"://"+request.getServerName()+"/wx/member/recharge";
        System.out.println("url = " + url);
        AccessToken jsapiticket = accessTokenDao.findByType("jsapi_ticket") ;
        Map<String, String> map = JsSdkSign.sign(jsapiticket.getAccesstoken(), url) ;
        map.put("appid", WeixinPropertiesUtils.getProperties("appid")) ;
        System.out.println("map = " + map.toString());
        model.putAll(map);*/
        model.addAttribute("one_month",WeixinPropertiesUtils.getProperties("one_month")) ;

        return "member/recharge" ;
    }


    @RequestMapping(value = "/charge",method = RequestMethod.POST)
    public String recharge(HttpServletRequest request, HttpSession session,
                           ModelMap model,@RequestParam int month_in) {
        log.info("进入开始预下单~~~~~~~~");
        Map userinfoMap = this.setUserInfoSession(request,session) ;
        if(userinfoMap == null) {
            return "error" ;
        }

    /*    //把.平台的公共属性，在平台初始化的时候就加入到内存中
        Map<String,Object> map = new TreeMap<String,Object>() ; //使用TreeMap 自动排序
        String key = WeixinPropertiesUtils.getProperties("wxkey");   //商户支付平台key，用于生成0sign

        map.put("appid", WeixinPropertiesUtils.getProperties("appid")) ;    //appid
        map.put("mch_id", Integer.parseInt(WeixinPropertiesUtils.getProperties("mch_id"))) ;  //商户号
        map.put("nonce_str", StringUtils.create_nonce_str()) ;  //随机字符串
        map.put("device_info", "WEB") ;
        map.put("body", "宾利俱乐部会员充值") ;  //商品描述
        map.put("out_trade_no", StringUtils.generateOrderId()) ;    //商户订单号
        map.put("total_fee", month_in * Integer.parseInt(WeixinPropertiesUtils.getProperties("one_month"))) ;
        map.put("spbill_create_ip", StringUtils.getIpAddress(request)) ;    //终端ip
        //接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
        map.put("notify_url", StringUtils.getWebServerName(request) + "/wx/member/recharge/callback") ;
        map.put("trade_type", "JSAPI") ;    //交易类型
        map.put("openid",userinfoMap.get("openid").toString()) ;    //openid
        String sign = JsSdkSign.getPrePaySign(map,key) ;
        Map<String,Object> requestMap = new LinkedHashMap<String,Object>() ;
        for(String keys:map.keySet()) {
            requestMap.put(keys,map.get(keys)) ;
        }
        requestMap.put("sign", sign) ; //签名

        String postUrl = WeixinPropertiesUtils.getProperties("single_unified_url") ;
        HttpConnectionCommon hcc = new HttpConnectionCommon(postUrl,"POST") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String responseMsg = connection.httpsClient(XmlUtils.mapToXmlTwo(requestMap)) ;
        System.out.println(XmlUtils.mapToXmlTwo(requestMap));
        log.info("微信返回信息：" + responseMsg);
        if(responseMsg == null) {
            log.debug("预下单出错，微信返回值为null");
            return "error" ;
        }
        Map responseMap = XmlUtils.xmlToMapString(responseMsg) ;

        if("SUCCESS".equals(responseMap.get("return_code"))) {
            if("SUCCESS".equals(responseMap.get("result_code"))){


            }else {
                String err_code_des = responseMap.get("err_code_des").toString() ;  //得到错误信息
                model.addAttribute(err_code_des) ;
                return "warning" ;
            }
        }else {
            return "error" ;
        }*/
        Member member = getMemberDao().findByWechatid(userinfoMap.get("openid").toString()) ;
        member.setIsVip(true);

        getMemberDao().save(member) ;

        model.addAttribute("msg","支付成功") ;
        return "success" ;
    }

    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public String pay() {
        //model.addAttribute("msg","充值成功") ;
        return "success" ;
    }

    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    public String payCallBack() {

        return null ;
    }

    @Override
    public MemberDao getMemberDao() {
        return memberDao;
    }
}

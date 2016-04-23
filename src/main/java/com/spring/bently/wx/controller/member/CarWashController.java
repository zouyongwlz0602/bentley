package com.spring.bently.wx.controller.member;

import com.spring.bently.manager.dao.MemberDao;
import com.spring.bently.manager.dao.WashCarDao;
import com.spring.bently.manager.model.Member;
import com.spring.bently.manager.model.WashCar;
import com.spring.bently.wx.controller.common.CommonController;
import com.spring.bently.wx.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wgq on 16-4-12.
 */
@Controller
@RequestMapping("/wx/member/carwash")
public class CarWashController extends CommonController {

    private static Logger log = LoggerFactory.getLogger(CarWashController.class) ;

    @Autowired
    private MemberDao memberDao ;

    @Autowired
    private WashCarDao washCarDao ;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String view(HttpServletRequest request,HttpSession session, ModelMap model) {
        log.info("进入");
        String url = this.checkIsMemberAndIsVip(request, session, model) ;
        if(url != null) {
            return url ;
        }

        Map<String, String> map = JsSdkSign.getSign(request, StringUtils.getWeixiCallBackUrl(request, "/wx/member/carwash")) ;
        log.info("url = " + StringUtils.getWeixiCallBackUrl(request,"/wx/member/carwash"));
        map.put("appid", WeixinPropertiesUtils.getProperties("appid")) ;
        log.info(map.toString());
        model.putAll(map);



        return "member/carwash" ;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(HttpServletRequest request,HttpSession session, ModelMap model) {
        Map userinfoMap = this.setUserInfoSession(request, session) ;
        if(userinfoMap == null) {
            return "error" ;
        }
        String wcdate = request.getParameter("wcdate") ;
        String latitude = request.getParameter("latitude") ;    //纬度
        String longitude = request.getParameter("longitude") ;        //经度
        String accuracy = request.getParameter("accuracy") ;    //位置精度
        String addressJson = StringUtils.getLocationName(latitude,longitude,accuracy) ;
        Map addressMap = JsonUtils.jsonToMap(addressJson) ;
        String address = "" ;
        if(StringUtils.isEmpty(latitude)) {
            model.addAttribute("请先打开定位功能") ;
            return "warning" ;
        }
        if(addressMap!=null && "0".equals(addressMap.get("status").toString())) {
            log.info("addressMap = " + ((Map)addressMap.get("result")).get("formatted_address"));

            address = ((Map)addressMap.get("result")).get("formatted_address").toString() ;
        }else {
            log.info("根据经纬度无法得到地址");
        }
        Member member = getMemberDao().findByWechatid(userinfoMap.get("openid").toString()) ;
        int washCarNum = member.getWashcarnum() ;
        try{


            if(washCarNum <= 0) {
                model.addAttribute("msg","您的洗车次数已用完，请联系俱乐部。") ;
                return "warning" ;
            }

            WashCar washCar = new WashCar() ;
            washCar.setWechatid(userinfoMap.get("openid").toString());
            washCar.setWechatname(userinfoMap.get("nickname").toString());
            washCar.setAddress(address);
            washCar.setDestinetime(DateUtils.stringToDate(wcdate));
            washCar.setIsHandle(false);
            washCarDao.save(washCar) ;

            //washCarNum = washCarNum - 1 ;
            //member.setWashcarnum(washCarNum);
            //memberDao.save(member) ;
        }catch (Exception e) {
            e.printStackTrace();
            log.info("添加hotelDestine出错，错误信息：" + e.getMessage());
            model.addAttribute("msg","请联系俱乐部") ;
            return "warning" ;
        }

        model.addAttribute("msg","上门洗车预定成功,剩余次数："+washCarNum) ;

        return "success" ;
    }

    @Override
    public MemberDao getMemberDao() {
        return memberDao;
    }
}

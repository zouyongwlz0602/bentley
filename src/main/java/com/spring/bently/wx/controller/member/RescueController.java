package com.spring.bently.wx.controller.member;

import com.spring.bently.manager.dao.MemberDao;
import com.spring.bently.manager.dao.RescueDao;
import com.spring.bently.manager.model.Member;
import com.spring.bently.manager.model.Rescue;
import com.spring.bently.wx.controller.common.CommonController;
import com.spring.bently.wx.utils.JsSdkSign;
import com.spring.bently.wx.utils.JsonUtils;
import com.spring.bently.wx.utils.StringUtils;
import com.spring.bently.wx.utils.WeixinPropertiesUtils;
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
@RequestMapping("/wx/member/rescue")
public class RescueController extends CommonController {

    private static Logger log = LoggerFactory.getLogger(RescueController.class) ;

    @Autowired
    private MemberDao memberDao ;

    @Autowired
    private RescueDao rescueDao ;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String view(HttpServletRequest request,HttpSession session, ModelMap model) {
        String url = this.checkIsMemberAndIsVip(request, session, model) ;
        if(url != null) {
            return url ;
        }

        Map<String, String> map = JsSdkSign.getSign(request, StringUtils.getWeixiCallBackUrl(request, "/wx/member/rescue")) ;
        log.info("url = " + StringUtils.getWeixiCallBackUrl(request,"/wx/member/rescue"));
        map.put("appid", WeixinPropertiesUtils.getProperties("appid")) ;
        log.info(map.toString());
        model.putAll(map);

        return "member/rescue" ;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,HttpSession session, ModelMap model) {
        Map userinfoMap = this.setUserInfoSession(request, session) ;
        if(userinfoMap == null) {
            return "error" ;
        }

        String localtion = request.getParameter("location") ;
        String latitude = request.getParameter("latitude") ;    //纬度
        String longitude = request.getParameter("longitude") ;        //经度
        String accuracy = request.getParameter("accuracy") ;    //位置精度
        String address = "" ;
        if(StringUtils.isEmpty(latitude)) {
            model.addAttribute("请先打开定位功能") ;
            return "warning" ;
        }
        if(!StringUtils.isEmpty(latitude) && !StringUtils.isEmpty(longitude)) {
            String addressJson = StringUtils.getLocationName(latitude,longitude,accuracy) ;
            Map addressMap = JsonUtils.jsonToMap(addressJson) ;
            if(addressMap!=null && "0".equals(addressMap.get("status").toString())) {
                log.info("addressMap = " + ((Map)addressMap.get("result")).get("formatted_address"));

                address = ((Map)addressMap.get("result")).get("formatted_address").toString() ;
            }else {
                log.info("根据经纬度无法得到地址");
            }
        }

        Member member = getMemberDao().findByWechatid(userinfoMap.get("openid").toString()) ;
        int resucenum = member.getIndoorrenum() ;
        try{


            if(resucenum <= 0) {
                model.addAttribute("msg","您的市内救援次数已用完，请联系俱乐部。") ;
                return "warning" ;
            }

            Rescue rescue = new Rescue() ;
            rescue.setWechatid(userinfoMap.get("openid").toString());
            rescue.setWechatname(userinfoMap.get("nickname").toString());
            rescue.setAddress(address);
            rescue.setIsHandle(false);
            rescue.setRealAddress(localtion);
            rescueDao.save(rescue) ;

            //resucenum = resucenum - 1 ;
            //member.setIndoorrenum(resucenum);
            //memberDao.save(member) ;
        }catch (Exception e) {
            e.printStackTrace();
            log.info("添加hotelDestine出错，错误信息：" + e.getMessage());
            model.addAttribute("msg","请联系俱乐部") ;
            return "warning" ;
        }

        model.addAttribute("msg","市内救援提交成功,剩余次数："+resucenum) ;

        return "success" ;
    }

    @Override
    public MemberDao getMemberDao() {
        return memberDao;
    }
}

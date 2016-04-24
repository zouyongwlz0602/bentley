package com.spring.bently.wx.controller.common;

import com.spring.bently.manager.dao.MemberDao;
import com.spring.bently.manager.model.Member;
import com.spring.bently.wx.utils.DateUtils;
import com.spring.bently.wx.utils.WebAccessTokenUtil;
import com.spring.bently.wx.utils.WeixinPropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created by wgq on 16-4-12.
 */
public abstract class CommonController {

    private static Logger log = LoggerFactory.getLogger(CommonController.class) ;

    public Map setUserInfoSession(HttpServletRequest request,HttpSession session) {
        Map map = null ;
        if(session.getAttribute("userinfoMap") == null) {
            log.info("userinfoMap不存在");
            String code = request.getParameter("code") ;
            if(code == null) {
                log.info("没有从指定的url进入导致code为空");
                return null ;
            }
            map = WebAccessTokenUtil.access_token_request(code) ;
            if(map == null) {
                log.info("获取用户的微信信息失败");
                return null ;
            }
            session.setAttribute("userinfoMap", map);
        }else {
            map = (Map) session.getAttribute("userinfoMap") ;
        }

        return map ;
    }

    public String checkIsMemberAndIsVip(HttpServletRequest request,HttpSession session,ModelMap model) {
        Map userinfoMap = this.setUserInfoSession(request, session) ;

        if(userinfoMap == null) {
            return "error" ;
        }
        log.info("userinfoMap = " + userinfoMap.toString());

        String openid = userinfoMap.get("openid").toString() ;
        Member member = this.getMemberDao().findByWechatid(openid) ;
        if(member == null) {
            model.addAttribute("msg",WeixinPropertiesUtils.getProperties("un_subscribe_message")) ;
            //model.addAttribute("url","") ;
            return "warning" ;
        }
        if(!member.getIsSubscribe()) {
            model.addAttribute("msg",WeixinPropertiesUtils.getProperties("un_subscribe_message")) ;
            return "warning" ;
        }
        if(member.getEndTime() == null||!member.getIsVip() || DateUtils.equalDateTime(member.getEndTime(),new Date())) {
            model.addAttribute("msg",WeixinPropertiesUtils.getProperties("vip_message")) ;
            model.addAttribute("url","/wx/member/recharge") ;
            return "warning" ;
        }

        return null ;
    }

    public abstract MemberDao getMemberDao() ;
}

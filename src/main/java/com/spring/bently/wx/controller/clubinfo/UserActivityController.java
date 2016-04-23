package com.spring.bently.wx.controller.clubinfo;

import com.spring.bently.manager.dao.ActivitySignDao;
import com.spring.bently.manager.dao.UserActivityDao;
import com.spring.bently.manager.model.ActivitySign;
import com.spring.bently.manager.model.UserActivity;
import com.spring.bently.wx.utils.WebAccessTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by wgq on 16-4-10.
 */
@Controller
@RequestMapping("/wx/user/activity")
public class UserActivityController {

    private static Logger log = LoggerFactory.getLogger(UserActivityController.class) ;

    @Autowired
    private UserActivityDao userActivityDao ;

    @Autowired
    private ActivitySignDao activitySignDao ;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String view(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0,5,sort) ;
        Page<UserActivity> page = userActivityDao.findAll(pageable) ;
        List<UserActivity> list = new ArrayList<UserActivity>() ;
        for(Iterator itr = page.iterator();itr.hasNext();) {
            UserActivity userActivity = (UserActivity)itr.next() ;
            list.add(userActivity);
        }
        model.addAttribute("ualist",list) ;
        return "clubinfo/useractivity" ;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String viewDetail(@RequestParam Long id,Model model) {

        UserActivity userActivity = userActivityDao.findOne(id) ;

        Date startTime = userActivity.getStartTime() ;
        Date endTime = userActivity.getEndTime() ;

        Date now = new Date() ;
        boolean status = false ;
        if(endTime != null && startTime != null) {
            if(now.getTime() >= startTime.getTime() && now.getTime() <= endTime.getTime()) {
                status = true ;
            }
        }

        model.addAttribute("status",status) ;
        model.addAttribute("title",userActivity.getTitle()) ;
        model.addAttribute("context", userActivity.getContext()) ;
        model.addAttribute("id",userActivity.getId()) ;
        return "clubinfo/useractivitydetail" ;
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinIn(HttpServletRequest request,ModelMap model) {

        String code = request.getParameter("code") ;

        if(code == null) {
            log.info("没有从指定的url进入导致code为空");
            return "error" ;
        }
        Map userinfoMap = WebAccessTokenUtil.week_access_token_request(code) ;
        if(userinfoMap == null) {
            log.info("获取用户的微信信息失败");
            return "error" ;
        }

        String activityId = request.getParameter("state") ;
        UserActivity userActivity = userActivityDao.findOne(Long.valueOf(activityId)) ;
        String title = userActivity.getTitle() ;
        ActivitySign activitySign = new ActivitySign() ;
        activitySign.setWechatname(userinfoMap.get("nickname").toString());
        activitySign.setWechatid(userinfoMap.get("openid").toString());
        activitySign.setActivityId(Integer.parseInt(activityId));
        activitySign.setActivityTitle(title);
        activitySignDao.save(activitySign) ;

        model.addAttribute("msg","报名成功");
        return "success";
    }
}

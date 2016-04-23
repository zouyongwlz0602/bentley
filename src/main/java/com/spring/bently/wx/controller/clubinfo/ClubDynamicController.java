package com.spring.bently.wx.controller.clubinfo;

import com.spring.bently.manager.dao.ClubDynamicDao;
import com.spring.bently.manager.model.ClubDynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wgq on 16-4-10.
 */
@Controller
@RequestMapping("/wx/club/dynamic")
public class ClubDynamicController {

    @Autowired
    private ClubDynamicDao clubDynamicDao ;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String view(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0,5,sort) ;
        Page<ClubDynamic> page = clubDynamicDao.findAll(pageable) ;
        List<ClubDynamic> list = new ArrayList<ClubDynamic>() ;
        for(Iterator itr = page.iterator();itr.hasNext();) {
            ClubDynamic clubDynamic = (ClubDynamic)itr.next() ;
            list.add(clubDynamic);
        }
        model.addAttribute("cdlist",list) ;
        return "clubinfo/clubdynamic" ;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String viewDetail(@RequestParam Long id,Model model) {

        ClubDynamic clubDynamic = clubDynamicDao.findOne(id) ;

        model.addAttribute("title", clubDynamic.getTitle()) ;
        model.addAttribute("context", clubDynamic.getContext()) ;
        return "clubinfo/clubdynamicdetail" ;
    }
}

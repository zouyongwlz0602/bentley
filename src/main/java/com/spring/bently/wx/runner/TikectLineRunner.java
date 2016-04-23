package com.spring.bently.wx.runner;

import com.spring.bently.wx.schedule.AccessTokenSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by wgq on 16-4-15.
 * 初始化access_token和jsapi_ticket
 */
@Component
public class TikectLineRunner implements CommandLineRunner {

    @Autowired
    private AccessTokenSchedule schedule ;

    @Override
    public void run(String... args) throws Exception {
        schedule.getAccessTokenScheduled();
        schedule.jsapiTicketScheduled();
    }
}

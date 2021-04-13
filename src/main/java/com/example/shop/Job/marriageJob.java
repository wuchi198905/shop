package com.example.shop.Job;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.shop.management.bean.Marriage;
import com.example.shop.pub.service.MarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class marriageJob {
    @Autowired
    private MarriageService advisoryService;
    private static marriageJob commonUtil;
    @PostConstruct
    public void init() {
        commonUtil = this;
        System.err.println("测试一下***********");
    }
//    @Scheduled(fixedRate=2000)

    @Scheduled(cron="0 0 1 * * ?")
    private void configureTasks() {

        Date nowDate = new Date();

        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        List<Marriage> list=this.advisoryService.selectList(new EntityWrapper<Marriage>().lt("effective_time",sdf.format(nowDate)).ne("status",3));
        for(int i=0;i<list.size();i++){
            list.get(i).setStatus("3");
            this.advisoryService.updateById(list.get(i));
        }
    }
}

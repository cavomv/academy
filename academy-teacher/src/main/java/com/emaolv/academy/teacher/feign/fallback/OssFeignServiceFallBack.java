//package com.emaolv.academy.teacher.feign.fallback;
//
//import com.emaolv.academy.common.result.R;
//import com.emaolv.academy.teacher.feign.OssFeignService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
///**
// * @author: liu jia
// * @description: oss 被选方案
// * @date: Created in 2021/5/24 17:51
// */
//@Service
//@Slf4j
//public class OssFeignServiceFallBack implements OssFeignService {
//
//    @Override
//    public R removeAvatar(String url) {
//        log.info("熔断保护");
//        return R.fail();
//    }
//
//    @Override
//    public R test() {
//        return R.fail();
//    }
//}

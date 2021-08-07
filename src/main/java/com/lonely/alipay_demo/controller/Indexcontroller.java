package com.lonely.alipay_demo.controller;

import com.lonely.alipay_demo.config.AlipayConfig;
import com.lonely.alipay_demo.result.R;
import com.lonely.alipay_demo.result.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiyang
 * @FileName: Indexcontroller
 * @Date: Created in 2021/8/6 11:39
 * @Vserion:
 * @Description: TODO
 */
@RestController
public class Indexcontroller {
    Logger logger = LoggerFactory.getLogger(Indexcontroller.class);

    @Autowired
    private AlipayConfig alipayConfig;

    @GetMapping("/test")
    public R test(){
        logger.info(alipayConfig.toString());

        return R.setResult(ResultCodeEnum.LOGIN_CODE_ERROR).data("key","123");
    }
}

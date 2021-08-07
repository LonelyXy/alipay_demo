package com.lonely.alipay_demo.controller;

import com.lonely.alipay_demo.service.KssOrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (KssOrderDetail)表控制层
 *
 * @author makejava
 * @since 2021-08-06 09:11:24
 */
@RestController
@RequestMapping("kssOrderDetail")
public class KssOrderDetailController {
    /**
     * 订单服务对象
     */
    @Resource
    private KssOrderDetailService kssOrderDetailService;



}

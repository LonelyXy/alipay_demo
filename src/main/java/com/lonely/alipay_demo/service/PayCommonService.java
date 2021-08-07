package com.lonely.alipay_demo.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: xiyang
 * @FileName: payCommonService
 * @Date: Created in 2021/8/7 10:29
 * @Vserion:
 * @Description: TODO
 */
public interface PayCommonService {
    /**
     * 添加订单
     * @param body
     * @param s
     * @param orderNumber
     * @param trade_no
     * @param s1
     */
    void payproductcourse(JSONObject body, String s, String orderNumber, String trade_no, String s1);
}

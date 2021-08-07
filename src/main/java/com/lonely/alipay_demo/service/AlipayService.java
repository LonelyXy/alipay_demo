package com.lonely.alipay_demo.service;

import com.lonely.alipay_demo.vo.PayVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xiyang
 * @FileName: AlipayService
 * @Date: Created in 2021/8/6 12:27
 * @Vserion:
 * @Description: TODO
 */
public interface AlipayService {

    /**
     * 获取支付宝支付二维码
     * @param payVo
     * @return img
     */
    byte[] alipay(PayVo payVo) throws Exception;

    /**
     * 支付成功后的回调函数
     * @param request
     * @return
     */
    Boolean alipayCallback(HttpServletRequest request) throws Exception;
}

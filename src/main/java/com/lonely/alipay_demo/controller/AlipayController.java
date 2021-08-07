package com.lonely.alipay_demo.controller;

import com.lonely.alipay_demo.service.AlipayService;
import com.lonely.alipay_demo.vo.PayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xiyang
 * @FileName: AlipayController
 * @Date: Created in 2021/8/6 12:30
 * @Vserion:
 * @Description: TODO
 */
@RestController
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    /**
     * 拉起支付请求
     * @param payVo
     * @return
     * @throws Exception
     */
    @GetMapping("/alipay/pay")
    public byte[] alipay(PayVo payVo) throws Exception{
        byte[] alipay = alipayService.alipay(payVo);
        return alipay;
    }

    /**
     * 支付成功以后的异步回调API
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/alipay/pay/callback")
    public String  notify_url(HttpServletRequest request) throws Exception{
         Boolean result =  alipayService.alipayCallback(request);
        if(result){
            return "success";
        }else{
            return "false";
        }
    }
}

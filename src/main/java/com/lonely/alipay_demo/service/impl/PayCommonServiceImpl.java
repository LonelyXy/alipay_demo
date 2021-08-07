package com.lonely.alipay_demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lonely.alipay_demo.dao.KssOrderDetailDao;
import com.lonely.alipay_demo.entity.KssOrderDetail;
import com.lonely.alipay_demo.service.PayCommonService;
import com.lonely.alipay_demo.util.GenerateNum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: xiyang
 * @FileName: payCommonServiceImpl
 * @Date: Created in 2021/8/7 10:29
 * @Vserion:
 * @Description: TODO
 */
@Service
public class PayCommonServiceImpl implements PayCommonService {
    @Resource
    private KssOrderDetailDao kssOrderDetailDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void payproductcourse(JSONObject body, String userId, String orderNumber, String trade_no, String paymethod) {
        // 支付的课程
        String courseId = body.getString("courseId");
        // 支付的金额
        String money = body.getString("price");
        // 保存订单明细表
        KssOrderDetail orderDetail = new KssOrderDetail();
        orderDetail.setId(Long.valueOf(GenerateNum.generateOrder()));
//        orderDetail.setUserid(userId);
        orderDetail.setCourseid(courseId);
        orderDetail.setUsername("飞哥");
        orderDetail.setPaymethod(paymethod);
        orderDetail.setCoursetitle(body.getString("courseTitle"));
        orderDetail.setOrdernumber(orderNumber);
        orderDetail.setTradeno(trade_no);
        orderDetail.setPrice(money == null ? "0.01" : money);
        kssOrderDetailDao.insert(orderDetail);
    }
}

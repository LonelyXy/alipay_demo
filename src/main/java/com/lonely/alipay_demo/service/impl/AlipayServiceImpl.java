package com.lonely.alipay_demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.lonely.alipay_demo.config.AlipayConfig;
import com.lonely.alipay_demo.dao.KssCoursesDao;
import com.lonely.alipay_demo.entity.KssCourses;
import com.lonely.alipay_demo.qrcode.QRCodeUtil;
import com.lonely.alipay_demo.qrcode.QrCodeResponse;
import com.lonely.alipay_demo.qrcode.QrResponse;
import com.lonely.alipay_demo.result.BusinessException;
import com.lonely.alipay_demo.result.ResultCodeEnum;
import com.lonely.alipay_demo.service.AlipayService;
import com.lonely.alipay_demo.service.PayCommonService;
import com.lonely.alipay_demo.util.GenerateNum;
import com.lonely.alipay_demo.util.ParamsUtil;
import com.lonely.alipay_demo.vo.PayVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @Author: xiyang
 * @FileName: AlipayServicImpl
 * @Date: Created in 2021/8/6 12:28
 * @Vserion:
 * @Description: TODO
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired
    private AlipayConfig alipayConfig;

    @Resource
    private KssCoursesDao kssCoursesDao;

    @Autowired
    private PayCommonService payCommonService;


    @Override
    public byte[] alipay(PayVo payVo) throws Exception {
        /**
         * 1. 获取阿里客户端
         * 2. 获取阿里请求对象
         * 3. 设置请求参数
         * 4. 设置同步通知回调路径
         * 5. 设置异步通知回调路径
         */
        KssCourses kssCourses = kssCoursesDao.queryById(payVo.getCourseId());
        if (kssCourses == null) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_EXCEPTION);
        }
        String orderNumber = GenerateNum.generateOrder();

        //设置支付回调时可以在request中获取的参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", kssCourses.getCourseid());
        jsonObject.put("courseTitle", kssCourses.getTitle());
        jsonObject.put("courseImg", kssCourses.getImg());
        jsonObject.put("orderNumber", orderNumber);
        jsonObject.put("payType", payVo.getPayMethod());
        jsonObject.put("price", kssCourses.getPrice());
        String params = jsonObject.toString();

        //设置支付参数
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setBody(params);
        model.setTotalAmount(kssCourses.getPrice().toString());
        model.setOutTradeNo(orderNumber);
        model.setSubject(kssCourses.getTitle());
        //获取响应二维码信息
        QrCodeResponse qrCodeResponse = qrcodePay(model);
        //制作二维码并且返回给前端
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String logopath = "";
        logopath = ResourceUtils.getFile("classpath:favicon.png").getAbsolutePath();
        logger.info("二维码的图片路径为===>" + logopath);
        BufferedImage encode = QRCodeUtil.encode(qrCodeResponse.getQr_code(), logopath, false);
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
        ImageIO.write(encode, "JPEG", imageOutputStream);
        imageOutputStream.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return FileCopyUtils.copyToByteArray(byteArrayInputStream);
    }

    @Override
    public Boolean alipayCallback(HttpServletRequest request) {
        try {
            Map<String, String> params = ParamsUtil.ParamstoMap(request);
            logger.info("回调参数=========>" + params);
            String trade_no = params.get("trade_no");
            String body1 = params.get("body");
            logger.info("交易的流水号和交易信息===========>", trade_no, body1);
            JSONObject body = JSONObject.parseObject(body1);
            //String userId = body.getString("userId");
            String ptype = body.getString("payType");
            String orderNumber = body.getString("orderNumber");
            if (ptype != null && ptype.equals("1")) {
                payCommonService.payproductcourse(body, "1", orderNumber, trade_no, "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("异常====>", e.toString());
            return false;
        }
        return true;
    }

    /**
     * 支付宝客户端发送支付请求获取支付二维码信息
     */
    public QrCodeResponse qrcodePay(AlipayTradePrecreateModel model) throws AlipayApiException {
        //1.获取请求客户端
        AlipayClient alipayClient = getAlipayClient();

        //2. 获取请求对象
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();

        //3.设置请求参数
        alipayRequest.setBizModel(model);
        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
        AlipayTradePrecreateResponse execute = null;
        execute = alipayClient.execute(alipayRequest);
        String body = execute.getBody();
        logger.info("请求的响应二维码信息====>" + body);
        QrResponse qrResponse = JSON.parseObject(body, QrResponse.class);
        return qrResponse.getAlipay_trade_precreate_response();
    }

    /**
     * 获取阿里客户端
     *
     * @return
     */
    public AlipayClient getAlipayClient() {
        DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(
                alipayConfig.getURL(),
                alipayConfig.getAPPID(),
                alipayConfig.getRSA_PRIVATE_KEY(),
                alipayConfig.getFORMAT(),
                alipayConfig.getCHARSET(),
                alipayConfig.getALIPAY_PUBLIC_KEY(),
                alipayConfig.getSIGNTYPE()
        );
        return defaultAlipayClient;
    }
}

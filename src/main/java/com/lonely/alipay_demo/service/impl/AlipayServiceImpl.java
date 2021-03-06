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
         * 1. ?????????????????????
         * 2. ????????????????????????
         * 3. ??????????????????
         * 4. ??????????????????????????????
         * 5. ??????????????????????????????
         */
        KssCourses kssCourses = kssCoursesDao.queryById(payVo.getCourseId());
        if (kssCourses == null) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_EXCEPTION);
        }
        String orderNumber = GenerateNum.generateOrder();

        //??????????????????????????????request??????????????????
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", kssCourses.getCourseid());
        jsonObject.put("courseTitle", kssCourses.getTitle());
        jsonObject.put("courseImg", kssCourses.getImg());
        jsonObject.put("orderNumber", orderNumber);
        jsonObject.put("payType", payVo.getPayMethod());
        jsonObject.put("price", kssCourses.getPrice());
        String params = jsonObject.toString();

        //??????????????????
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setBody(params);
        model.setTotalAmount(kssCourses.getPrice().toString());
        model.setOutTradeNo(orderNumber);
        model.setSubject(kssCourses.getTitle());
        //???????????????????????????
        QrCodeResponse qrCodeResponse = qrcodePay(model);
        //????????????????????????????????????
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String logopath = "";
        logopath = ResourceUtils.getFile("classpath:favicon.png").getAbsolutePath();
        logger.info("???????????????????????????===>" + logopath);
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
            logger.info("????????????=========>" + params);
            String trade_no = params.get("trade_no");
            String body1 = params.get("body");
            logger.info("?????????????????????????????????===========>", trade_no, body1);
            JSONObject body = JSONObject.parseObject(body1);
            //String userId = body.getString("userId");
            String ptype = body.getString("payType");
            String orderNumber = body.getString("orderNumber");
            if (ptype != null && ptype.equals("1")) {
                payCommonService.payproductcourse(body, "1", orderNumber, trade_no, "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("??????====>", e.toString());
            return false;
        }
        return true;
    }

    /**
     * ???????????????????????????????????????????????????????????????
     */
    public QrCodeResponse qrcodePay(AlipayTradePrecreateModel model) throws AlipayApiException {
        //1.?????????????????????
        AlipayClient alipayClient = getAlipayClient();

        //2. ??????????????????
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();

        //3.??????????????????
        alipayRequest.setBizModel(model);
        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
        AlipayTradePrecreateResponse execute = null;
        execute = alipayClient.execute(alipayRequest);
        String body = execute.getBody();
        logger.info("??????????????????????????????====>" + body);
        QrResponse qrResponse = JSON.parseObject(body, QrResponse.class);
        return qrResponse.getAlipay_trade_precreate_response();
    }

    /**
     * ?????????????????????
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

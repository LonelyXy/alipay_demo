package com.lonely.alipay_demo.qrcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xiyang
 * @FileName: QrResponse
 * @Date: Created in 2021/8/6 19:31
 * @Vserion:
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrResponse {

    private QrCodeResponse alipay_trade_precreate_response;

    private String sign;
}

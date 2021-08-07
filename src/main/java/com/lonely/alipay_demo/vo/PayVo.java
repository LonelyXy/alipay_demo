package com.lonely.alipay_demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xiyang
 * @FileName: PayVo
 * @Date: Created in 2021/8/6 14:45
 * @Vserion:
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayVo {

    private String courseId;

    private Integer payMethod;

}

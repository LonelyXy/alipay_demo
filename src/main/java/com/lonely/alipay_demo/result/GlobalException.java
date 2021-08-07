package com.lonely.alipay_demo.result;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: xiyang
 * @FileName: GlobalException
 * @Date: Created in 2021/8/6 12:14
 * @Vserion:
 * @Description: TODO
 */
public class GlobalException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        JSONObject jsonObject = new JSONObject();
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            jsonObject.put("status", businessException.getStatus());
            jsonObject.put("code", businessException.getCode());
            jsonObject.put("message", businessException.getMessage());
        } else if (e instanceof SQLException) {
            jsonObject.put("status", ResultCodeEnum.BAD_SQL_GRAMMAR.getStatus());
            jsonObject.put("code", ResultCodeEnum.BAD_SQL_GRAMMAR.getCode());
            jsonObject.put("message", ResultCodeEnum.BAD_SQL_GRAMMAR.getMessage());
        } else {
            jsonObject.put("status", ResultCodeEnum.SYSTEM_EXCEPTION.getStatus());
            jsonObject.put("code", ResultCodeEnum.SYSTEM_EXCEPTION.getCode());
            jsonObject.put("message", ResultCodeEnum.SYSTEM_EXCEPTION.getMessage());
        }

        try {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.getWriter().write(jsonObject.toString());
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

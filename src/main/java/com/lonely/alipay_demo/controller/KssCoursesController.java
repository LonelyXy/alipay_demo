package com.lonely.alipay_demo.controller;

import com.lonely.alipay_demo.entity.KssCourses;
import com.lonely.alipay_demo.service.KssCoursesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (KssCourses)表控制层
 *
 * @author makejava
 * @since 2021-08-06 09:11:43
 */
@RestController
@RequestMapping("kssCourses")
public class KssCoursesController {
    /**
     * 服务对象
     */
    @Resource
    private KssCoursesService kssCoursesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public KssCourses selectOne(String id) {
        return this.kssCoursesService.queryById(id);
    }

}

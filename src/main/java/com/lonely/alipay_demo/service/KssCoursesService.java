package com.lonely.alipay_demo.service;

import com.lonely.alipay_demo.entity.KssCourses;

import java.util.List;

/**
 * (KssCourses)表服务接口
 *
 * @author makejava
 * @since 2021-08-06 09:11:43
 */
public interface KssCoursesService {

    /**
     * 通过ID查询单条数据
     *
     * @param courseid 主键
     * @return 实例对象
     */
    KssCourses queryById(String courseid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<KssCourses> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param kssCourses 实例对象
     * @return 实例对象
     */
    KssCourses insert(KssCourses kssCourses);

    /**
     * 修改数据
     *
     * @param kssCourses 实例对象
     * @return 实例对象
     */
    KssCourses update(KssCourses kssCourses);

    /**
     * 通过主键删除数据
     *
     * @param courseid 主键
     * @return 是否成功
     */
    boolean deleteById(String courseid);

}

package com.lonely.alipay_demo.service;

import com.lonely.alipay_demo.entity.KssOrderDetail;

import java.util.List;

/**
 * (KssOrderDetail)表服务接口
 *
 * @author makejava
 * @since 2021-08-06 09:11:23
 */
public interface KssOrderDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    KssOrderDetail queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<KssOrderDetail> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param kssOrderDetail 实例对象
     * @return 实例对象
     */
    KssOrderDetail insert(KssOrderDetail kssOrderDetail);

    /**
     * 修改数据
     *
     * @param kssOrderDetail 实例对象
     * @return 实例对象
     */
    KssOrderDetail update(KssOrderDetail kssOrderDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);



}

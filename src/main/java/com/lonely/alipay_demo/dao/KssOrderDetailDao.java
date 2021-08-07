package com.lonely.alipay_demo.dao;

import com.lonely.alipay_demo.entity.KssOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (KssOrderDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-06 09:11:21
 */
public interface KssOrderDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    KssOrderDetail queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<KssOrderDetail> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param kssOrderDetail 实例对象
     * @return 对象列表
     */
    List<KssOrderDetail> queryAll(KssOrderDetail kssOrderDetail);

    /**
     * 新增数据
     *
     * @param kssOrderDetail 实例对象
     * @return 影响行数
     */
    int insert(KssOrderDetail kssOrderDetail);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<KssOrderDetail> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<KssOrderDetail> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<KssOrderDetail> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<KssOrderDetail> entities);

    /**
     * 修改数据
     *
     * @param kssOrderDetail 实例对象
     * @return 影响行数
     */
    int update(KssOrderDetail kssOrderDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}


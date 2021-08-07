package com.lonely.alipay_demo.dao;

import com.lonely.alipay_demo.entity.KssCourses;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (KssCourses)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-06 09:11:43
 */
public interface KssCoursesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param courseid 主键
     * @return 实例对象
     */
    KssCourses queryById(String courseid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<KssCourses> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param kssCourses 实例对象
     * @return 对象列表
     */
    List<KssCourses> queryAll(KssCourses kssCourses);

    /**
     * 新增数据
     *
     * @param kssCourses 实例对象
     * @return 影响行数
     */
    int insert(KssCourses kssCourses);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<KssCourses> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<KssCourses> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<KssCourses> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<KssCourses> entities);

    /**
     * 修改数据
     *
     * @param kssCourses 实例对象
     * @return 影响行数
     */
    int update(KssCourses kssCourses);

    /**
     * 通过主键删除数据
     *
     * @param courseid 主键
     * @return 影响行数
     */
    int deleteById(String courseid);

}


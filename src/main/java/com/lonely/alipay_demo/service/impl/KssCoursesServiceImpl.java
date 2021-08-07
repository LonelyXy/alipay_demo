package com.lonely.alipay_demo.service.impl;

import com.lonely.alipay_demo.entity.KssCourses;
import com.lonely.alipay_demo.dao.KssCoursesDao;
import com.lonely.alipay_demo.service.KssCoursesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (KssCourses)表服务实现类
 *
 * @author makejava
 * @since 2021-08-06 09:11:43
 */
@Service("kssCoursesService")
public class KssCoursesServiceImpl implements KssCoursesService {
    @Resource
    private KssCoursesDao kssCoursesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param courseid 主键
     * @return 实例对象
     */
    @Override
    public KssCourses queryById(String courseid) {
        return this.kssCoursesDao.queryById(courseid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<KssCourses> queryAllByLimit(int offset, int limit) {
        return this.kssCoursesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kssCourses 实例对象
     * @return 实例对象
     */
    @Override
    public KssCourses insert(KssCourses kssCourses) {
        this.kssCoursesDao.insert(kssCourses);
        return kssCourses;
    }

    /**
     * 修改数据
     *
     * @param kssCourses 实例对象
     * @return 实例对象
     */
    @Override
    public KssCourses update(KssCourses kssCourses) {
        this.kssCoursesDao.update(kssCourses);
        return this.queryById(kssCourses.getCourseid());
    }

    /**
     * 通过主键删除数据
     *
     * @param courseid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String courseid) {
        return this.kssCoursesDao.deleteById(courseid) > 0;
    }
}

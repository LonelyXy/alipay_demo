package com.lonely.alipay_demo.service.impl;

import com.lonely.alipay_demo.entity.KssOrderDetail;
import com.lonely.alipay_demo.dao.KssOrderDetailDao;
import com.lonely.alipay_demo.service.KssOrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (KssOrderDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-08-06 09:11:23
 */
@Service("kssOrderDetailService")
public class KssOrderDetailServiceImpl implements KssOrderDetailService {
    @Resource
    private KssOrderDetailDao kssOrderDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public KssOrderDetail queryById(Long id) {
        return this.kssOrderDetailDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<KssOrderDetail> queryAllByLimit(int offset, int limit) {
        return this.kssOrderDetailDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kssOrderDetail 实例对象
     * @return 实例对象
     */
    @Override
    public KssOrderDetail insert(KssOrderDetail kssOrderDetail) {
        this.kssOrderDetailDao.insert(kssOrderDetail);
        return kssOrderDetail;
    }

    /**
     * 修改数据
     *
     * @param kssOrderDetail 实例对象
     * @return 实例对象
     */
    @Override
    public KssOrderDetail update(KssOrderDetail kssOrderDetail) {
        this.kssOrderDetailDao.update(kssOrderDetail);
        return this.queryById(kssOrderDetail.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.kssOrderDetailDao.deleteById(id) > 0;
    }
}

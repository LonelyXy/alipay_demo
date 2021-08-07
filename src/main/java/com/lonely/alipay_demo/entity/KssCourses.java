package com.lonely.alipay_demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (KssCourses)实体类
 *
 * @author makejava
 * @since 2021-08-06 09:11:43
 */
public class KssCourses implements Serializable {
    private static final long serialVersionUID = 261036342156151394L;
    /**
     * 课程唯一id
     */
    private String courseid;
    /**
     * 课程标题
     */
    private String title;
    /**
     * 课程简短介绍
     */
    private String intro;
    /**
     * 课程封面地址
     */
    private String img;
    /**
     * 课程的活动价
     */
    private Double price;
    /**
     * 状态:已发布/未发布
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}

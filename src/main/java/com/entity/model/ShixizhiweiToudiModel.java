package com.entity.model;

import com.entity.ShixizhiweiToudiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 实习申请
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShixizhiweiToudiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 简历
     */
    private Integer jianliId;


    /**
     * 实习
     */
    private Integer shixizhiweiId;


    /**
     * 投递时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 审核状态
     */
    private Integer shixizhiweiToudiYesnoTypes;


    /**
     * 投递回复
     */
    private String shixizhiweiToudiYesnoText;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date shixizhiweiToudiShenheTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：简历
	 */
    public Integer getJianliId() {
        return jianliId;
    }


    /**
	 * 设置：简历
	 */
    public void setJianliId(Integer jianliId) {
        this.jianliId = jianliId;
    }
    /**
	 * 获取：实习
	 */
    public Integer getShixizhiweiId() {
        return shixizhiweiId;
    }


    /**
	 * 设置：实习
	 */
    public void setShixizhiweiId(Integer shixizhiweiId) {
        this.shixizhiweiId = shixizhiweiId;
    }
    /**
	 * 获取：投递时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：投递时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：审核状态
	 */
    public Integer getShixizhiweiToudiYesnoTypes() {
        return shixizhiweiToudiYesnoTypes;
    }


    /**
	 * 设置：审核状态
	 */
    public void setShixizhiweiToudiYesnoTypes(Integer shixizhiweiToudiYesnoTypes) {
        this.shixizhiweiToudiYesnoTypes = shixizhiweiToudiYesnoTypes;
    }
    /**
	 * 获取：投递回复
	 */
    public String getShixizhiweiToudiYesnoText() {
        return shixizhiweiToudiYesnoText;
    }


    /**
	 * 设置：投递回复
	 */
    public void setShixizhiweiToudiYesnoText(String shixizhiweiToudiYesnoText) {
        this.shixizhiweiToudiYesnoText = shixizhiweiToudiYesnoText;
    }
    /**
	 * 获取：回复时间
	 */
    public Date getShixizhiweiToudiShenheTime() {
        return shixizhiweiToudiShenheTime;
    }


    /**
	 * 设置：回复时间
	 */
    public void setShixizhiweiToudiShenheTime(Date shixizhiweiToudiShenheTime) {
        this.shixizhiweiToudiShenheTime = shixizhiweiToudiShenheTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }

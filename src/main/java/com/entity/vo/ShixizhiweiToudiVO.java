package com.entity.vo;

import com.entity.ShixizhiweiToudiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 实习申请
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shixizhiwei_toudi")
public class ShixizhiweiToudiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 简历
     */

    @TableField(value = "jianli_id")
    private Integer jianliId;


    /**
     * 实习
     */

    @TableField(value = "shixizhiwei_id")
    private Integer shixizhiweiId;


    /**
     * 投递时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 审核状态
     */

    @TableField(value = "shixizhiwei_toudi_yesno_types")
    private Integer shixizhiweiToudiYesnoTypes;


    /**
     * 投递回复
     */

    @TableField(value = "shixizhiwei_toudi_yesno_text")
    private String shixizhiweiToudiYesnoText;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "shixizhiwei_toudi_shenhe_time")
    private Date shixizhiweiToudiShenheTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：简历
	 */
    public Integer getJianliId() {
        return jianliId;
    }


    /**
	 * 获取：简历
	 */

    public void setJianliId(Integer jianliId) {
        this.jianliId = jianliId;
    }
    /**
	 * 设置：实习
	 */
    public Integer getShixizhiweiId() {
        return shixizhiweiId;
    }


    /**
	 * 获取：实习
	 */

    public void setShixizhiweiId(Integer shixizhiweiId) {
        this.shixizhiweiId = shixizhiweiId;
    }
    /**
	 * 设置：投递时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：投递时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：审核状态
	 */
    public Integer getShixizhiweiToudiYesnoTypes() {
        return shixizhiweiToudiYesnoTypes;
    }


    /**
	 * 获取：审核状态
	 */

    public void setShixizhiweiToudiYesnoTypes(Integer shixizhiweiToudiYesnoTypes) {
        this.shixizhiweiToudiYesnoTypes = shixizhiweiToudiYesnoTypes;
    }
    /**
	 * 设置：投递回复
	 */
    public String getShixizhiweiToudiYesnoText() {
        return shixizhiweiToudiYesnoText;
    }


    /**
	 * 获取：投递回复
	 */

    public void setShixizhiweiToudiYesnoText(String shixizhiweiToudiYesnoText) {
        this.shixizhiweiToudiYesnoText = shixizhiweiToudiYesnoText;
    }
    /**
	 * 设置：回复时间
	 */
    public Date getShixizhiweiToudiShenheTime() {
        return shixizhiweiToudiShenheTime;
    }


    /**
	 * 获取：回复时间
	 */

    public void setShixizhiweiToudiShenheTime(Date shixizhiweiToudiShenheTime) {
        this.shixizhiweiToudiShenheTime = shixizhiweiToudiShenheTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 实习记录
 *
 * @author 
 * @email
 */
@TableName("shixijilu")
public class ShixijiluEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShixijiluEntity() {

	}

	public ShixijiluEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 实习记录编号
     */
    @ColumnInfo(comment="实习记录编号",type="varchar(200)")
    @TableField(value = "shixijilu_uuid_number")

    private String shixijiluUuidNumber;


    /**
     * 实习单位名称
     */
    @ColumnInfo(comment="实习单位名称",type="varchar(200)")
    @TableField(value = "shixijilu_name")

    private String shixijiluName;


    /**
     * 实习职位
     */
    @ColumnInfo(comment="实习职位",type="int(11)")
    @TableField(value = "shixizhiwei_types")

    private Integer shixizhiweiTypes;


    /**
     * 单位位置
     */
    @ColumnInfo(comment="单位位置",type="varchar(200)")
    @TableField(value = "danwei_address")

    private String danweiAddress;


    /**
     * 联系人
     */
    @ColumnInfo(comment="联系人",type="varchar(200)")
    @TableField(value = "danwei_lianxiren")

    private String danweiLianxiren;


    /**
     * 联系方式
     */
    @ColumnInfo(comment="联系方式",type="varchar(200)")
    @TableField(value = "danwei_lianxi_phone")

    private String danweiLianxiPhone;


    /**
     * 实习薪资
     */
    @ColumnInfo(comment="实习薪资",type="decimal(10,2)")
    @TableField(value = "shixijilu_xinzi")

    private Double shixijiluXinzi;


    /**
     * 实习开始时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="实习开始时间",type="timestamp")
    @TableField(value = "shixijilu_kaishi_time")

    private Date shixijiluKaishiTime;


    /**
     * 实习结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="实习结束时间",type="timestamp")
    @TableField(value = "shixijilu_jieshu_time")

    private Date shixijiluJieshuTime;


    /**
     * 实习内容
     */
    @ColumnInfo(comment="实习内容",type="text")
    @TableField(value = "shixijilu_content")

    private String shixijiluContent;


    /**
     * 上传时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="上传时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间   listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：实习记录编号
	 */
    public String getShixijiluUuidNumber() {
        return shixijiluUuidNumber;
    }
    /**
	 * 设置：实习记录编号
	 */

    public void setShixijiluUuidNumber(String shixijiluUuidNumber) {
        this.shixijiluUuidNumber = shixijiluUuidNumber;
    }
    /**
	 * 获取：实习单位名称
	 */
    public String getShixijiluName() {
        return shixijiluName;
    }
    /**
	 * 设置：实习单位名称
	 */

    public void setShixijiluName(String shixijiluName) {
        this.shixijiluName = shixijiluName;
    }
    /**
	 * 获取：实习职位
	 */
    public Integer getShixizhiweiTypes() {
        return shixizhiweiTypes;
    }
    /**
	 * 设置：实习职位
	 */

    public void setShixizhiweiTypes(Integer shixizhiweiTypes) {
        this.shixizhiweiTypes = shixizhiweiTypes;
    }
    /**
	 * 获取：单位位置
	 */
    public String getDanweiAddress() {
        return danweiAddress;
    }
    /**
	 * 设置：单位位置
	 */

    public void setDanweiAddress(String danweiAddress) {
        this.danweiAddress = danweiAddress;
    }
    /**
	 * 获取：联系人
	 */
    public String getDanweiLianxiren() {
        return danweiLianxiren;
    }
    /**
	 * 设置：联系人
	 */

    public void setDanweiLianxiren(String danweiLianxiren) {
        this.danweiLianxiren = danweiLianxiren;
    }
    /**
	 * 获取：联系方式
	 */
    public String getDanweiLianxiPhone() {
        return danweiLianxiPhone;
    }
    /**
	 * 设置：联系方式
	 */

    public void setDanweiLianxiPhone(String danweiLianxiPhone) {
        this.danweiLianxiPhone = danweiLianxiPhone;
    }
    /**
	 * 获取：实习薪资
	 */
    public Double getShixijiluXinzi() {
        return shixijiluXinzi;
    }
    /**
	 * 设置：实习薪资
	 */

    public void setShixijiluXinzi(Double shixijiluXinzi) {
        this.shixijiluXinzi = shixijiluXinzi;
    }
    /**
	 * 获取：实习开始时间
	 */
    public Date getShixijiluKaishiTime() {
        return shixijiluKaishiTime;
    }
    /**
	 * 设置：实习开始时间
	 */

    public void setShixijiluKaishiTime(Date shixijiluKaishiTime) {
        this.shixijiluKaishiTime = shixijiluKaishiTime;
    }
    /**
	 * 获取：实习结束时间
	 */
    public Date getShixijiluJieshuTime() {
        return shixijiluJieshuTime;
    }
    /**
	 * 设置：实习结束时间
	 */

    public void setShixijiluJieshuTime(Date shixijiluJieshuTime) {
        this.shixijiluJieshuTime = shixijiluJieshuTime;
    }
    /**
	 * 获取：实习内容
	 */
    public String getShixijiluContent() {
        return shixijiluContent;
    }
    /**
	 * 设置：实习内容
	 */

    public void setShixijiluContent(String shixijiluContent) {
        this.shixijiluContent = shixijiluContent;
    }
    /**
	 * 获取：上传时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：上传时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间   listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间   listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Shixijilu{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", shixijiluUuidNumber=" + shixijiluUuidNumber +
            ", shixijiluName=" + shixijiluName +
            ", shixizhiweiTypes=" + shixizhiweiTypes +
            ", danweiAddress=" + danweiAddress +
            ", danweiLianxiren=" + danweiLianxiren +
            ", danweiLianxiPhone=" + danweiLianxiPhone +
            ", shixijiluXinzi=" + shixijiluXinzi +
            ", shixijiluKaishiTime=" + DateUtil.convertString(shixijiluKaishiTime,"yyyy-MM-dd") +
            ", shixijiluJieshuTime=" + DateUtil.convertString(shixijiluJieshuTime,"yyyy-MM-dd") +
            ", shixijiluContent=" + shixijiluContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}

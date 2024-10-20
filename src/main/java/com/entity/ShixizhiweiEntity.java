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
 * 职位实习
 *
 * @author 
 * @email
 */
@TableName("shixizhiwei")
public class ShixizhiweiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShixizhiweiEntity() {

	}

	public ShixizhiweiEntity(T t) {
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
     * 企业
     */
    @ColumnInfo(comment="企业",type="int(11)")
    @TableField(value = "qiye_id")

    private Integer qiyeId;


    /**
     * 实习信息名称
     */
    @ColumnInfo(comment="实习信息名称",type="varchar(200)")
    @TableField(value = "shixizhiwei_name")

    private String shixizhiweiName;


    /**
     * 实习信息照片
     */
    @ColumnInfo(comment="实习信息照片",type="varchar(200)")
    @TableField(value = "shixizhiwei_photo")

    private String shixizhiweiPhoto;


    /**
     * 薪资待遇
     */
    @ColumnInfo(comment="薪资待遇",type="varchar(200)")
    @TableField(value = "shixizhiwei_daiyu")

    private String shixizhiweiDaiyu;


    /**
     * 上班地点
     */
    @ColumnInfo(comment="上班地点",type="varchar(200)")
    @TableField(value = "shixizhiwei_address")

    private String shixizhiweiAddress;


    /**
     * 联系人
     */
    @ColumnInfo(comment="联系人",type="varchar(200)")
    @TableField(value = "lianxiren_name")

    private String lianxirenName;


    /**
     * 实习电话
     */
    @ColumnInfo(comment="实习电话",type="varchar(200)")
    @TableField(value = "shixizhiwei_phone")

    private String shixizhiweiPhone;


    /**
     * 实习职位
     */
    @ColumnInfo(comment="实习职位",type="int(11)")
    @TableField(value = "shixizhiwei_types")

    private Integer shixizhiweiTypes;


    /**
     * 实习人数
     */
    @ColumnInfo(comment="实习人数",type="int(11)")
    @TableField(value = "shixizhiwei_renshu_number")

    private Integer shixizhiweiRenshuNumber;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 实习信息详情
     */
    @ColumnInfo(comment="实习信息详情",type="text")
    @TableField(value = "shixizhiwei_content")

    private String shixizhiweiContent;


    /**
     * 创建时间
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
	 * 获取：企业
	 */
    public Integer getQiyeId() {
        return qiyeId;
    }
    /**
	 * 设置：企业
	 */

    public void setQiyeId(Integer qiyeId) {
        this.qiyeId = qiyeId;
    }
    /**
	 * 获取：实习信息名称
	 */
    public String getShixizhiweiName() {
        return shixizhiweiName;
    }
    /**
	 * 设置：实习信息名称
	 */

    public void setShixizhiweiName(String shixizhiweiName) {
        this.shixizhiweiName = shixizhiweiName;
    }
    /**
	 * 获取：实习信息照片
	 */
    public String getShixizhiweiPhoto() {
        return shixizhiweiPhoto;
    }
    /**
	 * 设置：实习信息照片
	 */

    public void setShixizhiweiPhoto(String shixizhiweiPhoto) {
        this.shixizhiweiPhoto = shixizhiweiPhoto;
    }
    /**
	 * 获取：薪资待遇
	 */
    public String getShixizhiweiDaiyu() {
        return shixizhiweiDaiyu;
    }
    /**
	 * 设置：薪资待遇
	 */

    public void setShixizhiweiDaiyu(String shixizhiweiDaiyu) {
        this.shixizhiweiDaiyu = shixizhiweiDaiyu;
    }
    /**
	 * 获取：上班地点
	 */
    public String getShixizhiweiAddress() {
        return shixizhiweiAddress;
    }
    /**
	 * 设置：上班地点
	 */

    public void setShixizhiweiAddress(String shixizhiweiAddress) {
        this.shixizhiweiAddress = shixizhiweiAddress;
    }
    /**
	 * 获取：联系人
	 */
    public String getLianxirenName() {
        return lianxirenName;
    }
    /**
	 * 设置：联系人
	 */

    public void setLianxirenName(String lianxirenName) {
        this.lianxirenName = lianxirenName;
    }
    /**
	 * 获取：实习电话
	 */
    public String getShixizhiweiPhone() {
        return shixizhiweiPhone;
    }
    /**
	 * 设置：实习电话
	 */

    public void setShixizhiweiPhone(String shixizhiweiPhone) {
        this.shixizhiweiPhone = shixizhiweiPhone;
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
	 * 获取：实习人数
	 */
    public Integer getShixizhiweiRenshuNumber() {
        return shixizhiweiRenshuNumber;
    }
    /**
	 * 设置：实习人数
	 */

    public void setShixizhiweiRenshuNumber(Integer shixizhiweiRenshuNumber) {
        this.shixizhiweiRenshuNumber = shixizhiweiRenshuNumber;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 设置：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：实习信息详情
	 */
    public String getShixizhiweiContent() {
        return shixizhiweiContent;
    }
    /**
	 * 设置：实习信息详情
	 */

    public void setShixizhiweiContent(String shixizhiweiContent) {
        this.shixizhiweiContent = shixizhiweiContent;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Shixizhiwei{" +
            ", id=" + id +
            ", qiyeId=" + qiyeId +
            ", shixizhiweiName=" + shixizhiweiName +
            ", shixizhiweiPhoto=" + shixizhiweiPhoto +
            ", shixizhiweiDaiyu=" + shixizhiweiDaiyu +
            ", shixizhiweiAddress=" + shixizhiweiAddress +
            ", lianxirenName=" + lianxirenName +
            ", shixizhiweiPhone=" + shixizhiweiPhone +
            ", shixizhiweiTypes=" + shixizhiweiTypes +
            ", shixizhiweiRenshuNumber=" + shixizhiweiRenshuNumber +
            ", shangxiaTypes=" + shangxiaTypes +
            ", shixizhiweiContent=" + shixizhiweiContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}

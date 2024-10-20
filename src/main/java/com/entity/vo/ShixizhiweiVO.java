package com.entity.vo;

import com.entity.ShixizhiweiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 职位实习
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shixizhiwei")
public class ShixizhiweiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 企业
     */

    @TableField(value = "qiye_id")
    private Integer qiyeId;


    /**
     * 实习信息名称
     */

    @TableField(value = "shixizhiwei_name")
    private String shixizhiweiName;


    /**
     * 实习信息照片
     */

    @TableField(value = "shixizhiwei_photo")
    private String shixizhiweiPhoto;


    /**
     * 薪资待遇
     */

    @TableField(value = "shixizhiwei_daiyu")
    private String shixizhiweiDaiyu;


    /**
     * 上班地点
     */

    @TableField(value = "shixizhiwei_address")
    private String shixizhiweiAddress;


    /**
     * 联系人
     */

    @TableField(value = "lianxiren_name")
    private String lianxirenName;


    /**
     * 实习电话
     */

    @TableField(value = "shixizhiwei_phone")
    private String shixizhiweiPhone;


    /**
     * 实习职位
     */

    @TableField(value = "shixizhiwei_types")
    private Integer shixizhiweiTypes;


    /**
     * 实习人数
     */

    @TableField(value = "shixizhiwei_renshu_number")
    private Integer shixizhiweiRenshuNumber;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 实习信息详情
     */

    @TableField(value = "shixizhiwei_content")
    private String shixizhiweiContent;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：企业
	 */
    public Integer getQiyeId() {
        return qiyeId;
    }


    /**
	 * 获取：企业
	 */

    public void setQiyeId(Integer qiyeId) {
        this.qiyeId = qiyeId;
    }
    /**
	 * 设置：实习信息名称
	 */
    public String getShixizhiweiName() {
        return shixizhiweiName;
    }


    /**
	 * 获取：实习信息名称
	 */

    public void setShixizhiweiName(String shixizhiweiName) {
        this.shixizhiweiName = shixizhiweiName;
    }
    /**
	 * 设置：实习信息照片
	 */
    public String getShixizhiweiPhoto() {
        return shixizhiweiPhoto;
    }


    /**
	 * 获取：实习信息照片
	 */

    public void setShixizhiweiPhoto(String shixizhiweiPhoto) {
        this.shixizhiweiPhoto = shixizhiweiPhoto;
    }
    /**
	 * 设置：薪资待遇
	 */
    public String getShixizhiweiDaiyu() {
        return shixizhiweiDaiyu;
    }


    /**
	 * 获取：薪资待遇
	 */

    public void setShixizhiweiDaiyu(String shixizhiweiDaiyu) {
        this.shixizhiweiDaiyu = shixizhiweiDaiyu;
    }
    /**
	 * 设置：上班地点
	 */
    public String getShixizhiweiAddress() {
        return shixizhiweiAddress;
    }


    /**
	 * 获取：上班地点
	 */

    public void setShixizhiweiAddress(String shixizhiweiAddress) {
        this.shixizhiweiAddress = shixizhiweiAddress;
    }
    /**
	 * 设置：联系人
	 */
    public String getLianxirenName() {
        return lianxirenName;
    }


    /**
	 * 获取：联系人
	 */

    public void setLianxirenName(String lianxirenName) {
        this.lianxirenName = lianxirenName;
    }
    /**
	 * 设置：实习电话
	 */
    public String getShixizhiweiPhone() {
        return shixizhiweiPhone;
    }


    /**
	 * 获取：实习电话
	 */

    public void setShixizhiweiPhone(String shixizhiweiPhone) {
        this.shixizhiweiPhone = shixizhiweiPhone;
    }
    /**
	 * 设置：实习职位
	 */
    public Integer getShixizhiweiTypes() {
        return shixizhiweiTypes;
    }


    /**
	 * 获取：实习职位
	 */

    public void setShixizhiweiTypes(Integer shixizhiweiTypes) {
        this.shixizhiweiTypes = shixizhiweiTypes;
    }
    /**
	 * 设置：实习人数
	 */
    public Integer getShixizhiweiRenshuNumber() {
        return shixizhiweiRenshuNumber;
    }


    /**
	 * 获取：实习人数
	 */

    public void setShixizhiweiRenshuNumber(Integer shixizhiweiRenshuNumber) {
        this.shixizhiweiRenshuNumber = shixizhiweiRenshuNumber;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：实习信息详情
	 */
    public String getShixizhiweiContent() {
        return shixizhiweiContent;
    }


    /**
	 * 获取：实习信息详情
	 */

    public void setShixizhiweiContent(String shixizhiweiContent) {
        this.shixizhiweiContent = shixizhiweiContent;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

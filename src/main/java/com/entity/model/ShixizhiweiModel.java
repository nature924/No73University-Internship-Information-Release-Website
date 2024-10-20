package com.entity.model;

import com.entity.ShixizhiweiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 职位实习
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShixizhiweiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 企业
     */
    private Integer qiyeId;


    /**
     * 实习信息名称
     */
    private String shixizhiweiName;


    /**
     * 实习信息照片
     */
    private String shixizhiweiPhoto;


    /**
     * 薪资待遇
     */
    private String shixizhiweiDaiyu;


    /**
     * 上班地点
     */
    private String shixizhiweiAddress;


    /**
     * 联系人
     */
    private String lianxirenName;


    /**
     * 实习电话
     */
    private String shixizhiweiPhone;


    /**
     * 实习职位
     */
    private Integer shixizhiweiTypes;


    /**
     * 实习人数
     */
    private Integer shixizhiweiRenshuNumber;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 实习信息详情
     */
    private String shixizhiweiContent;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }

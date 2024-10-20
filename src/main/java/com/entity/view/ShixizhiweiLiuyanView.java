package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShixizhiweiLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 职位留言
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shixizhiwei_liuyan")
public class ShixizhiweiLiuyanView extends ShixizhiweiLiuyanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 职位实习
					 
		/**
		* 职位实习 的 企业
		*/
		@ColumnInfo(comment="企业",type="int(11)")
		private Integer shixizhiweiQiyeId;
		/**
		* 实习信息名称
		*/

		@ColumnInfo(comment="实习信息名称",type="varchar(200)")
		private String shixizhiweiName;
		/**
		* 实习信息照片
		*/

		@ColumnInfo(comment="实习信息照片",type="varchar(200)")
		private String shixizhiweiPhoto;
		/**
		* 薪资待遇
		*/

		@ColumnInfo(comment="薪资待遇",type="varchar(200)")
		private String shixizhiweiDaiyu;
		/**
		* 上班地点
		*/

		@ColumnInfo(comment="上班地点",type="varchar(200)")
		private String shixizhiweiAddress;
		/**
		* 联系人
		*/

		@ColumnInfo(comment="联系人",type="varchar(200)")
		private String lianxirenName;
		/**
		* 实习电话
		*/

		@ColumnInfo(comment="实习电话",type="varchar(200)")
		private String shixizhiweiPhone;
		/**
		* 实习职位
		*/
		@ColumnInfo(comment="实习职位",type="int(11)")
		private Integer shixizhiweiTypes;
			/**
			* 实习职位的值
			*/
			@ColumnInfo(comment="实习职位的字典表值",type="varchar(200)")
			private String shixizhiweiValue;
		/**
		* 实习人数
		*/

		@ColumnInfo(comment="实习人数",type="int(11)")
		private Integer shixizhiweiRenshuNumber;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
		/**
		* 实习信息详情
		*/

		@ColumnInfo(comment="实习信息详情",type="text")
		private String shixizhiweiContent;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 头像
		*/

		@ColumnInfo(comment="头像",type="varchar(255)")
		private String yonghuPhoto;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 假删
		*/

		@ColumnInfo(comment="假删",type="int(11)")
		private Integer yonghuDelete;



	public ShixizhiweiLiuyanView() {

	}

	public ShixizhiweiLiuyanView(ShixizhiweiLiuyanEntity shixizhiweiLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, shixizhiweiLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 职位实习
		/**
		* 获取：职位实习 的 企业
		*/
		public Integer getShixizhiweiQiyeId() {
			return shixizhiweiQiyeId;
		}
		/**
		* 设置：职位实习 的 企业
		*/
		public void setShixizhiweiQiyeId(Integer shixizhiweiQiyeId) {
			this.shixizhiweiQiyeId = shixizhiweiQiyeId;
		}

		/**
		* 获取： 实习信息名称
		*/
		public String getShixizhiweiName() {
			return shixizhiweiName;
		}
		/**
		* 设置： 实习信息名称
		*/
		public void setShixizhiweiName(String shixizhiweiName) {
			this.shixizhiweiName = shixizhiweiName;
		}

		/**
		* 获取： 实习信息照片
		*/
		public String getShixizhiweiPhoto() {
			return shixizhiweiPhoto;
		}
		/**
		* 设置： 实习信息照片
		*/
		public void setShixizhiweiPhoto(String shixizhiweiPhoto) {
			this.shixizhiweiPhoto = shixizhiweiPhoto;
		}

		/**
		* 获取： 薪资待遇
		*/
		public String getShixizhiweiDaiyu() {
			return shixizhiweiDaiyu;
		}
		/**
		* 设置： 薪资待遇
		*/
		public void setShixizhiweiDaiyu(String shixizhiweiDaiyu) {
			this.shixizhiweiDaiyu = shixizhiweiDaiyu;
		}

		/**
		* 获取： 上班地点
		*/
		public String getShixizhiweiAddress() {
			return shixizhiweiAddress;
		}
		/**
		* 设置： 上班地点
		*/
		public void setShixizhiweiAddress(String shixizhiweiAddress) {
			this.shixizhiweiAddress = shixizhiweiAddress;
		}

		/**
		* 获取： 联系人
		*/
		public String getLianxirenName() {
			return lianxirenName;
		}
		/**
		* 设置： 联系人
		*/
		public void setLianxirenName(String lianxirenName) {
			this.lianxirenName = lianxirenName;
		}

		/**
		* 获取： 实习电话
		*/
		public String getShixizhiweiPhone() {
			return shixizhiweiPhone;
		}
		/**
		* 设置： 实习电话
		*/
		public void setShixizhiweiPhone(String shixizhiweiPhone) {
			this.shixizhiweiPhone = shixizhiweiPhone;
		}
		/**
		* 获取： 实习职位
		*/
		public Integer getShixizhiweiTypes() {
			return shixizhiweiTypes;
		}
		/**
		* 设置： 实习职位
		*/
		public void setShixizhiweiTypes(Integer shixizhiweiTypes) {
			this.shixizhiweiTypes = shixizhiweiTypes;
		}


			/**
			* 获取： 实习职位的值
			*/
			public String getShixizhiweiValue() {
				return shixizhiweiValue;
			}
			/**
			* 设置： 实习职位的值
			*/
			public void setShixizhiweiValue(String shixizhiweiValue) {
				this.shixizhiweiValue = shixizhiweiValue;
			}

		/**
		* 获取： 实习人数
		*/
		public Integer getShixizhiweiRenshuNumber() {
			return shixizhiweiRenshuNumber;
		}
		/**
		* 设置： 实习人数
		*/
		public void setShixizhiweiRenshuNumber(Integer shixizhiweiRenshuNumber) {
			this.shixizhiweiRenshuNumber = shixizhiweiRenshuNumber;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}

		/**
		* 获取： 实习信息详情
		*/
		public String getShixizhiweiContent() {
			return shixizhiweiContent;
		}
		/**
		* 设置： 实习信息详情
		*/
		public void setShixizhiweiContent(String shixizhiweiContent) {
			this.shixizhiweiContent = shixizhiweiContent;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 假删
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 假删
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}


	@Override
	public String toString() {
		return "ShixizhiweiLiuyanView{" +
			", shixizhiweiName=" + shixizhiweiName +
			", shixizhiweiPhoto=" + shixizhiweiPhoto +
			", shixizhiweiDaiyu=" + shixizhiweiDaiyu +
			", shixizhiweiAddress=" + shixizhiweiAddress +
			", lianxirenName=" + lianxirenName +
			", shixizhiweiPhone=" + shixizhiweiPhone +
			", shixizhiweiRenshuNumber=" + shixizhiweiRenshuNumber +
			", shixizhiweiContent=" + shixizhiweiContent +
			", yonghuName=" + yonghuName +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuEmail=" + yonghuEmail +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}

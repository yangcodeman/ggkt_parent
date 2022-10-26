package com.atguigu.ggkt.model.vod;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(description = "GubaData")
@TableName("guba_data")
public class GubaData {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id")
	private String id;

//	@ApiModelProperty(value = "阅读数")
//	@TableField("read_number")
//	private String readNumber;
//
//	@ApiModelProperty(value = "评论数")
//	@TableField("comment_number")
//	private String commentNumber;
//
//	@ApiModelProperty(value = "标题")
//	@TableField("title")
//	private String title;
//
//	@ApiModelProperty(value = "作者")
//	@TableField("author")
//	private String author;
//
//	@ApiModelProperty(value = "最后更新")
//	@TableField("update_time")
//	private String updateTime;
//
//
//	@ApiModelProperty(value = "情绪值")
//	private BigDecimal emotionValue;
//
//	@ApiModelProperty(value = "是否积极")
//	@TableField("is_positive")
//	private String isPositive;

	@ApiModelProperty(value = "态度因子(1:积极 0:中立 -1:消极)")
	@TableField("attitude_value")
	private String attitudeValue;
}
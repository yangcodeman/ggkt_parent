package com.atguigu.ggkt.model.vod;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "GubaDataVo")
@TableName("guba_data_vo")
public class GubaDataVo {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "股票名称")
	@TableField("stock_name")
	private String stockName;

	@ApiModelProperty(value = "发贴总量")
	@TableField("total_number")
	private String totalNumber;

	@ApiModelProperty(value = "积极量")
	@TableField("positive_number")
	private String positiveNumber;

	@ApiModelProperty(value = "消极量")
	@TableField("passive_number")
	private String passiveNumber;

	@ApiModelProperty(value = "BSI数值")
	@TableField("bsi_number")
	private String bsiNumber;

	@ApiModelProperty(value = "Sentiment数值")
	@TableField("sentiment_number")
	private String sentimentNumber;
}
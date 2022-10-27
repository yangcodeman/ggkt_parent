package com.atguigu.ggkt.vo.vod;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * Dict
 * </p>
 *
 * @author qy
 */
@Data
public class GubaDataEeVo {

	@ExcelProperty(value = "股票名称" ,index = 0)
	private String stockName;

	@ExcelProperty(value = "发贴总量" ,index = 1)
	private String totalNumber;

	@ExcelProperty(value = "积极量" ,index = 2)
	private String positiveNumber;

	@ExcelProperty(value = "消极量" ,index = 3)
	private String passiveNumber;

	@ExcelProperty(value = "BSI数值" ,index = 4)
	private String bsiNumber;

	@ExcelProperty(value = "Sentiment数值" ,index = 5)
	private String sentimentNumber;

}


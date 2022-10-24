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

	@ExcelProperty(value = "id" ,index = 0)
	private String id;

	@ExcelProperty(value = "阅读数" ,index = 1)
	private String readNumber;

	@ExcelProperty(value = "评论数" ,index = 2)
	private String commentNumber;

	@ExcelProperty(value = "标题" ,index = 3)
	private String title;

	@ExcelProperty(value = "作者" ,index = 4)
	private String author;

	@ExcelProperty(value = "最后更新" ,index = 5)
	private String updateTime;

	@ExcelProperty(value = "情绪" ,index = 6)
	private BigDecimal emotionValue;

	@ExcelProperty(value = "态度" ,index = 7)
	private String isPositive;

	@ExcelProperty(value = "态度因子" ,index = 8)
	private String attitudeValue;

}


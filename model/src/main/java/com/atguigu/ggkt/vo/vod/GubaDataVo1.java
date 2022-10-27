package com.atguigu.ggkt.vo.vod;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>
 * Dict
 * </p>
 *
 * @author qy
 */
@Data
public class GubaDataVo1 {

	@ExcelProperty(value = "id" ,index = 0)
	private String id;

	@ExcelProperty(value = "态度因子" ,index = 8)
	private String attitudeValue;
}


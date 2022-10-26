package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.GubaData;
import com.atguigu.ggkt.vo.vod.GubaDataVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * <p>
 * 股吧数据 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-26
 */
public interface GubaDataService extends IService<GubaData> {

    void importData(MultipartFile file);

    void exportData(HttpServletResponse response,String xlsxName,GubaDataVo gubaDataVo);

    MultipartFile getMultipartFile(File file);


    GubaDataVo calculateData(String xlsxName);
}

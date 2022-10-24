package com.atguigu.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.exception.GgktException;
import com.atguigu.ggkt.model.vod.GubaData;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.GubaDataEeVo;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.listener.GubaDataListener;
import com.atguigu.ggkt.vod.listener.SubjectListener;
import com.atguigu.ggkt.vod.mapper.GubaDataMapper;
import com.atguigu.ggkt.vod.service.GubaDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.jfr.ContentType;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 股吧数据 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-26
 */
@Service
public class GubaDataServiceImpl extends ServiceImpl<GubaDataMapper, GubaData> implements GubaDataService {
    @Resource
    private GubaDataListener gubaDataListener;

    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                    GubaDataEeVo.class,
                    gubaDataListener).sheet().doRead();
        } catch (IOException e) {
            throw new GgktException(20001,"表格导入失败");
        }
    }

    @Override
    public void exportData(HttpServletResponse response,String xlsxName) {
        try {
            // 消极因子
            BigDecimal passiveBigdemical = new BigDecimal(0.30);
            // 积极因子
            BigDecimal positiveBigdemical = new BigDecimal(0.70);
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            //查询课程分类表所有数据
            List<GubaData> gubaDataList = baseMapper.selectList(null);

            List<GubaDataEeVo> gubaDataEeVoList = new ArrayList<>();
            for (GubaData gubaData: gubaDataList) {
                GubaDataEeVo gubaDataEeVo = new GubaDataEeVo();
                BeanUtils.copyProperties(gubaData,gubaDataEeVo);
                int passiveFlag = gubaDataEeVo.getEmotionValue().compareTo(passiveBigdemical);
                int positiveFlag = gubaDataEeVo.getEmotionValue().compareTo(positiveBigdemical);
                if (passiveFlag != 1 ){
                    // 说明情绪值小于等于0.3
                    gubaDataEeVo.setIsPositive("消极");
                    gubaDataEeVo.setAttitudeValue("-1");
                }
                if (positiveFlag != -1 ){
                    // 说明情绪值大于等于0.7
                    gubaDataEeVo.setIsPositive("积极");
                    gubaDataEeVo.setAttitudeValue("1");
                }
                gubaDataEeVoList.add(gubaDataEeVo);
            }

            //EasyExcel写操作
//            EasyExcel.write(response.getOutputStream(), GubaDataEeVo.class)
//                    .sheet("股吧数据")
//                    .doWrite(gubaDataEeVoList);

            File ff = new File("/Users/yang/Desktop/Output/"+xlsxName);
//            if (!ff.exists()){
//                ff.mkdir();
//            }
            EasyExcel.write(ff, GubaDataEeVo.class)
                    .sheet("xlsxName")
                    .doWrite(gubaDataEeVoList);
        }catch(Exception e) {
            throw new GgktException(20001,"导出失败");
        }
    }


    @Override
    public MultipartFile getMultipartFile(File file){
        MultipartFile multipartFile = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(), inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipartFile;
    }
}

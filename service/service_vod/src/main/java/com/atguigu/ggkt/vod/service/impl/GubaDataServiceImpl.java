package com.atguigu.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.exception.GgktException;
import com.atguigu.ggkt.model.vod.GubaData;
import com.atguigu.ggkt.model.vod.GubaDataVo;
import com.atguigu.ggkt.vo.vod.GubaDataEeVo;
import com.atguigu.ggkt.vo.vod.GubaDataVo1;
import com.atguigu.ggkt.vod.listener.GubaDataListener;
import com.atguigu.ggkt.vod.mapper.GubaDataMapper;
import com.atguigu.ggkt.vod.mapper.GubaDataVoMapper;
import com.atguigu.ggkt.vod.service.GubaDataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import java.net.URLEncoder;
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
    @Resource GubaDataMapper gubaDataMapper;
    @Resource
    GubaDataVoMapper gubaDataVoMapper;

    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                    GubaDataVo1.class,
                    gubaDataListener).sheet().doRead();
        } catch (IOException e) {
            throw new GgktException(20001,"表格导入失败");
        }
    }

    @Override
    public void exportData(HttpServletResponse response,String xlsxName,GubaDataVo gubaDataVo) {
        try {
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
//
                gubaDataEeVoList.add(gubaDataEeVo);
            }

            //EasyExcel写操作
//            EasyExcel.write(response.getOutputStream(), GubaDataEeVo.class)
//                    .sheet("股吧数据")
//                    .doWrite(gubaDataEeVoList);

            File ff = new File("/Users/yang/Desktop/output2021/"+xlsxName);
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

    @Override
    public void calculateData(String xlsxName) {
        Integer totalNumber = gubaDataMapper.selectCount(null);

        QueryWrapper<GubaData> gubaQueryWrapper = Wrappers.query();

        gubaQueryWrapper.eq("attitude_value",1);

        Integer positiveNumber = gubaDataMapper.selectCount(gubaQueryWrapper);

        QueryWrapper<GubaData> gubaQuery1Wrapper = Wrappers.query();

        gubaQuery1Wrapper.eq("attitude_value",-1);

        Integer passiveNumber = gubaDataMapper.selectCount(gubaQuery1Wrapper);

        float bsiNumber = (float)(positiveNumber-passiveNumber)/(positiveNumber+passiveNumber);

        double log = Math.log(totalNumber);
        double sentimentNumber = log * bsiNumber;

        GubaDataVo gubaDataVo = new GubaDataVo();

        gubaDataVo.setTotalNumber(String.valueOf(totalNumber));
        gubaDataVo.setPassiveNumber(String.valueOf(passiveNumber));
        gubaDataVo.setPositiveNumber(String.valueOf(positiveNumber));
        gubaDataVo.setBsiNumber(String.valueOf(bsiNumber));
        gubaDataVo.setSentimentNumber(String.valueOf(sentimentNumber));
        gubaDataVo.setStockName(xlsxName);

        gubaDataVoMapper.insert(gubaDataVo);
    }
}

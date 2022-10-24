package com.atguigu.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.ggkt.model.vod.GubaData;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.GubaDataEeVo;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.mapper.GubaDataMapper;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GubaDataListener extends AnalysisEventListener<GubaDataEeVo> {
    //注入mapper
    @Resource
    private GubaDataMapper gubaDataMapper;

    @Override
    public void invoke( GubaDataEeVo gubaDataEeVo, AnalysisContext analysisContext) {
//        Subject subject = new Subject();
        GubaData gubaData = new GubaData();
        //  SubjectEeVo -- Subject
        BeanUtils.copyProperties(gubaDataEeVo,gubaData);

        gubaDataMapper.insert(gubaData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

package com.atguigu.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.ggkt.model.vod.GubaData;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.GubaDataEeVo;
import com.atguigu.ggkt.vo.vod.GubaDataVo1;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.mapper.GubaDataMapper;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GubaDataListener extends AnalysisEventListener<GubaDataVo1> {
    //注入mapper
    @Resource
    private GubaDataMapper gubaDataMapper;

    @Override
    public void invoke( GubaDataVo1 gubaDataVo1, AnalysisContext analysisContext) {
        GubaData gubaData = new GubaData();
//        gubaData.setAttitudeValue(gubaDataVo1.getAttitudeValue());
//        gubaData.setId(gubaDataVo1.getId());
        BeanUtils.copyProperties(gubaDataVo1,gubaData);

        gubaDataMapper.insert(gubaData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

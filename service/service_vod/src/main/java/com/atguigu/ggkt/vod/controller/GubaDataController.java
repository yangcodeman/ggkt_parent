package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.GubaDataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 * 股吧数据 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-09-26
 */
@Api(tags = "股吧数据")
@RestController
@RequestMapping("/vod/guba-data")
public class GubaDataController {

    @Resource
    GubaDataService gubaDataService;

    //股吧数据导入
    @ApiOperation("股吧数据导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        gubaDataService.importData(file);
        return Result.ok(null);
    }

    //股吧数据导出
//    @ApiOperation("课程分类导出")
//    @GetMapping("exportData")
//    public void exportData(HttpServletResponse response) {
//        gubaDataService.exportData(response);
//    }

    //股吧数据流程
    @ApiOperation("数据流程")
    @GetMapping("giveData")
    public void giveData(HttpServletResponse response) throws IOException {

        File file = new File("/Users/yang/IdeaProjects/ggkt_parent/service/service_vod/src/main/resources/exception");//
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);

        File ff = new File("/Users/yang/Desktop/Mock/2022");
        File[] fs = ff.listFiles();
        String xlsxName;
        MultipartFile multipartFile;
        for (int i = 0; i < fs.length; i++) {
            String fileName = fs[i].getName();
            if (fileName.contains("DS_Store")) {
                continue;
            }
            multipartFile = gubaDataService.getMultipartFile(fs[i]);
            xlsxName = multipartFile.getName();
            System.out.println(xlsxName);
            try {
                // 数据导入
                gubaDataService.importData(multipartFile);
                // 数据导出
                gubaDataService.exportData(response, xlsxName);
                // 数据清除
                gubaDataService.remove(new QueryWrapper<>());
                fs[i].delete();
            } catch (Exception e) {
                bw.write(xlsxName+",");
            } finally {
                bw.close();
                fw.close();
                continue;
            }
        }
    }
}



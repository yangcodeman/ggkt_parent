package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-09-08
 */
@RestController
@RequestMapping("/vod/course")
public class CourseController {

    @Resource
    private CourseService courseService;
    // 条件查询点播课程列表
    @ApiOperation("条件查询点播课程列表")
    @GetMapping("{page}/{limit}")
    public Result courseList(@PathVariable long page,
                             @PathVariable long limit,
                             CourseQueryVo courseQueryVo){
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String,Object> map = courseService.findPageCourse(pageParam,courseQueryVo);
        return Result.ok(map);
    }
}


package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.mapper.CourseMapper;
import com.atguigu.ggkt.vod.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-08
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private TeacherService teacherService;

    @Resource
    private SubjectService subjectService;

    @Resource
    private CourseDescriptionService descriptionService;

    @Resource
    private VideoService videoService;

    @Resource
    private ChapterService chapterService;

    @Override
    public Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo) {

        Long teacherId = courseQueryVo.getTeacherId();
        Long subjectId = courseQueryVo.getSubjectId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();
        String title = courseQueryVo.getTitle();

        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(subjectId)){
            wrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)){
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if (!StringUtils.isEmpty(teacherId)){
            wrapper.eq("teacher_id",teacherId);
        }

        Page<Course> pages = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = pages.getTotal();
        long totalPage = pages.getPages();
        List<Course> list = pages.getRecords();

        list.stream().forEach(item ->{
            this.getNameById(item);
        });

        Map<String, Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",list);

        return map;
    }

    private Course getNameById(Course course) {
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if (teacher!=null){
            String name = teacher.getName();
            course.getParam().put("teacherName",name);
        }

        Subject serviceOne = subjectService.getById(course.getSubjectParentId());
        if (serviceOne!=null){
            course.getParam().put("subjectParentTitle",serviceOne.getTitle());
        }

        Subject serviceTwo = subjectService.getById(course.getSubjectId());
        if (serviceTwo!=null){
            course.getParam().put("subjectTitle",serviceTwo.getTitle());
        }
        return course;
    }
}

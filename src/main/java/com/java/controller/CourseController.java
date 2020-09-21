package com.java.controller;

import com.java.mapper.CourseMapper;
import com.java.pojo.Course;
import com.java.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * time:14:54
 * author:丁鹏
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    /**
     * 获取课程信息
     * @return
     */
    @RequestMapping("/getCourseInfo")
    public String getCourseInfo(Model model){
        model.addAttribute("cList",courseService.findCourseInfo());
        return "admin/showCourse.jsp";
    }

    /**
     * 获取课程类型
     * @return
     */
    @RequestMapping("/getCatogory")
    public String getCatogory(Model model){
        model.addAttribute("ctList",courseService.findCourseType());
        return "admin/addCourse.jsp";
    }

    /**
     * 添加课程
     * @param course
     * @return
     */
    @RequestMapping("/addCourse")
    public String addCourse(Course course, Model model, HttpSession session){
        course.setuID(session.getAttribute("uID")+"");
        boolean flag = courseService.saveCourse(course);
        if(flag){//添加成功
            return "redirect:/course/getCourseInfo";
        }else{//添加失败
            model.addAttribute("course",course);
            model.addAttribute("ctList",courseService.findCourseType());
            return "admin/addCourse.jsp";
        }
    }

    /**
     * excel导出
     * @param response
     * @return
     */
    @RequestMapping("/exportData2Excel")
    public @ResponseBody boolean exportData2Excel(HttpServletResponse response){
        boolean flag = courseService.exportData2Excel(response);
        return flag;
    }


}

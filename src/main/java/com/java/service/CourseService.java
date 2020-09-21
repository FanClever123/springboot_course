package com.java.service;

import com.java.pojo.Course;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * time:14:51
 * author:丁鹏
 */
public interface CourseService {
    /**
     * 查询课程信息(users、catogory、courses三表联合查询)
     * @return
     */
    List<Map<String,Object>> findCourseInfo();

    /**
     * 查询课程类别
     * @return
     */
    List<Map<String,Object>> findCourseType();

    /**
     * 添加课程
     * @param c
     * @return
     */
    boolean saveCourse(Course c);

    /**
     * Excel导出
     * @param response
     * @return
     */
    boolean exportData2Excel(HttpServletResponse response);
}

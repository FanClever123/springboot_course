package com.java.service.impl;

import com.java.mapper.CourseMapper;
import com.java.pojo.Course;
import com.java.utils.ExcelHelper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 课程业务层
 * time:14:50
 * author:丁鹏
 */
@Service
@Transactional(readOnly = false)
public class CourseServiceImpl implements com.java.service.CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询课程信息(users、catogory、courses三表联合查询)
     * @return
     */
    @Override
    public List<Map<String,Object>> findCourseInfo(){
        return courseMapper.selectCourseInfo();
    }

    /**
     * 查询课程类别
     * @return
     */
    public List<Map<String,Object>> findCourseType(){
        return courseMapper.selectCourseType();
    }

    /**
     * 添加课程
     * @param c
     * @return
     */
    public boolean saveCourse(Course c){
        //1、判断cID是否存在
        int i = courseMapper.selectCourseIDIsExist(c.getcID());
        if(i!=1){
            return false;
        }
        //2、调用DAO层存数据到数据库中去
        int j = courseMapper.insertCourseInfo(c);
        if(j!=1){
            return false;
        }
        return true;
    }

    /**
     * Excel导出
     * @param response
     * @return
     */
    public boolean exportData2Excel(HttpServletResponse response){
        try {
            List<Map<String, Object>> courseList = courseMapper.selectCourseInfo();
            //构建excel文件的名字
            String fileName = UUID.randomUUID()+"";
            //构建Excel表格的表头
            List<String> headerList = Arrays.asList("课程ID","课程名","方向","描述","课时(h)","操作人");
            //构建表体数据
            List<Object[]> bodyList = new ArrayList<Object[]>();
            for(int i=0;courseList!=null && i<courseList.size();i++){
                Map<String, Object> courseMap = courseList.get(0);
                Object courseId = courseMap.get("courseId");
                Object courseName = courseMap.get("courseName");
                Object courseType = courseMap.get("courseType");
                Object description = courseMap.get("description");
                Object courseTime = courseMap.get("courseTime");
                Object username = courseMap.get("username");
                Object[] obj = {courseId,courseName,courseType,description,courseTime,username};
                bodyList.add(obj);
            }
            ExcelHelper.exportExcel(headerList,bodyList,fileName,response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

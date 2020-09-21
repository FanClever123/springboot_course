package com.java.mapper;

import com.java.pojo.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.*;

/**
 * 课程Mapper
 * time:14:49
 * author:丁鹏
 */
public interface CourseMapper {

    /**
     * 查询课程信息(users、catogory、courses三表联合查询)
     * @return
     */
    @Select("SELECT c.`courseType`,cr.`id` AS courseId,cr.`courseName`,\n" +
            "cr.`description`,cr.`courseTime`,u.`username` FROM catogory c \n" +
            "INNER JOIN courses cr \n" +
            "ON c.`id`=cr.`cID` INNER JOIN users u ON u.`id`=cr.`uID`")
    List<Map<String,Object>> selectCourseInfo();

    /**
     * 查询课程类别
     * @return
     */
    @Select("SELECT * FROM catogory")
    List<Map<String,Object>> selectCourseType();

    /**
     * 添加课程信息
     * @param c
     * @return
     */
    @Insert("INSERT INTO courses SET courseName=#{courseName},cID=#{cID}," +
            "description=#{description},courseTime=#{courseTime},uID=#{uID}")
    int insertCourseInfo(Course c);

    /**
     * 判断cID是否重复
     * @param cID
     * @return
     */
    @Select("select count(*) from catogory where id=#{arg0}")
    int selectCourseIDIsExist(String cID);

}

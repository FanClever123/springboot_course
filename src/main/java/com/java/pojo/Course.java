package com.java.pojo;

/**
 * 课程实体类
 * time:15:50
 * author:丁鹏
 */
public class Course {
    private String courseName;//课程名称
    private String cID;//课程类型ID(与catogory表中的主键一致)
    private String description;//课程描述
    private String courseTime;//课时
    private String uID;//操作人ID

    public Course() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}

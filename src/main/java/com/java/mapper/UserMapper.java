package com.java.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 用户DAO层
 * time:15:35
 * author:丁鹏
 */
public interface UserMapper {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT username,flag,id FROM users WHERE username=#{arg0} AND `password`=#{arg1}")
    Map<String,Object> selectLogin(String username, String password);

    /**
     * 查询所有管理员信息
     * @return
     */
    @Select("SELECT * FROM users")
    List<Map<String,Object>> selectUsers();

    /**
     * 删除
     * @param userId
     * @return
     */
    @Delete("DELETE FROM users WHERE id=#{arg0}")
    int deleteUser(String userId);

    /**
     * 添加用户
     * @param username
     * @param password
     * @return
     */
    @Insert("INSERT INTO users SET username=#{arg0},`password`=#{arg1}")
    int insertUser(String username,String password);

    /**
     * 查询用户名是否重复
     * @param username
     * @return
     */
    @Select("SELECT COUNT(*) FROM users WHERE username=#{arg0}")
    int selectUserNameIsExist(String username);
}

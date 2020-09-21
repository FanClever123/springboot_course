package com.java.service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * time:15:40
 * author:丁鹏
 */
public interface UserService {
    /**
     *登录
     * @param username
     * @param password
     * @return
     */
    boolean findLogin(String username, String password,HttpSession session);

    /**
     * 查询所有管理员信息
     * @return
     */
    List<Map<String,Object>> findUsers();

    /**
     * 删除
     * @param userId
     * @return
     */
    Map<String,Object> remove(String userId);

    /**
     * 添加用户
     * @param username
     * @param password
     * @return
     */
    Map<String,Object> saveUser(String username,String password);
}

package com.java.controller;

import com.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * time:15:32
 * author:丁鹏
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 登录
     * @param username  账号
     * @param password  明文密码
     * @return
     */
    @RequestMapping("/login")
    public String login(String username,String password,HttpSession session){
        boolean flag = userService.findLogin(username, password,session);
        if(flag){//登录成功
            return "redirect:/pages/admin/server.jsp";
        }else{//登录失败
            return "redirect:/pages/admin/index.jsp";
        }
    }

    /**
     * 查询所有管理员信息
     * @return
     */
    @RequestMapping("/getUsers")
    public String getUsers(HttpServletRequest request){
        List<Map<String, Object>> userList = userService.findUsers();
        request.setAttribute("userList",userList);
        return "admin/selectUsers.jsp";
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @RequestMapping("/scUser")
    public @ResponseBody Map<String,Object> scUser(String userId){
        return userService.remove(userId);
    }

    /**
     * 添加管理员
     * @param username 用户名
     * @param password  明文密码
     * @return
     */
    @RequestMapping("/addUser")
    public @ResponseBody Map<String,Object> addUser(String username,String password){
        return userService.saveUser(username,password);
    }

}

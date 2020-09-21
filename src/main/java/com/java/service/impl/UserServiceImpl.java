package com.java.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.java.mapper.UserMapper;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * time:15:36
 * author:丁鹏
 */
@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements com.java.service.UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean findLogin(String username, String password,HttpSession session){
        //1、数据校验
        if(username==null || password==null){
            return false;
        }
        if(!(username.matches("\\w{3,12}")) || !(password.matches("\\w{5,12}"))){
            return false;
        }
        //2、调用DAO层
        Map<String, Object> resultMap = userMapper.selectLogin(username, SecureUtil.md5(password));
        if(resultMap==null || resultMap.size()==0){
            return false;
        }
        session.setAttribute("flag",resultMap.get("flag"));
        session.setAttribute("username",username);
        session.setAttribute("uID",resultMap.get("id"));
        return true;
    }

    /**
     * 查询所有管理员信息
     * @return
     */
    public List<Map<String,Object>> findUsers(){
        return userMapper.selectUsers();
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    public Map<String,Object> remove(String userId){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("flag",true);//删除一切ok
        //1、数据校验
        if(userId==null || !(userId.matches("[1-9]\\d*"))){
            resultMap.put("flag",false);
            resultMap.put("msg","亲，不要动我的代码哦!");
            return resultMap;
        }
        //2、调用DAO层
        int i = userMapper.deleteUser(userId);
        if(i!=1){
            resultMap.put("flag",false);
            resultMap.put("msg","亲，俺现在好忙，您稍等!");
            return resultMap;
        }
        return resultMap;
    }

    /**
     * 添加用户
     * @param username
     * @param password
     * @return
     */
    public Map<String,Object> saveUser(String username,String password){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("flag",true);//添加成功
        //1、数据校验
        if(username==null || password==null){
            resultMap.put("flag",false);
            resultMap.put("msg","用户名或密码不能为空!");
            return resultMap;
        }
        if(!(username.matches("\\w{3,12}")) || !(password.matches("\\w{5,12}"))){
            resultMap.put("flag",false);
            resultMap.put("msg","用户名或密码格式错误!");
            return resultMap;
        }
        //2、查看用户名在数据库中是否存在
        int i = userMapper.selectUserNameIsExist(username);
        if(i==1){//此用户名已经被注册
            resultMap.put("flag",false);
            resultMap.put("msg","用户名已被注册!");
            return resultMap;
        }
        //3、如果用户名唯一，则将数据保存到数据库中去
        int j = userMapper.insertUser(username, SecureUtil.md5(password));
        if(j==0){
            resultMap.put("flag",false);
            resultMap.put("msg","数据库后台错误!");
            return resultMap;
        }
        return resultMap;
    }

}

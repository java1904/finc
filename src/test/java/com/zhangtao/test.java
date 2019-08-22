package com.zhangtao;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class test {
    @Test
    public void shiroFirst(){
        //根据ini初始化文件创建SecurityManager工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
        //使用工厂创建securityManager实例
        SecurityManager instance = factory.getInstance();
        //使用工具类设置securityManager运行环境
        SecurityUtils.setSecurityManager(instance);
        //获取Subject实例
        Subject subject = SecurityUtils.getSubject();
        //设置用户名
        //封装一个用户信息的令牌（用户名，密码），相当于在controller获得用户的登陆信息并封装
        String username="lisi";
        String pwd ="456";
        AuthenticationToken token = new UsernamePasswordToken(username, pwd);
        try {
//			用户登录
            subject.login(token);
        }catch(UnknownAccountException ue){
            System.out.println("账号："+username+"不存在");
        }catch(IncorrectCredentialsException ice){
            System.out.println("不正确的用户凭证！");
        }catch (AuthenticationException e) {
            e.printStackTrace();
        }
        if(subject.isAuthenticated()){
            System.out.println("zhangsan认证通过！");
        }else{
            System.out.println("zhangsan认证未通过！");
        }
    }
    @Test
    public void md5Test(){
        IniSecurityManagerFactory factory= new IniSecurityManagerFactory("classpath:shiro-realm-md5.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        String username="zhangsan";
        AuthenticationToken token = new UsernamePasswordToken(username, "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(subject.isAuthenticated()){
            System.out.println(username+"认证通过！");
        }
    }

}

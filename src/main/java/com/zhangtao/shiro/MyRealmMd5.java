package com.zhangtao.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealmMd5 extends AuthorizingRealm {
    public String getName(){
        return this.getClass().getName();
    }
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String  username = (String) authenticationToken.getPrincipal();
        String salt="zhangsan";
//		获取加盐后的密码
        String hashedpwd= "4e7bdb88640b376ac6646b8f1ecfb558";
//		将送来用户账号及根据账号查出的密码（凭证）封装成一个AuthenticationInfo对象，返回
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, hashedpwd, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }


}

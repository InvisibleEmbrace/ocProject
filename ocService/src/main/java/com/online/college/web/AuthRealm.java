package com.online.college.web;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.storage.ThumbModel;
import com.online.college.common.web.SessionContext;
import com.online.college.common.web.auth.SessionUser;
import com.online.college.pojo.AuthUser;
import com.online.college.service.IAuthUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     *  doGetAuthorizationInfo(PrincipalCollection principals)：表示根据用户身份获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection != null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        // 获取当前登录用户
        SessionUser user = SessionContext.getAuthUser();
        if (user == null) {
            return null;
        }
        //设置权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户权限并设置 以供shiro框架
        info.setStringPermissions(user.getPermissions());
        return info;
    }

    /**
     * doGetAuthenticationInfo(AuthenticationToken token)：表示获取身份验证信息
     * 实现用户登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        AuthUser authUser = null;
        /**
         * 业务代码-start
         */
        try {
            AuthUser tmpAuthUser = new AuthUser();
            tmpAuthUser.setUsername(username);
            tmpAuthUser.setPassword(password);

            tmpAuthUser = iAuthUserService.getByUsernameAndPassword(tmpAuthUser);
            if(null != tmpAuthUser){
                authUser = new AuthUser();
                authUser.setId(tmpAuthUser.getId());
                authUser.setRealname(tmpAuthUser.getRealname());
                authUser.setUsername(tmpAuthUser.getUsername());
                authUser.setStatus(tmpAuthUser.getStatus());
                if(!StringUtils.isBlank(tmpAuthUser.getHeader())){
                    authUser.setHeader(QiniuStorage.getUrl(tmpAuthUser.getHeader(),ThumbModel.THUMB_256));//设置头像
                }
            }else{
                throw new AuthenticationException("## user password is not correct! ");
            }
        } catch (Exception e) {
            throw new AuthenticationException("## user password is not correct! ");
        }
        //业务代码-end
        // 设置用户权限信息
		/*try {
			authUser.setPermissions(permissions);
		} catch (Exception e) {
			throw new AuthenticationException("## user permission setter exception! ");
		}*/
        // 创建授权用户
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authUser, password, getName());
        return info;
    }
}

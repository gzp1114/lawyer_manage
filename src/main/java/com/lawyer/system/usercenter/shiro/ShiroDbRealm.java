package com.lawyer.system.usercenter.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.lawyer.cores.result.Results;
import com.lawyer.system.usercenter.domain.ShiroUser;
import com.lawyer.system.usercenter.domain.SysRole;
import com.lawyer.system.usercenter.domain.SysUserSession;
import com.lawyer.system.usercenter.service.SysRoleService;
import com.lawyer.system.usercenter.service.SysUserSessionService;

public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
	
	private static final int INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private static final String ALGORITHM = "SHA-1";

	private SysUserSessionService userSessionService;
	
	private SysRoleService roleService;
	
	/**
	 * 给ShiroDbRealm提供编码信息，用于密码密码比对
	 * 描述
	 */	
	public ShiroDbRealm() {
		super();
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
		matcher.setHashIterations(INTERATIONS);

		setCredentialsMatcher(matcher);
	}
	
	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		logger.info("shiro认证回调函数, 登录时调用");
		
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		String userName = token.getUsername();
		try {
			ShiroUser shiroUser = new ShiroUser();
			
			Results result = userSessionService.findByLoginAccount(userName);
			SysUserSession user = (SysUserSession) result.getData();
			if (user != null) {
				if (user.getStatus() != 1) {
					throw new DisabledAccountException();
				}
				token.setPassword(user.getLoginPassword().toCharArray());
				
				shiroUser.setLoginName(String.valueOf(userName));
				shiroUser.setUser(user);
				
				logger.info("用户"+userName+"登录验证成功");
				return new SimpleAuthenticationInfo(shiroUser, user.getLoginPassword(),getName());
			} else {
				throw new DisabledAccountException();
			}
		} catch (AuthenticationException e) {
			logger.error("用户"+userName+"登录验证异常："+e.getMessage());
		}
		return null;
		
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("授权查询回调函数");
		
		try {
			SysUserSession userSession = null;
			Object obj = principals.fromRealm(getName()).iterator().next();
			if(obj instanceof ShiroUser){
				userSession = ((ShiroUser) obj).getUser();
			}
//			else if(obj instanceof String){
//				Results result = userSessionService.findByLoginAccount((String) obj);
//				userSession = (SysUserSession) result.getData();
//			}
			
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<SysRole> rolelist = userSessionService.selectUserRoles(userSession.getLoginAccount()).getRoleList();
			if (!rolelist.isEmpty()) {
				for (SysRole sysRole : rolelist) {
					List<String> permissionlist = roleService.selectRolePermissionList(sysRole.getId());
					info.addStringPermissions(permissionlist);
				}
				return info;
			} else {
				return null;
			}
			
		} catch (UnauthorizedException e) {
			logger.error("用户"+getName()+"权限验证异常："+e.getMessage());
		}
		return null;
	}
	
	@Override
	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException{
		String tokenPassword = String.copyValueOf((char[]) token.getCredentials());
//		String infoCredentials =  String.copyValueOf((char[]) info.getCredentials());
		if(!tokenPassword.equals(info.getCredentials())){
			 String msg = "Submitted credentials for token [" + token + "] did not match the expected credentials.";
             throw new IncorrectCredentialsException(msg);
		}
	}
	
	public static class HashPassword {
		public String salt;
		public String password;
	}
	
	public HashPassword encrypt(String plainText) {
		HashPassword result = new HashPassword();
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		result.salt = Encodes.encodeHex(salt);

		byte[] hashPassword = Digests.sha1(plainText.getBytes(), salt, INTERATIONS);
		result.password = Encodes.encodeHex(hashPassword);
		return result;

	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}


	public SysUserSessionService getUserSessionService() {
		return userSessionService;
	}

	public void setUserSessionService(SysUserSessionService userSessionService) {
		this.userSessionService = userSessionService;
	}

	public void setRoleService(SysRoleService roleService) {
		this.roleService = roleService;
	}

}

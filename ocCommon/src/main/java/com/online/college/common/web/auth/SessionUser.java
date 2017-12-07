package com.online.college.common.web.auth;

import java.util.Set;


/**
 * 权限用户
 */
public interface SessionUser {
	
	String getUsername();
	
	Integer getUserId();
	
	Set<String> getPermissions();
	
}

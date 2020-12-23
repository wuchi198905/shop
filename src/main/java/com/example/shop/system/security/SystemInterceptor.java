package com.example.shop.system.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SystemInterceptor implements HandlerInterceptor {

//	@Autowired
//	private IUserLogMDAO userLogMDAO;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		if (!SessionUser.isLogined()) {
//			response.sendRedirect(request.getContextPath() + "/system.html");
//			return false;
//		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String  str = handler.toString();
		String strMVO = "";
		if(str.indexOf("update")>0 || str.indexOf("audit")>0){
			strMVO = str.substring(str.lastIndexOf(".")+1,str.indexOf("MVO"));
			//userOperateLog("更新", UserLog.OPERATE_TYPE_UPDATE, strMVO);
		}
		if(str.indexOf("add")>0){
			strMVO = str.substring(str.lastIndexOf(".")+1,str.indexOf("MVO"));
			//userOperateLog("新增", UserLog.OPERATE_TYPE_ADD, strMVO);
		}
		if(str.indexOf("delete")>0){
			strMVO = str.substring(str.lastIndexOf(".")+1,str.indexOf("MVO"));
			//userOperateLog("删除", UserLog.OPERATE_TYPE_DELETE, strMVO);
		}
	}
	
	public void userOperateLog(String operate, String operateType, String operateTable){
//		UserLogMVO userLog = new UserLogMVO();
//    	userLog.setUserId(SessionUser.get(SessionUser.USER_ID));
//    	userLog.setUsername(SessionUser.get(SessionUser.USERNAME));
//    	userLog.setContent(operate + "：" + operateTable);
//    	userLog.setOperateType(operateType);
//    	userLog.setIpaddr(HttpHelper.getIpAddr());
//    	userLog.setCreateTime(DateUtil.now(DateUtil.TIME_FORMATTER));
//    	userLogMDAO.insert(userLog);
		//System.out.println(operate + "：" +operateTable+"表数据");
	}

}

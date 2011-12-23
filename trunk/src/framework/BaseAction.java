/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package framework;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BaseAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response)
	    throws Exception
	{
		//取得action名称
		//uncertain
		String actionName = request.getServletPath().replace("/", "").replace(".do", "");
		Class cls = this.getClass();
		//设置调用函数的参数类型
		Class[] paraTypes = new Class[4];
		paraTypes[0] = ActionMapping.class;
		paraTypes[1] = ActionForm.class;
		paraTypes[2] = HttpServletRequest.class;
		paraTypes[3] = HttpServletResponse.class;
		//取得调用函数
		Method method = cls.getMethod(actionName, paraTypes);
		//设置函数参数值
		Object[] args = new Object[4];
		args[0] = mapping;
		args[1] = form;
		args[2] = request;
		args[3] = response;
		//调用函数获得返回值
		//注意这里的this是对象实例而不是类本身！
		Object result = method.invoke(this, args);
		//返回函数结果
		return (ActionForward)result;
	}
}

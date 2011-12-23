/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse httpservletresponse)
	    throws Exception
	{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		ActionForward actionForward = null;
		if((name!=null&&name.equalsIgnoreCase("leon")==true)&&(password!=null&&password.equalsIgnoreCase("0098")==true)){
			actionForward = mapping.findForward("success");
		}else{
			actionForward = mapping.findForward("fail");
		}
		return actionForward;
	}
}

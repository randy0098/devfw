/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import to.MessageTO;
import dao.MessageDAO;
import framework.BaseAction;


public class MessageAction extends BaseAction
{
	/**
	 * 
	 * 短信记录查询
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward message_query(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse httpservletresponse)
	    throws Exception
	{
		ArrayList messages = MessageDAO.getMessageList();
		request.setAttribute("messages", messages);
		ActionForward forward = mapping.findForward("success");
		return forward;
	}
	
	/**
	 * 
	 * 短信记录新增
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward message_insert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse httpservletresponse)
	    throws Exception
	{
		MessageTO message = new MessageTO();
		DynaActionForm messageForm = (DynaActionForm)form;
		BeanUtils.copyProperties(message, messageForm);
		message.setMsg_time("20111226");
		MessageDAO.insertMessage(message);
		ActionForward forward = mapping.findForward("success");
		return forward;
	}
	
	/**
	 * 
	 * 查询单条短信记录信息
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward message_selectOne(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse httpservletresponse)
	    throws Exception
	{
		MessageTO messageTO = MessageDAO.getMessageInfo(request.getParameter("id"));
		request.setAttribute("messageTO", messageTO);
		ActionForward forward = mapping.findForward("success");
		return forward;
	}
}

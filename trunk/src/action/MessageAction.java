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
import util.OraclePage;
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
//		ArrayList messages = MessageDAO.getMessageList();
//		request.setAttribute("messages", messages);
		
		request.setAttribute("action", "go");
		request.setAttribute("currentPageIndex", "1");
		message_page(mapping,form,request,httpservletresponse);
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
	
	/**
	 * 
	 * 保存单条短信记录信息
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward message_update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse httpservletresponse)
	    throws Exception
	{
		MessageTO message = new MessageTO();
		DynaActionForm messageForm = (DynaActionForm)form;
		BeanUtils.copyProperties(message, messageForm);
		message.setMsg_time("20111227");
		MessageDAO.updateMessage(message);
		ActionForward forward = mapping.findForward("success");
		return forward;
	}
	
	
	/**
	 * 
	 * 删除单条短信记录信息
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward message_delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse httpservletresponse)
	    throws Exception
	{
		MessageDAO.deleteMessage(request.getParameter("id"));
		ActionForward forward = mapping.findForward("success");
		return forward;
	}
	
	
	/**
	 * 
	 * 翻页
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param httpservletresponse
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward message_page(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse httpservletresponse)
	    throws Exception
	{
		String action = request.getParameter("action");
		String currentPageIndex = request.getParameter("currentPageIndex");
		OraclePage page = new OraclePage();
		page.setQuerySql("SELECT * FROM devfw_message");
		page.setCountSql("SELECT COUNT(ID) AS n FROM devfw_message");
		page.setTOClassName("to.MessageTO");
		page.setPageRecordNum(1);
		if(action!=null && action.equalsIgnoreCase("goToFirst")==true){
			page.goToFirst();
		}
		else if(action!=null && action.equalsIgnoreCase("goToLast")==true){
			page.goToLast();
		}
		else if(action!=null && action.equalsIgnoreCase("back")==true){
			page.setCurrentPageIndex(Integer.parseInt(currentPageIndex));
			page.back();
		}
		else if(action!=null && action.equalsIgnoreCase("next")==true){
			page.setCurrentPageIndex(Integer.parseInt(currentPageIndex));
			page.next();
		}
		else if(action!=null && action.equalsIgnoreCase("go")==true){
			page.go(Integer.parseInt(currentPageIndex));
		}
		request.setAttribute("page", page);
		ActionForward forward = mapping.findForward("success");
		return forward;
	}
}

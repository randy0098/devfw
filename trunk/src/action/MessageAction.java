/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import to.MessageTO;
import framework.BaseAction;
import framework.DAOController;

public class MessageAction extends BaseAction
{
	public ActionForward message_query(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse httpservletresponse)
	    throws Exception
	{
		DAOController controller = new DAOController();
		String sql = "SELECT * FROM DEVFW_MESSAGE";
		ResultSet rs = controller.select(sql);
    	ArrayList messages = new ArrayList();
		try
        {
	        while (rs.next())
	        {
	        	String id = rs.getString("ID");
	        	String sender = rs.getString("SENDER");
	        	String receiver = rs.getString("RECEIVER");
	        	String content = rs.getString("CONTENT");
	        	String msg_time = rs.getString("MSG_TIME");
	        	System.out.println("id:" + id);
	        	System.out.println("sender:" + sender);
	        	System.out.println("receiver:" + receiver);
	        	System.out.println("content:" + content);
	        	System.out.println("msg_time:" + msg_time);
	        	System.out.println("######################");
	    		MessageTO messageTO = new MessageTO();
	    		messageTO.setId(id);
	    		messageTO.setSender(sender);
	    		messageTO.setReceiver(receiver);
	    		messageTO.setContent(content);
	    		messageTO.setMsg_time(msg_time);
	        	messages.add(messageTO);
	        }
	        request.setAttribute("messages", messages);
	        System.out.println("messages:" + messages.size());
	        controller.close();
        }
        catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		ActionForward forward = mapping.findForward("success");
		return forward;
	}
}

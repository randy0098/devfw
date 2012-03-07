/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import framework.BaseVO;


public class MessageVO extends BaseVO
{
	private String id;
	private String sender;
	private String receiver;
	private String content;
	private String msg_time;
	
	public String getId()
    {
    	return id;
    }
	public void setId(String id)
    {
    	this.id = id;
    }
	public String getSender()
    {
    	return sender;
    }
	public void setSender(String sender)
    {
    	this.sender = sender;
    }
	public String getReceiver()
    {
    	return receiver;
    }
	public void setReceiver(String receiver)
    {
    	this.receiver = receiver;
    }
	public String getContent()
    {
    	return content;
    }
	public void setContent(String content)
    {
    	this.content = content;
    }
	public String getMsg_time()
    {
    	return msg_time;
    }
	public void setMsg_time(String msg_time)
    {
    	this.msg_time = msg_time;
    }
	
	@Override
    public BaseVO buildTO(ResultSet rs) throws SQLException
    {
	    // TODO Auto-generated method stub
		MessageVO to = new MessageVO();
		to.setId(rs.getString("ID"));
		to.setSender(rs.getString("SENDER"));
		to.setReceiver(rs.getString("RECEIVER"));
		to.setContent(rs.getString("CONTENT"));
		to.setMsg_time(rs.getString("MSG_TIME"));
		return to;
    }
	
}

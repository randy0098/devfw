/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import to.MessageTO;
import framework.BaseDAO;
import framework.DAOController;


public class MessageDAO extends BaseDAO
{
	/**
	 * 
	 * 查询短信记录列表
	 *
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws SQLException 
	 */
	public static ArrayList getMessageList() throws CloneNotSupportedException, SQLException{
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
	    		MessageTO messageTO = new MessageTO();
	    		messageTO.setId(id);
	    		messageTO.setSender(sender);
	    		messageTO.setReceiver(receiver);
	    		messageTO.setContent(content);
	    		messageTO.setMsg_time(msg_time);
	        	messages.add(messageTO.clone());
	        }
	        controller.close();
        }
        catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return messages;
	}
	
	/**
	 * 
	 * 短信记录保存
	 *
	 * @param form
	 * @return
	 * @throws SQLException 
	 */
	public static boolean insertMessage(MessageTO message) throws SQLException{
		DAOController controller = new DAOController();
		String values = "'"+message.getSender()+"'," + "'"+message.getReceiver()+"'," + "'"+message.getContent()+"'," + "'"+message.getMsg_time()+"'";
		String sql = "INSERT INTO DEVFW_MESSAGE(SENDER,RECEIVER,CONTENT,MSG_TIME) VALUES("+ values + ")";
		System.out.println("insertMessage:" + sql);
		boolean result = controller.insert(sql);
		controller.close();
		return result;
	}
	
	
	public static void main(String[] args) throws SQLException
	{
		DAOController controller = new DAOController();
		String sql = "SELECT * FROM DEVFW_MESSAGE";
		ResultSet rs = controller.select(sql);
		try
        {
	        while (rs.next())
	        {
	        	String ID = rs.getString("ID");
	        	String SENDER = rs.getString("SENDER");
	        	String RECEIVER = rs.getString("RECEIVER");
	        	String CONTENT = rs.getString("CONTENT");
	        	String MSG_TIME = rs.getString("MSG_TIME");
	        	System.out.println("ID:" + ID);
	        	System.out.println("SENDER:" + SENDER);
	        	System.out.println("RECEIVER:" + RECEIVER);
	        	System.out.println("CONTENT:" + CONTENT);
	        	System.out.println("MSG_TIME:" + MSG_TIME);
	        	System.out.println("######################");
	        }
	        controller.close();
        }
        catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
}

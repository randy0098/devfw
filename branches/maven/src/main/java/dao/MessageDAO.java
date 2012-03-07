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

import vo.MessageVO;
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
        while (rs.next())
        {
        	String id = rs.getString("ID");
        	String sender = rs.getString("SENDER");
        	String receiver = rs.getString("RECEIVER");
        	String content = rs.getString("CONTENT");
        	String msg_time = rs.getString("MSG_TIME");
    		MessageVO messageVO = new MessageVO();
    		messageVO.setId(id);
    		messageVO.setSender(sender);
    		messageVO.setReceiver(receiver);
    		messageVO.setContent(content);
    		messageVO.setMsg_time(msg_time);
        	messages.add(messageVO.clone());
        }
        controller.close();
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
	public static boolean insertMessage(MessageVO message) throws SQLException{
		DAOController controller = new DAOController();
		String values = "'"+message.getSender()+"'," + "'"+message.getReceiver()+"'," + "'"+message.getContent()+"'," + "'"+message.getMsg_time()+"'";
		String sql = "INSERT INTO DEVFW_MESSAGE(SENDER,RECEIVER,CONTENT,MSG_TIME) VALUES("+ values + ")";
		System.out.println("insertMessage:" + sql);
		boolean result = controller.insert(sql);
		controller.close();
		return result;
	}
	
	/**
	 * 
	 * 查询单条短信记录
	 *
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws SQLException 
	 */
	public static MessageVO getMessageInfo(String id) throws CloneNotSupportedException, SQLException{
		DAOController controller = new DAOController();
		String sql = "SELECT * FROM DEVFW_MESSAGE WHERE ID = " + id;
		System.out.println("getMessageInfo:" + sql);
		ResultSet rs = controller.selectOne(sql);
		MessageVO messageVO = new MessageVO();
        while (rs.next())
        {
        	String sender = rs.getString("SENDER");
        	String receiver = rs.getString("RECEIVER");
        	String content = rs.getString("CONTENT");
        	String msg_time = rs.getString("MSG_TIME");
    		messageVO.setId(id);
    		messageVO.setSender(sender);
    		messageVO.setReceiver(receiver);
    		messageVO.setContent(content);
    		messageVO.setMsg_time(msg_time);
        }
        controller.close();
		return messageVO;
	}
	
	
	/**
	 * 
	 * 保存单条短信记录
	 *
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws SQLException 
	 */
	public static int updateMessage(MessageVO message) throws CloneNotSupportedException, SQLException{
		DAOController controller = new DAOController();
		String updateSql = "SENDER = '"+message.getSender()+"'," 
						+ "RECEIVER = '"+message.getReceiver()+"',"
						+ "CONTENT = '"+message.getContent()+"',"
						+ "MSG_TIME = '"+message.getMsg_time()+"'";
		String sql = "UPDATE DEVFW_MESSAGE SET " + updateSql + " WHERE ID = " + message.getId();
		System.out.println("updateMessage:" + sql);
		int result = controller.update(sql);
		controller.close();
		return result;
	}
	
	/**
	 * 
	 * 删除单条短信记录
	 *
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws SQLException 
	 */
	public static boolean deleteMessage(String id) throws CloneNotSupportedException, SQLException{
		DAOController controller = new DAOController();
		String sql = "DELETE FROM DEVFW_MESSAGE WHERE ID = " + id;
		System.out.println("deleteMessage:" + sql);
		boolean result = controller.delete(sql);
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
	        	System.out.println("0:"+rs.getString(1));
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

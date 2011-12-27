/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import framework.BaseTO;
import framework.Page;

import to.MessageTO;

public class OraclePage extends Page
{
	
	/**
	 * 
	 * 创建查询语句
	 *
	 */
	public void createSelectSql(){
		String sql = "SELECT * ";
		sql = sql + " FROM (SELECT ROWNUM R,t1.* From devfw_message t1 where rownum <= "+this.endRecordIndex+" ) t2 ";
		sql = sql + " Where t2.R >= "+ this.startRecordIndex;
		this.createdQuerySql = sql;
	}
	
	/**
	 * 
	 * 将记录封装成TO对象
	 *
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public BaseTO buildTO(ResultSet rs) throws SQLException{
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
		return messageTO;
	}


	public static void main(String[] args) throws SQLException, CloneNotSupportedException
	{
		// TODO Auto-generated method stub
		OraclePage page = new OraclePage();
		page.setQuerySql("SELECT * FROM devfw_message");
		page.setCountSql("SELECT COUNT(ID) AS n FROM devfw_message");
		page.setPageRecordNum(10);
		page.setCurrentPageIndex(2);
		page.createPage();
	}
}

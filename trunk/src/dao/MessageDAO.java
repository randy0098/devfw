/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import framework.BaseDAO;
import framework.DAOController;

public class MessageDAO extends BaseDAO
{

	public static void main(String[] args)
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

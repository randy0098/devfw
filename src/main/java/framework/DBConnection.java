/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static String user = "system";
	private static String password = "system";
	
	/**
	 * 
	 * 获得数据库连接
	 * 
	 */
	public static Connection getConnection(){
		Connection conn = null;
        try
        {
	        Class.forName(driver);
	        conn = DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return conn;
	}
	
	
}

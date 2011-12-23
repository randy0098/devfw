/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package framework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOController
{
	private Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public DAOController(){
		conn = DBConnection.getConnection();
	}
	
	public boolean insert(String sql){
		boolean result = false;
		try
        {
	        ps = conn.prepareStatement(sql);
	        result = ps.execute();
        }
        catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        return result;
	}
	
	/**
	 * 
	 * 修改记录
	 *
	 */
	public int update(String sql){
		int result = 0;
		try
        {
	        ps = conn.prepareStatement(sql);
	        result = ps.executeUpdate();
        }
        catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return result;
	}
	
	/**
	 * 
	 * 查询记录
	 *
	 */
	public ResultSet select(String sql){
		try
        {
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return rs;
	}
	
	/**
	 * 
	 * 删除记录
	 *
	 */
	public boolean delete(String sql){
		boolean result = false;
		try
        {
	        ps = conn.prepareStatement(sql);
	        result = ps.execute();
        }
        catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        return result;
	}
	
	public void close(){
		if(rs!=null){
			try
            {
	            rs.close();
            }
            catch (SQLException e)
            {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
            }
		}
		if(ps!=null){
			try
            {
	            rs.close();
            }
            catch (SQLException e)
            {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
            }
		}
		if(conn!=null){
			try
            {
	            rs.close();
            }
            catch (SQLException e)
            {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
            }
		}
	}
}

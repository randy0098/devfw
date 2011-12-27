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

	public DAOController()
	{
		conn = DBConnection.getConnection();
	}

	public boolean insert(String sql)
	    throws SQLException
	{
		boolean result = false;
		ps = conn.prepareStatement(sql);
		result = ps.execute();
		return result;
	}

	/**
	 * 
	 * 修改记录
	 * @throws SQLException 
	 *
	 */
	public int update(String sql)
	    throws SQLException
	{
		int result = 0;
		ps = conn.prepareStatement(sql);
		result = ps.executeUpdate();
		return result;
	}

	/**
	 * 
	 * 查询记录
	 * @throws SQLException 
	 *
	 */
	public ResultSet select(String sql)
	    throws SQLException
	{
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		return rs;
	}

	/**
	 * 
	 * 查询单条记录
	 * @throws SQLException 
	 *
	 */
	public ResultSet selectOne(String sql)
	    throws SQLException
	{
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		return rs;
	}
	
	/**
	 * 
	 * 删除记录
	 * @throws SQLException 
	 *
	 */
	public boolean delete(String sql)
	    throws SQLException
	{
		boolean result = false;
		ps = conn.prepareStatement(sql);
		result = ps.execute();
		return result;
	}

	public void close()
	    throws SQLException
	{
		if (rs != null)
		{
			rs.close();
		}
		if (ps != null)
		{
			ps.close();
		}
		if (conn != null)
		{
			conn.close();
		}
	}
}

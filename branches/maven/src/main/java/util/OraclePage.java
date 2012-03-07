/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package util;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import framework.Page;

public class OraclePage extends Page
{
	
	/**
	 * 
	 * 创建查询语句
	 *
	 */
	public void createSelectSql(){
		String sql1 = " SELECT *  FROM (SELECT ROWNUM R ,t1.* from ( ";
		String sql2 = " ) t1 WHERE ROWNUM <= " + this.endRecordIndex + " ) t2 ";
		String sql3 = " Where t2.R >= " + this.startRecordIndex;
		String resultSql = sql1+this.querySql+sql2+sql3;
		System.out.println("createdQuerySql:" + resultSql);
		this.createdQuerySql = resultSql;
	}


	public static void main(String[] args) throws SQLException, CloneNotSupportedException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		OraclePage page = new OraclePage();
		page.setQuerySql("SELECT * FROM devfw_message WHERE 1=1 AND id = 8");
		page.setCountSql("SELECT COUNT(ID) AS n FROM devfw_message");
		page.setPageRecordNum(10);
		page.setCurrentPageIndex(1);
		page.setTOClassName("vo.MessageVO");
		page.createPage();
		System.out.println(page.getRecords().size());
		System.out.println(page.getRecordNum());
		
//		String str = "select * from a".split("from")[0];
//		System.out.println(str);
		
//		OraclePage page = new OraclePage();
//		page.setQuerySql("SELECT id as test,content FROM devfw_message");
//		page.setPageRecordNum(10);
//		page.setCurrentPageIndex(1);
//		page.createSelectSql();
//		System.out.println(page.getCreatedQuerySql());
	}
	
	
}

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
		String sql1 = " SELECT * ";
		String sql2 = " FROM (SELECT ROWNUM R ";
		String sql3 = " t1 WHERE ROWNUM <= " + this.endRecordIndex + " ) t2 ";
		String sql4 = " Where t2.R >= " + this.startRecordIndex;
		//以"from"进行分隔
		String[] sqls = this.querySql.toLowerCase().split("from");
		//去除多余字符
		String[] fields = sqls[0].replaceAll("select", "").split(",");
		//生成oracle分页查询中所需的字段列表
		String newFields = "";
		for(int i=0; i<fields.length; i++){
			newFields = newFields + ",t1." + fields[i];
		}
		String sql5 = " from " + sqls[1];
		String resultSql = sql1+sql2+newFields+sql5+sql3+sql4;
		System.out.println("createdQuerySql:" + resultSql);
		this.createdQuerySql = resultSql;
	}


	public static void main(String[] args) throws SQLException, CloneNotSupportedException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		OraclePage page = new OraclePage();
		page.setQuerySql("SELECT * FROM devfw_message");
		page.setCountSql("SELECT COUNT(ID) AS n FROM devfw_message");
		page.setPageRecordNum(10);
		page.setCurrentPageIndex(1);
		page.setTOClassName("to.MessageTO");
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

/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package framework;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Page
{
	//记录总数
	protected int recordNum;
	//每页显示记录数量
	protected int pageRecordNum;
	//页面数量
	protected int totalPage;
	//当前页面序号
	protected int currentPageIndex;
	//当前页面的记录集合
	protected ArrayList records = new ArrayList();
	//本页开始记录序号
	protected int startRecordIndex;
	//本页截止记录序号
	protected int endRecordIndex;
	//原始查询语句
	protected String querySql;
	//生成的实际查询语句
	protected String createdQuerySql;
	//查询记录数量的sql语句
	protected String countSql;
	
	public int getRecordNum()
    {
    	return recordNum;
    }
	public int getPageRecordNum()
    {
    	return pageRecordNum;
    }
	public void setPageRecordNum(int pageRecordNum)
    {
    	this.pageRecordNum = pageRecordNum;
    }
	public int getTotalPage()
    {
    	return totalPage;
    }
	public int getCurrentPageIndex()
    {
    	return currentPageIndex;
    }
	public void setCurrentPageIndex(int currentPageIndex)
    {
    	this.currentPageIndex = currentPageIndex;
    }
	public ArrayList getRecords()
    {
    	return records;
    }
	public int getStartRecordIndex()
    {
    	return startRecordIndex;
    }
	public int getEndRecordIndex()
    {
    	return endRecordIndex;
    }
	
	public String getQuerySql()
    {
    	return querySql;
    }
	public void setQuerySql(String querySql)
    {
    	this.querySql = querySql;
    }
	public String getCreatedQuerySql()
    {
    	return createdQuerySql;
    }
	public String getCountSql()
    {
    	return countSql;
    }
	public void setCountSql(String countSql)
    {
    	this.countSql = countSql;
    }
	
	/**
	 * 
	 * 创建页面
	 * @throws SQLException 
	 * @throws CloneNotSupportedException 
	 *
	 */
	public void createPage() throws SQLException, CloneNotSupportedException{
		//设置记录开始和截止序号
		//记录序号从1开始
		//当前页序号从1开始
		if(this.currentPageIndex>0 && this.pageRecordNum>0){
			this.startRecordIndex = (this.currentPageIndex-1)*this.pageRecordNum+1;
			this.endRecordIndex = this.currentPageIndex*this.pageRecordNum;	
			//生成查询语句
			this.createSelectSql();
			//查询并保存查询记录结果集
			this.records = this.selectRecords();
			//获得记录总数
			this.selectRecordsNum();
		}
	}
	
	/**
	 * 
	 * 查询记录总数
	 * @throws SQLException 
	 *
	 */
	public void selectRecordsNum() throws SQLException{
		DAOController controller = new DAOController();
		ResultSet rs = controller.selectOne(this.countSql);
		rs.next();
		int recordNum = rs.getInt(0);
		controller.close();
		this.recordNum = recordNum;
	}
	
	
	/**
	 * 
	 * 查询数据库获得记录列表信息
	 *
	 * @return
	 * @throws SQLException
	 * @throws CloneNotSupportedException 
	 */
	private ArrayList selectRecords() throws SQLException, CloneNotSupportedException{
		DAOController controller = new DAOController();
		ResultSet rs = controller.select(this.createdQuerySql);
		ArrayList list = new ArrayList();
		while(rs.next()){
			BaseTO to = this.buildTO(rs);
    		list.add(to.clone());
		}
		controller.close();
		return new ArrayList();
	}
	
	/**
	 * 
	 * 将记录封装成TO对象
	 *
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public abstract BaseTO buildTO(ResultSet rs) throws SQLException;
	
	/**
	 * 
	 * 创建查询语句
	 *
	 */
	public abstract void createSelectSql();
	

}

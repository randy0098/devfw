/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
	//用来保存记录信息的TO类名称
	protected String TOClassName;
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
	public String getTOClassName()
    {
    	return TOClassName;
    }
	public void setTOClassName(String tOClassName)
    {
    	TOClassName = tOClassName;
    }
	/**
	 * 
	 * 创建页面
	 * @throws SQLException 
	 * @throws CloneNotSupportedException 
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 *
	 */
	public void createPage() throws SQLException, CloneNotSupportedException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException{
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
			//获得记录总数和总页数
			this.calculateTotalPage();
		}
	}
	
	/**
	 * 
	 * 计算得到总页数
	 *
	 * @return
	 * @throws SQLException 
	 */
	public void calculateTotalPage() throws SQLException{
		//获得记录总数
		this.selectRecordsNum();
		//获得总页数
		if(this.pageRecordNum!=0){
			if(this.recordNum%this.pageRecordNum == 0){
				this.totalPage = this.recordNum/this.pageRecordNum;
			}else{		
				this.totalPage = this.recordNum/this.pageRecordNum+1;
			}
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
		//获得记录数量，注意字段序号是从1开始。
		int recordNum = rs.getInt(1);
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
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws ClassNotFoundException 
	 */
	private ArrayList selectRecords() throws SQLException, CloneNotSupportedException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException, ClassNotFoundException{
		DAOController controller = new DAOController();
		ResultSet rs = controller.select(this.createdQuerySql);
		ArrayList list = new ArrayList();
		//将数据库记录封装成TO对象
		Class cls = Class.forName(this.TOClassName);
		Class[] paraTypes = new Class[1];
		//设置调用函数的参数类型
		paraTypes[0] = ResultSet.class;
		//取得调用函数
		Method method = cls.getMethod("buildTO", paraTypes);
		//设置函数参数值
		Object[] args = new Object[1];

		while(rs.next()){
			args[0] = rs;
			//调用函数获得返回值
			BaseVO to = (BaseVO)method.invoke(cls.newInstance(), args);
			//返回函数结果
    		list.add(to.clone());
		}
		controller.close();
		return (ArrayList)list.clone();
	}
	
	/**
	 * 
	 * 创建查询语句
	 *
	 */
	public abstract void createSelectSql();
	
	/**
	 * 
	 * 转到首页
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws CloneNotSupportedException 
	 * @throws SQLException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 *
	 */
	public void goToFirst() throws IllegalArgumentException, SecurityException, SQLException, CloneNotSupportedException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException{
		this.setCurrentPageIndex(1);
		this.createPage();
	}
	
	/**
	 * 
	 * 转到尾页
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws CloneNotSupportedException 
	 * @throws SQLException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 *
	 */
	public void goToLast() throws IllegalArgumentException, SecurityException, SQLException, CloneNotSupportedException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException{
		//获得记录总数和总页数
		this.calculateTotalPage();
		this.currentPageIndex = this.totalPage;
		this.createPage();
	}
	
	/**
	 * 
	 * 下一页
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws CloneNotSupportedException 
	 * @throws SQLException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 *
	 */
	public void next() throws IllegalArgumentException, SecurityException, SQLException, CloneNotSupportedException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException{
		//获得记录总数和总页数
		this.calculateTotalPage();
		if(this.currentPageIndex<this.totalPage){
			this.currentPageIndex = this.currentPageIndex+1;
		}else{
			this.currentPageIndex = this.totalPage;
		}
		this.createPage();		
	}
	
	/**
	 * 
	 * 上一页
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws CloneNotSupportedException 
	 * @throws SQLException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 *
	 */
	public void back() throws IllegalArgumentException, SecurityException, SQLException, CloneNotSupportedException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException{
		if(this.currentPageIndex>1){
			this.currentPageIndex = this.currentPageIndex-1;
		}else{
			this.currentPageIndex = 1;
		}
		this.createPage();		
	}
	
	/**
	 * 
	 * 转到第几页
	 *
	 * @param pageIndex
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws SQLException
	 * @throws CloneNotSupportedException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 */
	public void go(int pageIndex) throws IllegalArgumentException, SecurityException, SQLException, CloneNotSupportedException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException{
		//获得记录总数和总页数
		this.calculateTotalPage();
		if(pageIndex<1){
			pageIndex = 1;
		}
		else if(pageIndex>this.totalPage){
			pageIndex = this.totalPage;
		}
		this.setCurrentPageIndex(pageIndex);
		this.createPage();
	}
}

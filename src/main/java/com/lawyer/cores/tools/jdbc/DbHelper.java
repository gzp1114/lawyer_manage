package com.lawyer.cores.tools.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.shiro.realm.ldap.LdapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单例类
 * 
 * @author DELL-PC
 * 
 */
public class DbHelper {
	private static Logger logger = LoggerFactory.getLogger(LdapUtils.class);
	
	private static Connection connection = null;


	/**
	 * 初始化连接池
	 */
	public static Connection getConnection(String dbUrl, String dbName,
			String userName, String password) {
		// 联结字符串
		String url = dbUrl + "/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * 释放资源
	 * 
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void closeResources(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	public static int select (String sql, Connection conn) {
		if (sql == null || conn == null) {
			return 0;
		}
		Statement stmt = null;
		ResultSet result = null;
		int col = 0;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			if(result.next())      
			 {      
				col = result.getInt(1);  
			}   
		} catch (SQLException sqle) {
			System.out.println(sqle);
		} catch (Exception e) {
		} finally {
			if(result!=null){
				try {
					result.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}	
		
		return col;
	}
	
	public static int insert (String sql, Connection conn) {
		if (sql == null || conn == null) {
			return 0;
		}
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException sqle) {
			System.out.println(sqle);
		} catch (Exception e) {
		} finally {
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}	
		
		return result;
	}
	
	public static int delete (String sql, Connection conn) {
		if (sql == null || conn == null) {
			return 0;
		}
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
		} catch (SQLException sqle) {
			System.out.println(sqle);
			return result;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return result;
	}

}

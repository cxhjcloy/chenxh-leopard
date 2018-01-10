package cn.chenxhcloud.multithread.threadexecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 
*   
* 项目名称：chenxh-leopard  
* 类名称：.DbService  
* @author：chenxh  
* 创建时间：2017年12月27日 下午6:01:54
* 描述：
*
 */
public class DbService {
	private static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://gz-cdb-e3mogspj.sql.tencentcdb.com:63643/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&autoReconnect=true";
		String username = "root";
		String password = "cxh234mca$";
		Connection conn = null;
		try {
			// classLoader,加载对应驱动
			Class.forName(driver); 
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static List<ThreadInfo> getPageData(Integer start, Integer end) {
		Connection conn = getConn();
		String sql = "select * from tb_threads limit ?,? ";
		PreparedStatement pstmt = null;
		List<ThreadInfo> datas = new ArrayList<>();
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ThreadInfo threadInfo = new ThreadInfo();
				threadInfo.setId(rs.getLong("id"));
				threadInfo.setDataId(rs.getLong("data_id"));
				threadInfo.setName(rs.getString("name"));
				threadInfo.setQueueSize(rs.getInt("queue_size"));
				threadInfo.setCounter(rs.getLong("counter"));
				threadInfo.setMessage(rs.getString("message"));
				threadInfo.setCrated(rs.getDate("created"));
				datas.add(threadInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return datas;
	}

	public static Long getCount() {
		Connection conn = getConn();
		String sql = "select 15000000";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long count = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}

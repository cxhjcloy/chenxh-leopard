package cn.chenxhcloud.utils.ssh;

import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

/**
 * 
*   
* 项目名称：chenxh-leopard  
* 类名称：.ScpDemo  
* @author：chenxh  
* 创建时间：2018年1月2日 下午6:05:23
* 描述：
*
 */
public class ScpDemo {
    private String dataServerIp = "111.230.233.152";
    private String dataServerUsername = "ubuntu";
    private String dataServerPassword = "cxh234mca$";
    
    public static void main(String[] args) {
    	ScpDemo scpDemo = new ScpDemo();
    	scpDemo.get("/home/ubuntu/myapps/snow-leopard/webapps/Dockerfile", "target");
    	scpDemo.put("src/main/java/cn/chenxhcloud/utils/ssh/ScpDemo.java", "/home/ubuntu/myapps/snow-leopard/webapps/");
	}

    /**
     * 拷贝远程主机文件到本机
     * @param remoteFile 远程文件
     * @param dist 本地目标文件
     */
	private void get(String remoteFile, String dist) {
		Connection conn = new Connection(dataServerIp);
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(dataServerUsername, dataServerPassword);
			if (isAuthenticated == false) {
				throw new IOException("Authentication failed,can not login");
			}
			SCPClient client = new SCPClient(conn);
			client.get(remoteFile, dist);			
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
     * 上传本地文件
     * @param localFile 本地文件
     * @param remoteFile 远程目标文件
     */
	private void put(String localFile, String remoteFile) {
		Connection conn = new Connection(dataServerIp);
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(dataServerUsername, dataServerPassword);
			if (isAuthenticated == false) {
				throw new IOException("Authentication failed,can not login");
			}
			SCPClient client = new SCPClient(conn);
			client.put(localFile, remoteFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package cn.chenxhcloud.utils.ssh;

/**
 * 
*   
* 项目名称：chenxh-utils  
* 类名称：cn.chenxhcloud.utils.ssh.SSHDemo  
* @author：chenxh  
* 创建时间：2018年1月2日 下午1:43:11
* 描述：
*
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SshDemo {
	
	private static final Logger log = Logger.getLogger("SSHDemo");
	
	public static String execShellScript(String host, String username, String password,String cmd, int port) throws IOException {
		log.info("running SSH cmd [" + cmd + "]");
		Connection connection = null;
		Session sess = null;
		InputStream stdout = null;
		BufferedReader br = null;
		StringBuffer buffer = new StringBuffer("exec result:");
		buffer.append(System.getProperty("line.separator"));
		try {
			connection = new Connection(host, port);
			connection.connect();
			if (!connection.authenticateWithPassword(username, password)) {
				throw new RuntimeException("authenticateWithPassword failed");
			}
			sess = connection.openSession();
			sess.execCommand(cmd);
			stdout = new StreamGobbler(sess.getStdout());
			br = new BufferedReader(new InputStreamReader(stdout));
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				buffer.append(line);
				buffer.append(System.getProperty("line.separator"));
				log.info("--"+line);
			}
		} finally {
			if (br != null) {
				br.close();
			}
			if (sess != null) {
				sess.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		String cmd = "cat /home/ubuntu/myapps/snow-leopard/webapps/Dockerfile";
		try {
			String info = execShellScript("111.230.233.152", "ubuntu", "cxh234mca$", cmd, 22);
			log.info(info);
			info = execShellScript("111.230.233.152", "ubuntu", "cxh234mca$", "ls -al", 22);
			log.info(info);
			info = execShellScript("111.230.233.152", "ubuntu", "cxh234mca$", "docker info", 22);
			log.info(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

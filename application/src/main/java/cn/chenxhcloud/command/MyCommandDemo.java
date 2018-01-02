package cn.chenxhcloud.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


/**
 * 
*   
* 项目名称：chenxh-leopard  
* 类名称：.MyCommandDemo  
* @author：chenxh  
* 创建时间：2018年1月2日 下午2:08:38
* 描述：
*
 */
public class MyCommandDemo {
	public static void executeNewFlow() {
        Runtime run = Runtime.getRuntime();
        File wd = new File("/bin");
        System.out.println(wd);
        Process proc = null;
        try {
            proc = run.exec("/bin/bash", null, wd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (proc != null) {
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            out.println("cd /home/ubuntu/myapps/snow-leopard/webapps");
            out.println("pwd");
            out.println("cat Dockerfile");
            out.println("touch Trush");
            out.println("exit");
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                proc.waitFor();
                in.close();
                out.close();
                proc.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

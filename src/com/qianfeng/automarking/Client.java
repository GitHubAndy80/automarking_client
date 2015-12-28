package com.qianfeng.automarking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入要连接的服务端IP地址：");
		String ip = input.next();
		System.out.println("请输入要连接的服务端端口号");
		int port = input.nextInt();
		System.out.println("请输入你要传输的文件路径，如：d://io.txt");
		String path = input.next();
		
		Socket socket = new Socket(ip, port);
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(path));
		BufferedOutputStream writer = new BufferedOutputStream(socket.getOutputStream());
		byte[] bs = new byte[4096];
		int len;
		while((len=reader.read(bs))!=-1){
			writer.write(bs, 0, len);
			writer.flush();
		}
		System.out.println("客户端上传文件完毕");
		reader.close();
		socket.close();
	}
}

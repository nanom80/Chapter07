package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		//서버 소켓 생성
		ServerSocket serverSocket = new ServerSocket();
		
		//bind
		//InetSocketAddress ipport = new InetSocketAddress("192.168.0.99",10001);
		//serverSocket.bind(ipport);
		serverSocket.bind(new InetSocketAddress("192.168.0.99",10001));
		
		//서버 시작
		System.out.println("<서버 시작>");
		System.out.println("=====================================");
		
		while(true) {
			System.out.println("[연결을 기다리고 있습니다]");
			Socket socket = serverSocket.accept();
			System.out.println("클라이언트가 연결 되었습니다");
			
			Thread st = new ServerThread(socket);
			st.start();
		}
		
	}

}



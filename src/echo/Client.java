package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

//C:\javaStudy\workspace\Chapter06\bin>java echo.Client
//클라이언트에서부터 메시지를 보내야 자연스럽게 진행이 되네
//프로그램 작성 순서 때문인거같은데... 어떻게 풀어야하나

public class Client {

	public static void main(String[] args) throws IOException {
		
		//소켓 생성
		Socket socket = new Socket();
		
		//클라이언트 시작
		System.out.println("<클라이언트 시작>");
		System.out.println("=====================================");
		
		//클라이언트가 연결되면 서버의 accept()가 실행된다
		System.out.println("[서버에 연결을 요청합니다]");
		socket.connect(new InetSocketAddress("192.168.0.99",10001));
		
		//쓰기 스트림
		//OutputStream out = new FileOutputStream("C:\\javaStudy\\MS949.txt");
		OutputStream out = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//읽기 스트림
		InputStream in = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//스캐너 준비
		Scanner sc = new Scanner(System.in);
		//InputStream sIn = System.in; //주스트림
		//InputStreamReader sIsr = new InputStreamReader(sIn, "UTF-8");
		//BufferedReader sBr = new BufferedReader(sIsr);
		
		
		while(true) {
			//메시지 키보드로 입력받기
			String msg = sc.nextLine();
			//String msg = sBr.readLine();
			
			//메시지 보내기
			bw.write("신현석:"+msg);
			bw.newLine();
			bw.flush();
			System.out.println("서버로 보낸 메시지: "+msg);
			
			if("/q".equals(msg)) {
				break;
			}
			
			//메세지 받기
			String reMsg = br.readLine();
			System.out.println("서버로부터 받은 메시지: "+reMsg);
			
			if("/q".equals(reMsg)) {
				break;
			}
			
		}
		
		System.out.println("=====================================");
		//System.out.println("<클라이언트 종료>");
		
		//println스트림
		OutputStream pOut = System.out;
		OutputStreamWriter pOsw = new OutputStreamWriter(pOut, "UTF-8");
		BufferedWriter pBw = new BufferedWriter(pOsw);
		
		pBw.write("<클라이언트 종료>");
		pBw.newLine();
		pBw.flush();
		
		//자원정리
		pBw.close();
		sc.close();
		//sBr.close();
		br.close();
		bw.close();
		socket.close();
		
		
		
	}

}

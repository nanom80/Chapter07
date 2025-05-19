package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	
	//필드
	private Socket socket;
	
	//생성자
	public ServerThread() {
		super();
	}
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	//메소드 gs
	
	//메소드 일반
	@Override
	public void run() {
		try {
			//읽기 스트림
			//InputStream in = new FileInputStream("C:\\javaStudy\\MS949.txt");
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			//쓰기 스트림
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			while(true) {
				//메세지 받기
				String msg = br.readLine();
				
				System.out.println("클라이언트로부터 받은 메시지: "+msg);
				
				if("/q".equals(msg)) {
					break;
				}
				
				//메시지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
				System.out.println("클라이언트로 보낸 메시지: "+msg);
				
				if(msg == null) {
					break;
				}
				
				if("/q".equals(msg)) {
					break;
				}
			}
		}catch(IOException e) {
			System.out.println(e.toString());
		}
	}

	

	
	
}

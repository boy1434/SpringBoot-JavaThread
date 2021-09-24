package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// 소켓 서버 = StateFul - 기다릴수도 있고 임의로 요청할 수도 있다
public class MyServerSocket {
	
	
	
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader br;
	
	
	public MyServerSocket() {
		try {
			serverSocket = new ServerSocket(10000);
			System.out.println("클라이언트로 부터 접속 대기중");
			socket = serverSocket.accept(); // 클라이언트가 접속할 때까지 락이 걸림.
			System.out.println("클라이언트 연결 완료");
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String input = null;
			
			while((input = br.readLine()) != null) {
				System.out.println("클라이언트: " + input); 
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new MyServerSocket();
	}
}

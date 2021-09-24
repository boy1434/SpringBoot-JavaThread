package ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// 소켓 서버 = StateFul - 기다릴수도 있고 임의로 요청할 수도 있다
public class MyServerSocket {
	
	
	
	private ServerSocket serverSocket;
	
	
	
	public MyServerSocket() {
		try {
			serverSocket = new ServerSocket(10000); // 한번만 열면된다
			while(true) { // 메인스레드 = 데몬 스레드
				
				System.out.println("클라이언트로 부터 접속 대기중");
				Socket socket = serverSocket.accept(); // 클라이언트가 접속할 때까지 락이 걸림.
				System.out.println("클라이언트 연결 완료");
			
				Thread t = new Thread(new InnerThread(socket));
				t.start();
			}
			
		} catch (IOException e) { // IO 통신에서 메세지를 받아서 저장하겠다
			System.out.println("클라이언트와의 연결이 실패하였습니다.");
		}
	}
	
	class InnerThread implements Runnable{

		private Socket socket;
		
		public InnerThread(Socket socket) {
			this.socket = socket;
		}
		
		private BufferedReader br;
		
		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String input = null;
				
				// 겁나 바빠요
				while((input = br.readLine()) != null) {
					System.out.println("클라이언트: " + input); 
				}
				
			} catch (Exception e) {
				System.out.println("클라이언트와의 연결이 종료되었습니다.");
				try {
					socket.close();
					br.close();
				} catch (IOException e1) {
					System.out.println("메모리 릭이 발생하였습니다.");
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		new MyServerSocket();
	}
}

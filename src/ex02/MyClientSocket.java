package ex02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClientSocket {

	private Socket socket;
	private BufferedWriter bw;
	//private PrintWriter pw; = bw
	
	public MyClientSocket() {
		try {
			socket = new Socket("localhost", 10000 );
			
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		//	pw = new PrintWriter(socket.getOutputStream(), true); // true 하면 auto flush
			
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				String keyboardInput = sc.nextLine(); // 키보드로 입력 안하면 락걸림.
				bw.write(keyboardInput +"\n");
				bw.flush();
			}
			
			
			
		} catch (UnknownHostException e) { // 호스트가 잘못되면 발생 
			e.printStackTrace();
		} catch (IOException e) { // 포트가 잘못되면 IO 발생
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new MyClientSocket();
	}
}

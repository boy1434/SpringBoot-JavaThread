package ex01;

class NewThread implements Runnable {
	public void run() {
		for (int i = 1; i < 11; i++) {
			System.out.println("새로운 스레드:" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // static 함수  , 1/1000초 
		}
	}
} 

public class ThreadEx02 {

	// 일꾼 하나 = 메인 쓰레드 
	// 일 = 최대 퍼포먼스
	// 딜레이 걸기 = Thread.sleep()
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new NewThread());
		t1.start(); // run() 함수가 실행 
		
		for (int i = 1; i < 11; i++) {
			System.out.println("메인 스레드:" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // static 함수  , 1/1000초 
		}
	}
}

package com.shiyuanhui.socket;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SocketActivity extends Activity {
    /** Called when the activity is first created. */
	private Button startButton = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startButton = (Button)findViewById(R.id.startListener);
        startButton.setOnClickListener(new StartSocketListener());
    }
    
    class StartSocketListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			new ServerThread().start();
		}
    	
    }
    
    class ServerThread extends Thread{
    	//ʹ��TCPЭ��ͻ�����Ҫ���еĲ���
    	/*public void run(){
    		//����һ��ServerSocket����
    		ServerSocket serverSocket = null;
    		try {
    			//����һ��ServerSocket���󣬲������Socket��4567�˿ڼ���
				serverSocket = new ServerSocket(4567);
				//����ServerSocket��accept()���������ܿͻ��������͵�����
				Socket socket = serverSocket.accept();
				//��Socket���еõ�InputStream����
				InputStream inputStream = socket.getInputStream();
				byte buffer [] = new byte[1024*4];
				int temp = 0;
				//��InputStream���ж�ȡ�ͻ��������͵�����
				while((temp = inputStream.read(buffer)) != -1){
					System.out.println(new String(buffer,0,temp));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
    	}*/
    	//ʹ��UDPЭ��ͻ�����Ҫ���еĲ���
    	public void run(){
    		try {
    			//����һ��DatagramSocket���󣬲�ָ�������Ķ˿ں�
				DatagramSocket socket = new DatagramSocket(4567);
				byte data [] = new byte[1024];
				//����һ���յ�DatagramPacket����
				DatagramPacket packet = new DatagramPacket(data,data.length);
				//ʹ��receive�������տͻ��������͵�����
				socket.receive(packet);
				String result = new String(packet.getData(),packet.getOffset(),packet.getLength());
				System.out.println("result--->" + result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}

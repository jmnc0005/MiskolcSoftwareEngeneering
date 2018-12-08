 package conexion;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.StandardWatchEventKinds;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Server implements Runnable, Serializable {

	private String host;				//Computer where we send the information	
	private final int clientPort;		//port where we send the information
	private Queue<String> buffer;		//buffer that connect the class server and the class interface
	private Semaphore semaforo;			//Semaphore to manage the access to the buffer

	public Server(String _host, int _clientPort, Queue<String> _buffer, Semaphore _semaforo) {
		this.host=_host;				
		this.clientPort = _clientPort;	
		this.buffer = _buffer;			
		this.semaforo=_semaforo;		
	}

	@Override
	public void run() {
		try (DatagramSocket serverSocket = new DatagramSocket(12356)) {
			String message;
			while (true) {
				semaforo.acquire(); 			//we get the access to the buffer
				if (!buffer.isEmpty()) {		//we check that the buffer content at least one element to be sent
				//	Thread.sleep(100);
					message = buffer.poll();	//we get a element from the buffer
					semaforo.release();			//we release the access to the server
					DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(),
							InetAddress.getByName(host),
							//InetAddress.getLocalHost(), // ip adress
							clientPort);		//we create the package to send
					serverSocket.send(datagramPacket);	//we send the package
					
				} else semaforo.release();		//if we didn't release the access to the buffer inside the conditional IF we release the buffer here
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

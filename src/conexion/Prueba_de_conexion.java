package conexion;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import conexion.Client;
import conexion.Server;


public class Prueba_de_conexion {
	
	public static void main(String[] args) {
		Queue<String> bufferSend=new LinkedList<>();
		//Queue<String> bufferRecive=new LinkedList<>();
		int port = 50001;
		String host="localhost";
		//Server sr = new Server(host, port, bufferSend);
		//Client cl = new Client(port,bufferRecive);
		Client cl = new Client(port);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		//ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.submit(cl);
		//executorService.submit(sr);
		
		
		
		Mensaje message=new Mensaje();
		String envio;
	
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 75, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 72, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 69, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 67, 2);
		envio=message.toString();
		bufferSend.add(envio);
		
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 75, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 72, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 69, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 67, 2);
		envio=message.toString();
		bufferSend.add(envio);
		
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 75, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 72, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 69, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 67, 2);
		envio=message.toString();
		bufferSend.add(envio);
		
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 75, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 72, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 69, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 67, 2);
		envio=message.toString();
		bufferSend.add(envio);
		
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 75, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 72, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 69, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 67, 2);
		envio=message.toString();
		bufferSend.add(envio);
		
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 75, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 76, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 64, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 72, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 69, 2);
		envio=message.toString();
		bufferSend.add(envio);
		message.rewrite(1, 67, 2);
		envio=message.toString();
		bufferSend.add(envio);
		
	}
	
	
	
	
	
	
}

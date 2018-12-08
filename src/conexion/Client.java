package conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import midi.MyMidiPlayer1;



public class Client implements Runnable {
	private final int port;				//the port where we will receive the information
	private MyMidiPlayer1 player;		//the player to play the notes
	private Mensaje message;			//the information that we receive from the server
	private Boolean finish;				//an control variable to keep the WHILE loop working 
	
	public Client(int _port/*, Queue<String> _buffer*/){
		this.port = _port;
		this.player = new MyMidiPlayer1();
		this.message=new Mensaje();
		this.finish=false;
	}

	@Override
	public void run() {
		try(DatagramSocket clientSocket = new DatagramSocket(port)){		
			byte[] buffer = new byte[20000];		//maximum byte is 65507
			while(!finish) {
				DatagramPacket datagramPacket = new DatagramPacket(buffer,0,buffer.length);		//we create the datagramPacket
				clientSocket.receive(datagramPacket);											//we receive the information in the datagramPacket
				message.show();
				String receivedMessage = new String(datagramPacket.getData());					//we convert the datagramPacket to String
				message.fromString(receivedMessage);											//we get the new values from the string
				//System.out.println(message.toString());									
				player.setUpPlayer(message.getInstrument(), message.getNote());					//we play the new note
					Thread.sleep(600);
				
				
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Timeout. Client is closing.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	    }
	}
	
	

package main;
import java.awt.EventQueue;

import interfaz.index;

public class main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					index window = new index();
					window.frameVisibility(true);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}

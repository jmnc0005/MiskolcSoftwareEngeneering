package interfaz;

import midi.MyMidiPlayer1;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import conexion.Client;
import conexion.Mensaje;
import conexion.Server;

public class index {

	private JFrame frame;
	int instrument = 0;
	int note = 0;
	private JButton btnSi;
	private JTextField txtDodo;
	private JTextField textFieldPuertoServer;
	private JTextField textFieldDestinationIP;
	private JTextField textFieldPuertoClient;
	private JTextField textFieldIPLocal;
	private String clientPort, serverPort, ip;
	Queue<String> bufferSend;
	private Boolean brodcasting, listening;
	private Mensaje message;
	private String envio;
	private ArrayList<JPanel> paneles = new ArrayList<JPanel>();
	private Semaphore semaforo;

	public void frameVisibility(boolean _boleanValue) {
		frame.setVisible(_boleanValue);
	}

	public index() {
		listening = false;
		brodcasting = false;
		clientPort = "50001";
		serverPort = "50001";
		ip = "localhost";
		message = new Mensaje();
		semaforo=new Semaphore(1);
		initialize();

	}

	void playNote(int _instrument, int _note) {
		MyMidiPlayer1 player = new MyMidiPlayer1();
		player.setUpPlayer(_instrument, _note);
		instrument = _instrument;
		note = _note;
	}

	void visibility(JPanel panel) {
		int i;
		for (i = 0; i < paneles.size(); i++) {
			paneles.get(i).setVisible(false);
		}
		panel.setVisible(true);
	}

	void saveConnectionSettings() {
		clientPort = textFieldPuertoClient.getText();
		serverPort = textFieldPuertoServer.getText();
		ip = textFieldDestinationIP.getText();
	}

	private String getLocalIP() {
		try {
			String iplocal = InetAddress.getLocalHost() + "";
			String ipPart[] = iplocal.split("/");
			return ipPart[1];
		} catch (UnknownHostException e1) {
			return "0";
		}
	}

	private void initialize() {
		bufferSend = new LinkedList<>();

		frame = new JFrame();
		frame.setBounds(100, 100, 735, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panelInicio = new JPanel();
		JPanel panelGuitar = new JPanel();
		JPanel panelStrings = new JPanel();
		JPanel panelFx = new JPanel();
		JPanel panelPiano = new JPanel();
		JPanel panelNewProject = new JPanel();
		JPanel panelKeyboard = new JPanel();
		JPanel panelConexion = new JPanel();
		
		paneles.add(panelInicio);
		paneles.add(panelGuitar);
		paneles.add(panelStrings);
		paneles.add(panelFx);
		paneles.add(panelPiano);
		paneles.add(panelNewProject);
		paneles.add(panelKeyboard);
		paneles.add(panelConexion);

		panelGuitar.setVisible(false);
		panelStrings.setVisible(false);
		panelFx.setVisible(false);
		panelPiano.setVisible(false);
		panelNewProject.setVisible(false);
		panelKeyboard.setVisible(false);
		panelConexion.setVisible(false);

		// PANEL DE INICIO
		panelInicio.setBounds(130, 0, 587, 410);
		frame.getContentPane().add(panelInicio);
		panelInicio.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		lblWelcome.setBounds(138, 76, 283, 187);
		panelInicio.add(lblWelcome);

		// PANEL NEW PROJECT
		panelNewProject.setBounds(130, 0, 587, 410);
		frame.getContentPane().add(panelNewProject);
		panelNewProject.setLayout(null);

		// PANEL CONEXION
		panelConexion.setBounds(130, 0, 587, 397);
		frame.getContentPane().add(panelConexion);
		panelConexion.setLayout(null);

		JLabel lblServer = new JLabel("SERVER");
		lblServer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setBounds(189, 80, 72, 33);
		panelConexion.add(lblServer);

		JLabel lblClient = new JLabel("CLIENT");
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblClient.setBounds(396, 80, 72, 33);
		panelConexion.add(lblClient);

		textFieldPuertoServer = new JTextField();
		textFieldPuertoServer.setBounds(175, 139, 86, 20);
		panelConexion.add(textFieldPuertoServer);
		textFieldPuertoServer.setColumns(10);
		textFieldPuertoServer.setText("50001");

		textFieldDestinationIP = new JTextField();
		textFieldDestinationIP.setText(getLocalIP());
		textFieldDestinationIP.setBounds(175, 194, 86, 20);
		panelConexion.add(textFieldDestinationIP);
		textFieldDestinationIP.setColumns(10);

		JLabel lblPortServer = new JLabel("Port:");
		lblPortServer.setBounds(175, 124, 86, 14);
		panelConexion.add(lblPortServer);

		JLabel lblIpServer = new JLabel("Client's IP:");
		lblIpServer.setBounds(175, 181, 86, 14);
		panelConexion.add(lblIpServer);

		textFieldPuertoClient = new JTextField();
		textFieldPuertoClient.setBounds(396, 139, 86, 20);
		panelConexion.add(textFieldPuertoClient);
		textFieldPuertoClient.setColumns(10);
		textFieldPuertoClient.setText("50001");

		textFieldIPLocal = new JTextField();
		textFieldIPLocal.setBounds(396, 194, 86, 20);
		panelConexion.add(textFieldIPLocal);
		textFieldIPLocal.setColumns(10);
		textFieldIPLocal.setEditable(false);
		textFieldIPLocal.setText(getLocalIP());

		JLabel lblPortClient = new JLabel("Port:");
		lblPortClient.setBounds(396, 124, 46, 14);
		panelConexion.add(lblPortClient);

		JLabel lblIpClient = new JLabel("my IP:");
		lblIpClient.setBounds(396, 181, 46, 14);
		panelConexion.add(lblIpClient);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveConnectionSettings();
			}
		});
		btnSave.setBounds(290, 262, 89, 23);
		panelConexion.add(btnSave);

		// BOTONES DEL TECLADO

		// DO SOSTENIDO --> W
		JButton btnDoSostenido = new JButton("");
		btnDoSostenido.setBackground(Color.BLACK);
		btnDoSostenido.setBounds(144, 81, 24, 144);
		panelKeyboard.add(btnDoSostenido);
		btnDoSostenido.setRolloverEnabled(false);
		btnDoSostenido.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent ep) {
				if (ep.getKeyCode() == 87) {
					playNote(instrument, 61);
				}

			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// RE SOSTENIDO --> E
		JButton btnReSostenido = new JButton("");
		btnReSostenido.setBackground(Color.BLACK);
		btnReSostenido.setBounds(196, 81, 24, 144);
		panelKeyboard.add(btnReSostenido);
		btnReSostenido.setRolloverEnabled(false);

		btnReSostenido.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 69) {
					playNote(instrument, 63);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// FA SOSTENIDO --> T
		JButton btnFaSostenido = new JButton("");
		btnFaSostenido.setBackground(Color.BLACK);
		btnFaSostenido.setBounds(277, 81, 24, 144);
		panelKeyboard.add(btnFaSostenido);
		btnFaSostenido.setRolloverEnabled(false);

		btnFaSostenido.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 84) {
					playNote(instrument, 66);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// SOL SOSTENIDO --> Y
		JButton btnSolSostenido = new JButton("");
		btnSolSostenido.setBackground(Color.BLACK);
		btnSolSostenido.setBounds(330, 81, 24, 144);
		panelKeyboard.add(btnSolSostenido);
		btnSolSostenido.setRolloverEnabled(false);

		btnSolSostenido.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 89) {
					playNote(instrument, 68);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// LA SOSTENIDO --> U
		JButton btnLaSostenido = new JButton("");
		btnLaSostenido.setBackground(Color.BLACK);
		btnLaSostenido.setBounds(383, 81, 24, 144);
		panelKeyboard.add(btnLaSostenido);
		btnLaSostenido.setRolloverEnabled(false);

		btnLaSostenido.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 85) {
					playNote(instrument, 70);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// DO --> A
		JButton btnDo = new JButton("");
		btnDo.setBounds(115, 81, 32, 144);
		btnDo.setRolloverEnabled(false);
		panelKeyboard.add(btnDo);

		btnDo.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 65) {
					playNote(instrument, 60);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// RE --> S
		JButton btnRe = new JButton("");
		btnRe.setBounds(167, 81, 32, 144);
		panelKeyboard.add(btnRe);
		btnRe.setRolloverEnabled(false);

		btnRe.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 83) {
					playNote(instrument, 62);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// MI --> D
		JButton btnMi = new JButton("");
		btnMi.setBounds(219, 81, 32, 144);
		panelKeyboard.add(btnMi);
		btnMi.setRolloverEnabled(false);

		btnMi.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 68) {
					playNote(instrument, 64);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// FA --> F
		JButton btnFa = new JButton("");
		btnFa.setBounds(249, 81, 32, 144);
		panelKeyboard.add(btnFa);
		btnFa.setRolloverEnabled(false);

		btnFa.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 70) {
					playNote(instrument, 65);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// SOL --> G
		JButton btnSol = new JButton("");
		btnSol.setBounds(300, 81, 32, 144);
		panelKeyboard.add(btnSol);
		btnSol.setRolloverEnabled(false);

		btnSol.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 71) {
					playNote(instrument, 67);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// LA --> H
		JButton btnLa = new JButton("");
		btnLa.setBounds(353, 81, 32, 144);
		panelKeyboard.add(btnLa);
		btnLa.setRolloverEnabled(false);

		btnLa.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 72) {
					playNote(instrument, 69);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// SI --> J
		btnSi = new JButton("");
		btnSi.setBounds(406, 81, 32, 144);
		panelKeyboard.add(btnSi);
		btnSi.setRolloverEnabled(false);

		btnSi.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 74) {
					playNote(instrument, 71);
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});

		// TEXTO TECLAS
		txtDodo = new JTextField();
		txtDodo.setText("  Do   #Do    Re   #Re    Mi     Fa    #Fa   Sol   #Sol   La   #La     Si");
		txtDodo.setBounds(112, 250, 338, 20);
		panelKeyboard.add(txtDodo);
		txtDodo.setColumns(10);

		// ACTION LISTENERS DE LAS TECLAS
		btnDoSostenido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 61);
			}
		});

		btnReSostenido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 63);
			}
		});

		btnFaSostenido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 66);
			}
		});

		btnSolSostenido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 68);
			}
		});

		btnLaSostenido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 70);
			}
		});

		btnDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 60);
			}
		});

		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 62);
			}
		});

		btnMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 64);
			}
		});

		btnFa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 65);
			}
		});

		btnSol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 67);
			}
		});

		btnLa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 69);
			}
		});

		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNote(instrument, 71);
			}
		});

		// PANEL PIANO
		panelPiano.setBounds(130, 0, 587, 410);
		frame.getContentPane().add(panelPiano);
		panelPiano.setLayout(null);

		// BOTON PIANO
		JButton btnPiano = new JButton("Piano");
		btnPiano.setIcon(new ImageIcon(
				"D:\\UNIVERSIDAD\\MISKOLC\\SOFTWARE ENGINEERING\\Software\\src\\images\\icons8-classic-music-96.png"));
		btnPiano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visibility(panelPiano);
			}
		});
		btnPiano.setBounds(77, 74, 182, 107);
		panelNewProject.add(btnPiano);

		// PANEL GUITAR
		panelGuitar.setBounds(130, 0, 587, 410);
		frame.getContentPane().add(panelGuitar);
		panelGuitar.setLayout(null);

		// BOTON GUITAR
		JButton btnGuitar = new JButton("Guitar");
		btnGuitar.setIcon(new ImageIcon(
				"D:\\UNIVERSIDAD\\MISKOLC\\SOFTWARE ENGINEERING\\Software\\src\\images\\icons8-guitar-96.png"));
		btnGuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(panelGuitar);
			}
		});
		btnGuitar.setBounds(333, 74, 208, 107);
		panelNewProject.add(btnGuitar);

		// PANEL STRINGS
		panelStrings.setBounds(130, 0, 587, 410);
		frame.getContentPane().add(panelStrings);
		panelStrings.setLayout(null);

		// BOTON STRINGS
		JButton btnStrings = new JButton("Strings");
		btnStrings.setIcon(new ImageIcon(
				"D:\\UNIVERSIDAD\\MISKOLC\\SOFTWARE ENGINEERING\\Software\\src\\images\\icons8-cello-96.png"));
		btnStrings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(panelStrings);
			}
		});
		btnStrings.setBounds(77, 194, 182, 90);
		panelNewProject.add(btnStrings);

		// PANEL FX
		panelFx.setBounds(130, 0, 587, 410);
		frame.getContentPane().add(panelFx);
		panelFx.setLayout(null);

		// BOTON FX
		JButton btnFx = new JButton("FX");
		btnFx.setIcon(new ImageIcon(
				"D:\\UNIVERSIDAD\\MISKOLC\\SOFTWARE ENGINEERING\\Software\\src\\images\\icons8-guitar-amp-96.png"));
		btnFx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(panelFx);
			}
		});
		btnFx.setBounds(333, 194, 208, 90);
		panelNewProject.add(btnFx);

		// PANEL KEYBOARD
		panelKeyboard.setBounds(130, 0, 587, 397);
		frame.getContentPane().add(panelKeyboard);
		panelKeyboard.setLayout(null);

		// BOTON PRESS TO PLAY
		JButton btnPressToPlay = new JButton("Press To Play");
		btnPressToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPressToPlay.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 87) {
					playNote(instrument, 61);
				}
				if (e.getKeyCode() == 69) {
					playNote(instrument, 63);
				}
				if (e.getKeyCode() == 84) {
					playNote(instrument, 66);
				}
				if (e.getKeyCode() == 89) {
					playNote(instrument, 68);
				}
				if (e.getKeyCode() == 85) {
					playNote(instrument, 70);
				}
				if (e.getKeyCode() == 65) {
					playNote(instrument, 60);
				}
				if (e.getKeyCode() == 83) {
					playNote(instrument, 62);
				}
				if (e.getKeyCode() == 68) {
					playNote(instrument, 64);
				}
				if (e.getKeyCode() == 70) {
					playNote(instrument, 65);
				}
				if (e.getKeyCode() == 71) {
					playNote(instrument, 67);
				}
				if (e.getKeyCode() == 72) {
					playNote(instrument, 69);
				}
				if (e.getKeyCode() == 74) {
					playNote(instrument, 71);
				}
				message.rewrite(instrument, note, 2);
				envio = message.toString();
				try {
					semaforo.acquire();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				bufferSend.add(envio);
				semaforo.release();
				
				
			}

			public void keyPressed(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});
		btnPressToPlay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnPressToPlay.setBounds(115, 295, 323, 52);
		panelKeyboard.add(btnPressToPlay);

		// BOTON ACOUSTIC GUITAR
		JButton btnAcousticGuitar = new JButton("Acoustic Guitar");
		btnAcousticGuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 25;
				visibility(panelKeyboard);
			}
		});
		btnAcousticGuitar.setBounds(115, 121, 153, 25);
		panelGuitar.add(btnAcousticGuitar);

		// BOTON DISTORSION GUITAR
		JButton btnDistorsionGuitar = new JButton("Distorsion Guitar");
		btnDistorsionGuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 31;
				visibility(panelKeyboard);
			}
		});
		btnDistorsionGuitar.setBounds(359, 121, 147, 25);
		panelGuitar.add(btnDistorsionGuitar);

		// BOTON ELECTRIC GUITAR
		JButton btnElectricGuitar = new JButton("Electric Guitar");
		btnElectricGuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 28;
				visibility(panelKeyboard);
			}
		});
		btnElectricGuitar.setBounds(115, 241, 153, 25);
		panelGuitar.add(btnElectricGuitar);

		// BOTON ARMONIC GUITAR
		JButton btnArmonicGuitar = new JButton("Armonic Guitar");
		btnArmonicGuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 32;
				visibility(panelKeyboard);
			}
		});
		btnArmonicGuitar.setBounds(359, 241, 147, 25);
		panelGuitar.add(btnArmonicGuitar);

		// BOTON ACOUSTIC GRAND PIANO
		JButton btnAcousticGrandPiano = new JButton("Acoustic Grand Piano");
		btnAcousticGrandPiano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 1;
				visibility(panelKeyboard);
				btnAcousticGrandPiano.addKeyListener(new KeyListener() {

					public void keyTyped(KeyEvent arg0) {

					}

					public void keyReleased(KeyEvent arg0) {

					}

					public void keyPressed(KeyEvent ep) {
						if (ep.getKeyCode() == 87) {
							playNote(instrument, 61);
						}
						if (ep.getKeyCode() == 69) {
							playNote(instrument, 63);
						}
					}

				});

			}
		});
		btnAcousticGrandPiano.setBounds(100, 121, 178, 25);
		panelPiano.add(btnAcousticGrandPiano);

		// BOTON ELECTRIC GRAND PIANO
		JButton btnElectricGrandPiano = new JButton("Electric Grand Piano");
		btnElectricGrandPiano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 3;
				visibility(panelKeyboard);
			}
		});
		btnElectricGrandPiano.setBounds(333, 121, 173, 25);
		panelPiano.add(btnElectricGrandPiano);

		// BOTON BRIGHT ACOUSTIC PIANO
		JButton btnBrightAcousticPiano = new JButton("Bright Acoustic Piano");
		btnBrightAcousticPiano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 2;
				visibility(panelKeyboard);
			}
		});
		btnBrightAcousticPiano.setBounds(100, 241, 178, 25);
		panelPiano.add(btnBrightAcousticPiano);

		// BOTON CLAVINET
		JButton btnClavinet = new JButton("Clavinet");
		btnClavinet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 8;
				visibility(panelKeyboard);
			}
		});
		btnClavinet.setBounds(333, 241, 173, 25);
		panelPiano.add(btnClavinet);

		// BOTON VIOLIN
		JButton btnViolin = new JButton("Violin");
		btnViolin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 41;
				visibility(panelKeyboard);
			}
		});
		btnViolin.setBounds(115, 121, 153, 25);
		panelStrings.add(btnViolin);

		// BOTON CELLO
		JButton btnCello = new JButton("Cello");
		btnCello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 43;
				visibility(panelKeyboard);
			}
		});
		btnCello.setBounds(359, 121, 147, 25);
		panelStrings.add(btnCello);

		// BOTON VIOLLA
		JButton btnViolla = new JButton("Violla");
		btnViolla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 42;
				visibility(panelKeyboard);
			}
		});
		btnViolla.setBounds(115, 241, 153, 25);
		panelStrings.add(btnViolla);

		// BOTON CONTRABASS
		JButton btnContrabass = new JButton("Contrabass");
		btnContrabass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 44;
				visibility(panelKeyboard);
			}
		});
		btnContrabass.setBounds(359, 241, 147, 25);
		panelStrings.add(btnContrabass);

		// BOTON NEW PROJECT
		JButton btnNewProject = new JButton("New Project");
		btnNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(panelNewProject);
			}
		});

		// BOTON RAIN
		JButton btnRain = new JButton("Rain");
		btnRain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 97;
				visibility(panelKeyboard);
			}
		});
		btnRain.setBounds(115, 121, 153, 25);
		panelFx.add(btnRain);

		// BOTON BRIGHTNESS
		JButton btnBrightness = new JButton("Brightness");
		btnBrightness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 101;
				visibility(panelKeyboard);
			}
		});
		btnBrightness.setBounds(359, 121, 147, 25);
		panelFx.add(btnBrightness);

		// BOTON CRYSTAL
		JButton btnCrystal = new JButton("Crystal");
		btnCrystal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 99;
				visibility(panelKeyboard);
			}
		});
		btnCrystal.setBounds(115, 241, 153, 25);
		panelFx.add(btnCrystal);

		// BOTON GOBLINS
		JButton btnGoblins = new JButton("Goblins");
		btnGoblins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrument = 102;
				visibility(panelKeyboard);
			}
		});
		btnGoblins.setBounds(359, 241, 147, 25);
		panelFx.add(btnGoblins);

		// BOTONES INTERFAZ

		// BOTON NEW PROJECT
		btnNewProject.setBounds(0, 0, 130, 25);
		frame.getContentPane().add(btnNewProject);

		// BOTON LISTEN IT
		JButton btnListen = new JButton("Listen it");
		btnListen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listening = !listening;
				ExecutorService executorClient = Executors.newSingleThreadExecutor();
				if (listening) {
					btnListen.setText("Listening");
					int port = Integer.parseInt(clientPort);
					Client cl = new Client(port);
					executorClient.submit(cl);
					System.out.println("Listo para escuchar");
				} else {
					btnListen.setText("Listen it");
					executorClient.shutdown();
					System.out.println("Escucha finalizada");
				}

			}
		});
		btnListen.setBounds(0, 24, 130, 25);
		frame.getContentPane().add(btnListen);

		// BOTON LEARN IT
		JButton btnLearnIt = new JButton("Learn it");
		btnLearnIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLearnIt.setBounds(0, 93, 130, 23);
		frame.getContentPane().add(btnLearnIt);

		// BOTON SETTING CONNECTION
		JButton btnConnection = new JButton("Setting Connection");
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(panelConexion);
			}
		});
		btnConnection.setBounds(0, 73, 130, 23);
		frame.getContentPane().add(btnConnection);

		// BOTON BROADCAST
		JButton btnBrodcast = new JButton("Brodcast");
		btnBrodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				brodcasting = !brodcasting;
				ExecutorService executorBrodcast = Executors.newSingleThreadExecutor();
				if (brodcasting) {
					btnBrodcast.setText("Brodcasting...");
					int port = Integer.parseInt(serverPort);
					Server sr = new Server(ip, port, bufferSend, semaforo);
					executorBrodcast.submit(sr);
					System.out.println("Listo para emitir");
				} else {
					btnBrodcast.setText("Brodcast");
					executorBrodcast.shutdown();
					System.out.println("Emision finalizada");
				}
			}
		});
		btnBrodcast.setBounds(0, 50, 130, 23);
		frame.getContentPane().add(btnBrodcast);
	}
}


import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Ventana extends JFrame{

	private JPanel panel;
	private Process proceso1, proceso2, proceso3;
	private JTextArea area1, area2;
	private JButton ButtonBlocNotas, ButtonPaint, ButtonGestion, ButtonTerminar, ButtonEjecutar;
	
	public Ventana() {
		area1 = new JTextArea();
		area1.setBounds(100, 150, 300, 300);
		area1.setEditable(false);
		
		area2 = new JTextArea();
		area2.setBounds(500, 390, 300, 250);
		area2.setEditable(false);
		
		setSize(1000, 700);
		setTitle("Practica PSP Tema 1");
		setLocationRelativeTo(null);
		initComponents();
		panel.add(area1);
		panel.add(area2);
		procesosActivos();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void procesosActivos() {
		area2.setText("");
		if(proceso1 != null && proceso1.isAlive()) {
			area2.setText("proceso 1 activo");
			
		}
		if(proceso2 != null && proceso2.isAlive()) {
			String txt = area2.getText();
			area2.setText(txt + "\nproceso 2 activo");
		}
		if(proceso3 != null && proceso3.isAlive()) {
			String txt = area2.getText();
			area2.setText(txt + "\nproceso 3 activo");
		}
		panel.updateUI();
	}
	
	private void initComponents() {
		panel = new JPanel();
		panel.setLayout(null);
		this.getContentPane().add(panel);

		JLabel etiqueta = new JLabel("Procesos activos");
		etiqueta.setBounds(500, 350, 200, 50);
		panel.add(etiqueta);
		ButtonBlocNotas  = new JButton();
		ButtonBlocNotas.setBounds(700, 50, 200, 40);
		ButtonBlocNotas.setText("Bloc de notas");
		ButtonBlocNotas.setEnabled(true);
		panel.add(ButtonBlocNotas);
		
		ButtonPaint = new JButton();
		ButtonPaint.setBounds(700, 130, 200, 40);
		ButtonPaint.setText("Paint");
		ButtonPaint.setEnabled(true);
		panel.add(ButtonPaint);
		
		ButtonGestion = new JButton();
		ButtonGestion.setBounds(700, 210, 200, 40);
		ButtonGestion.setText("Gestion");
		ButtonGestion.setEnabled(true);
		panel.add(ButtonGestion);
		
		ButtonTerminar = new JButton();
		ButtonTerminar.setBounds(820, 400, 130, 40);
		ButtonTerminar.setText("Terminar");
		ButtonTerminar.setEnabled(true);
		panel.add(ButtonTerminar);
		
		JTextField input = new JTextField("",20);
		panel.add(input);
		input.setBounds(80, 80, 160, 40);
		
		ButtonEjecutar = new JButton();
		ButtonEjecutar.setBounds(250, 80, 160, 40);
		ButtonEjecutar.setText("Ejecutar");
		ButtonEjecutar.setEnabled(true);
		panel.add(ButtonEjecutar);
		
		ActionListener ButtonBlocNotasListener = new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					ProcessBuilder pb = new ProcessBuilder("notepad");
					proceso1 = pb.start();
					procesosActivos();
				} catch (IOException e1) {
					e1.printStackTrace();
					System.exit(-1);
				}
			}
		};
		ButtonBlocNotas.addActionListener(ButtonBlocNotasListener);
		
		ActionListener ButtonPaintListener = new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					ProcessBuilder pb = new ProcessBuilder("mspaint");
					proceso2 = pb.start();
					procesosActivos();
				} catch (IOException e1) {
					e1.printStackTrace();
					System.exit(-1);
				}
				
			}
		};
		ButtonPaint.addActionListener(ButtonPaintListener);
		
		ActionListener ButtonGestionListener = new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					proceso3 = Runtime.getRuntime().exec( "cmd.exe /C start ./lib/Reloj.jar" );
					procesosActivos();
				} catch (IOException e1) {
					e1.printStackTrace();
					System.exit(-1);
				}
				
			}
		};
		ButtonGestion.addActionListener(ButtonGestionListener);
		
		ActionListener ButtonEjecutarListener = new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					Process p = Runtime.getRuntime().exec( "cmd /C " + input.getText());
					BufferedReader reader=new BufferedReader(
		                    new InputStreamReader(p.getInputStream())
		                ); 
		                String line; 
		                String total = "";
		                while((line = reader.readLine()) != null) { total+=line+"\n"; } 
		                area1.setText(total);
		        		panel.updateUI();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		ButtonEjecutar.addActionListener(ButtonEjecutarListener);
		
		ActionListener ButtonTerminarListener= new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(proceso1 != null && proceso1.isAlive()) proceso1.destroy();
				if(proceso2 != null && proceso2.isAlive()) proceso2.destroy();
				if(proceso3 != null && proceso3.isAlive()) proceso3.destroy();
			}
		};
		ButtonTerminar.addActionListener(ButtonTerminarListener);
	}
}

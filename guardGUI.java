import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

public class guardGUI {
	virusAnalyze va = new virusAnalyze();
	File file;
	private JFrame frame;
	
	private final JFileChooser openFileChooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guardGUI window = new guardGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public guardGUI() {
		
		initialize();
		openFileChooser.setCurrentDirectory(new File("c:\\temp"));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnInsert = new JButton("Open File...");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = openFileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION){
					file = openFileChooser.getSelectedFile();
					
					try {
						va.check(file);
						JOptionPane.showMessageDialog(frame, "File successfully added");
						
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame, "Error in adding file");
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame, "Error in adding file");	
						e.printStackTrace();
					}

				}
				
			}
		});
		btnInsert.setBounds(39, 36, 130, 25);
		panel.add(btnInsert);
		
		JLabel lblSelectTargetFile = new JLabel("Select target file from here");
		lblSelectTargetFile.setBounds(39, 73, 162, 16);
		panel.add(lblSelectTargetFile);
		
		JButton btnCheckForVirus = new JButton("Check for virus");
		btnCheckForVirus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						String s = va.check(file);
						//System.out.println(s);
						virusFile vf = new virusFile();
						int num = vf.readFile("virus.txt",s);
						if(num == 0){
							JOptionPane.showMessageDialog(frame, "no viruses found");
						}else{
							JOptionPane.showMessageDialog(frame, "virus found. check the details");
						}
						frame.setVisible(false);
					} catch (NoSuchAlgorithmException e1) {
						System.out.println("first open a file");
						e1.printStackTrace();
					}

					
				} catch (IOException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
			}
		});
		btnCheckForVirus.setBounds(265, 36, 137, 25);
		panel.add(btnCheckForVirus);
	}
}

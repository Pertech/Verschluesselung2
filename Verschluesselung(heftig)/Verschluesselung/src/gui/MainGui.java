package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entschlüsseln.GetEntschluesselterText;
import verschluesseln.GetVerschluesselterText;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(0, 0, 1264, 681);
		contentPane.add(tabbedPane);
		
		JPanel pnVerschluesseln = new JPanel();
		tabbedPane.addTab("Verschlüsseln", null, pnVerschluesseln, null);
		pnVerschluesseln.setLayout(null);
		
		JLabel lblUnverschlsselterText = new JLabel("Unverschlüsselter Text");
		lblUnverschlsselterText.setBounds(10, 11, 1239, 14);
		pnVerschluesseln.add(lblUnverschlsselterText);
		
		JTextArea taUnverschluesselterText = new JTextArea();
		//panel.add(textPane);
		taUnverschluesselterText.setLineWrap(true);
		taUnverschluesselterText.setBounds(10, 36, 1239, 221);
		JScrollPane scroll = new JScrollPane (taUnverschluesselterText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10, 36, 1239, 221);
		pnVerschluesseln.add(scroll);
		
		JLabel lblVerschlsselterText = new JLabel("Verschlüsselter Text");
		lblVerschlsselterText.setBounds(381, 268, 868, 14);
		pnVerschluesseln.add(lblVerschlsselterText);
		
		JTextArea taVerschluesselterText = new JTextArea();
		//panel.add(textPane_1);
		taVerschluesselterText.setLineWrap(true);
		taVerschluesselterText.setEditable(false);
		taVerschluesselterText.setBounds(381, 293, 868, 316);
		JScrollPane scroll2 = new JScrollPane (taVerschluesselterText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(381, 293, 868, 316);
		pnVerschluesseln.add(scroll2);

		
		JLabel lblKeyVer = new JLabel("Key");
		lblKeyVer.setBounds(10, 268, 46, 14);
		pnVerschluesseln.add(lblKeyVer);
		
		JTextArea taKeyVer = new JTextArea();
		taKeyVer.setLineWrap(true);
		taKeyVer.setBounds(10, 293, 361, 291);
		JScrollPane scroll7 = new JScrollPane (taKeyVer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll7.setBounds(10, 293, 361, 258);
		pnVerschluesseln.add(scroll7);
		
		JLabel lblSec = new JLabel("Geheimzahl");
		lblSec.setBounds(10, 562, 361, 14);
		pnVerschluesseln.add(lblSec);
		
		JTextField tfSec = new JTextField();
		tfSec.setBounds(10, 589, 361, 20);
		pnVerschluesseln.add(tfSec);
		
		GetVerschluesselterText ver = new GetVerschluesselterText();
		
		JButton btnVerschlsseln = new JButton("Verschlüsseln");
		btnVerschlsseln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] key = null;
				String geheim = null;
				if(taKeyVer.getLineCount() == 10){
					int i = 0;
					key = new String[10];
					for (String line : taKeyVer.getText().split("\\n")){
						key[i] = line;
						i++;
					}
				}
				if(!tfSec.getText().equals("")){
					geheim = tfSec.getText();
				}
				
				taVerschluesselterText.setText(ver.verschluesseln(taUnverschluesselterText.getText(), geheim, key));
			}
		});
		btnVerschlsseln.setBounds(1107, 619, 142, 23);
		pnVerschluesseln.add(btnVerschlsseln);
		
		JPanel pnEntschluesseln = new JPanel();
		tabbedPane.addTab("Entschlüsseln", null, pnEntschluesseln, null);
		pnEntschluesseln.setLayout(null);
		
		JLabel lblVerschlsselterText_1 = new JLabel("Verschl\u00FCsselter Text");
		lblVerschlsselterText_1.setBounds(10, 11, 1239, 14);
		pnEntschluesseln.add(lblVerschlsselterText_1);
		
		JTextArea taVerText = new JTextArea();
		taVerText.setLineWrap(true);
		taVerText.setBounds(10, 36, 1239, 221);
		JScrollPane scroll3 = new JScrollPane (taVerText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll3.setBounds(10, 36, 1239, 221);
		pnEntschluesseln.add(scroll3);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setBounds(10, 268, 46, 14);
		pnEntschluesseln.add(lblKey);
		
		JTextArea taKey = new JTextArea();
		taKey.setLineWrap(true);
		taKey.setBounds(10, 293, 361, 291);
		JScrollPane scroll4 = new JScrollPane (taKey, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll4.setBounds(10, 293, 361, 258);
		pnEntschluesseln.add(scroll4);
		
		JTextArea taEntText = new JTextArea();
		taEntText.setEditable(false);
		taEntText.setLineWrap(true);
		taEntText.setBounds(381, 293, 868, 316);
		JScrollPane scroll6 = new JScrollPane (taEntText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll6.setBounds(381, 293, 868, 316);
		pnEntschluesseln.add(scroll6);
		
		JLabel lblGeheimnummer = new JLabel("Geheimzahl");
		lblGeheimnummer.setBounds(10, 562, 361, 14);
		pnEntschluesseln.add(lblGeheimnummer);
		
		JTextField tfGeheimnummer = new JTextField();
		tfGeheimnummer.setBounds(10, 589, 361, 20);
		pnEntschluesseln.add(tfGeheimnummer);
		tfGeheimnummer.setColumns(10);
		
		GetEntschluesselterText ent = new GetEntschluesselterText();
		
		JButton btnEntschlsseln = new JButton("Entschlüsseln");
		btnEntschlsseln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!taVerText.getText().equalsIgnoreCase("") && !tfGeheimnummer.getText().equalsIgnoreCase("") && taKey.getLineCount() == 10){
					String[] strArr = new String[10];
					int i = 0;
					for (String line : taKey.getText().split("\\n")){
						strArr[i] = line;
						i++;
					}
					taEntText.setText(ent.entschluesseln(taVerText.getText(), tfGeheimnummer.getText(), strArr));
				}
				else{
					JOptionPane.showMessageDialog(null,
						    "Bitte fülle alles korrekt aus!",
						    "ERROR!",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEntschlsseln.setBounds(1107, 621, 142, 23);
		pnEntschluesseln.add(btnEntschlsseln);
		
		JLabel lblEntschlsselterText = new JLabel("Entschl\u00FCsselter Text");
		lblEntschlsselterText.setBounds(381, 268, 868, 14);
		pnEntschluesseln.add(lblEntschlsselterText);
	}
}

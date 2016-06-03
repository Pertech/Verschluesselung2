package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import entschlüsseln.GetEntschluesselterText;
import verschluesseln.GetVerschluesselterText;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;

public class HauptGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HauptGui frame = new HauptGui();
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
	public HauptGui() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(0, 0, 1274, 685);
		contentPane.add(tabbedPane);
		
		JPanel pnVerschluesseln = new JPanel();
		tabbedPane.addTab("Verschlüsseln", null, pnVerschluesseln, null);
		pnVerschluesseln.setLayout(null);
		
		JLabel lblUnverschlsselterText = new JLabel("Unverschlüsselter Text");
		lblUnverschlsselterText.setBounds(10, 11, 1053, 14);
		pnVerschluesseln.add(lblUnverschlsselterText);
		
		JTextArea taUnverschluesselterText = new JTextArea();
		//panel.add(textPane);
		taUnverschluesselterText.setLineWrap(true);
		taUnverschluesselterText.setBounds(10, 36, 1239, 221);
		JScrollPane scroll = new JScrollPane (taUnverschluesselterText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10, 36, 1239, 221);
		pnVerschluesseln.add(scroll);
		
		JLabel lblVerschlsselterText = new JLabel("Verschlüsselter Text");
		lblVerschlsselterText.setBounds(381, 268, 682, 14);
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
				String Text = "";
				String Key = "";
				String[] verText = ver.verschluesseln(taUnverschluesselterText.getText(), geheim, key).split("\n");
				for (int i = 0; i < verText.length; i++) {
					if(!verText[i].equalsIgnoreCase("Start Key")){
						Text += verText[i] + "\n";
					}else{
						Text = Text.substring(0, Text.length() - 1);
						for (int j = 1; j < 11; j++) {
							Key += verText[i+j];
							if(j < 10){
								Key += "\n";
							}
						}
						tfSec.setText(verText[i+11]);
						break;
					}
				}
				taKeyVer.setText(Key);
				taVerschluesselterText.setText(Text);
			}
		});
		btnVerschlsseln.setBounds(1107, 621, 142, 23);
		pnVerschluesseln.add(btnVerschlsseln);
		
		JButton btnAktuellerSchlsselSpeichern = new JButton("Key speichern");
		btnAktuellerSchlsselSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				File f = new File(System.getProperty("user.home") + "\\Keys");
				f.mkdir();
				c.setCurrentDirectory(f);
				FileFilter filter = new FileNameExtensionFilter("Key Files", "key");
				c.setFileFilter(filter);
				int rVal = c.showSaveDialog(HauptGui.this);
				if(rVal == JFileChooser.APPROVE_OPTION){
					try {
						FileWriter fw;
						if(c.getSelectedFile().getAbsolutePath().contains(".key")){
							fw = new FileWriter(c.getSelectedFile().getAbsolutePath());
						}
						else{
							fw = new FileWriter(c.getSelectedFile().getAbsolutePath() + ".key");
						}
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(taKeyVer.getText());
						bw.newLine();
						bw.write(tfSec.getText());
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnAktuellerSchlsselSpeichern.setBounds(10, 620, 174, 25);
		pnVerschluesseln.add(btnAktuellerSchlsselSpeichern);
		
		JButton btnKeyffnen = new JButton("Key öffnen");
		btnKeyffnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				File f = new File(System.getProperty("user.home") + "\\Keys");
				f.mkdir();
				c.setCurrentDirectory(f);
				FileFilter filter = new FileNameExtensionFilter("Key Files", "key");
				c.setFileFilter(filter);
				int rVal = c.showOpenDialog(HauptGui.this);
				if(rVal == JFileChooser.APPROVE_OPTION){
					try {
						FileReader fr = new FileReader(c.getSelectedFile().getAbsolutePath());
						BufferedReader br = new BufferedReader(fr);
						taKeyVer.setText(br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine());
						tfSec.setText(br.readLine());
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnKeyffnen.setBounds(197, 620, 174, 25);
		pnVerschluesseln.add(btnKeyffnen);
		
		JButton btnTextdateiffnen = new JButton("Textdatei \u00F6ffnen");
		btnTextdateiffnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				File f = new File(System.getProperty("user.home") + "\\Verschluesselte_Dateien");
				f.mkdir();
				c.setCurrentDirectory(f);
				FileFilter filter = new FileNameExtensionFilter("txt-Files", "txt");
				c.setFileFilter(filter);
				int rVal = c.showOpenDialog(HauptGui.this);
				if(rVal == JFileChooser.APPROVE_OPTION){
					try {
						FileReader fr = new FileReader(c.getSelectedFile().getAbsolutePath());
						BufferedReader br = new BufferedReader(fr);
						String Text = "";
						String line = null;
						while((line = br.readLine()) != null){
							Text += line + "\n";
						}
						taUnverschluesselterText.setText(Text.substring(0, Text.length() - 1));
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnTextdateiffnen.setBounds(1075, 6, 174, 25);
		pnVerschluesseln.add(btnTextdateiffnen);
		
		JButton btnTextdateiSpeichern = new JButton("Textdatei speichern");
		btnTextdateiSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				File f = new File(System.getProperty("user.home") + "\\Verschluesselte_Dateien");
				f.mkdir();
				c.setCurrentDirectory(f);
				FileFilter filter = new FileNameExtensionFilter("Verschlüsselte Files", "ver");
				c.setFileFilter(filter);
				int rVal = c.showSaveDialog(HauptGui.this);
				if(rVal == JFileChooser.APPROVE_OPTION){
					try {
						BufferedWriter bw;
						if(c.getSelectedFile().getAbsolutePath().contains(".ver")){
							bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c.getSelectedFile().getAbsolutePath()), "Unicode"));
						}
						else{
							bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c.getSelectedFile().getAbsolutePath() + ".ver"), "Unicode"));
						}
						String test = taVerschluesselterText.getText();
						bw.write(test);
						bw.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
		});
		btnTextdateiSpeichern.setBounds(1075, 263, 174, 25);
		pnVerschluesseln.add(btnTextdateiSpeichern);
		
		JPanel pnEntschluesseln = new JPanel();
		tabbedPane.addTab("Entschlüsseln", null, pnEntschluesseln, null);
		pnEntschluesseln.setLayout(null);
		
		JLabel lblVerschlsselterText_1 = new JLabel("Verschl\u00FCsselter Text");
		lblVerschlsselterText_1.setBounds(10, 11, 1053, 14);
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
		lblEntschlsselterText.setBounds(381, 268, 682, 14);
		pnEntschluesseln.add(lblEntschlsselterText);
		
		JButton button = new JButton("Key öffnen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				File f = new File(System.getProperty("user.home") + "\\Keys");
				f.mkdir();
				c.setCurrentDirectory(f);
				FileFilter filter = new FileNameExtensionFilter("Key Files", "key");
				c.setFileFilter(filter);
				int rVal = c.showOpenDialog(HauptGui.this);
				if(rVal == JFileChooser.APPROVE_OPTION){
					try {
						FileReader fr = new FileReader(c.getSelectedFile().getAbsolutePath());
						BufferedReader br = new BufferedReader(fr);
						taKey.setText(br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine());
						tfGeheimnummer.setText(br.readLine());
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		button.setBounds(197, 620, 174, 25);
		pnEntschluesseln.add(button);
		
		JButton button_1 = new JButton("Textdatei \u00F6ffnen");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				File f = new File(System.getProperty("user.home") + "\\Verschluesselte_Dateien");
				f.mkdir();
				c.setCurrentDirectory(f);
				FileFilter filter = new FileNameExtensionFilter("Verschluesselte Files", "ver");
				c.setFileFilter(filter);
				int rVal = c.showOpenDialog(HauptGui.this);
				if(rVal == JFileChooser.APPROVE_OPTION){
					try {
						BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(c.getSelectedFile().getAbsolutePath()), "Unicode"));
						String Text = "";
						String line = null;
						while((line = br.readLine()) != null){
							Text += line + "\n";
						}
						taVerText.setText(Text.substring(0, Text.length() - 1));
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		button_1.setBounds(1075, 6, 174, 25);
		pnEntschluesseln.add(button_1);
		
		JButton btnTextdateiSpeichern_1 = new JButton("Textdatei speichern");
		btnTextdateiSpeichern_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				File f = new File(System.getProperty("user.home") + "\\Verschluesselte_Dateien");
				f.mkdir();
				c.setCurrentDirectory(f);
				FileFilter filter = new FileNameExtensionFilter("Textdatei", "txt");
				c.setFileFilter(filter);
				int rVal = c.showSaveDialog(HauptGui.this);
				if(rVal == JFileChooser.APPROVE_OPTION){
					try {
						FileWriter fw;
						if(c.getSelectedFile().getAbsolutePath().contains(".txt")){
							fw = new FileWriter(c.getSelectedFile().getAbsolutePath());
						}
						else{
							fw = new FileWriter(c.getSelectedFile().getAbsolutePath() + ".txt");
						}
						BufferedWriter bw = new BufferedWriter(fw);
						String test = taEntText.getText();
						bw.write(test);
						bw.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
		});
		btnTextdateiSpeichern_1.setBounds(1075, 263, 174, 25);
		pnEntschluesseln.add(btnTextdateiSpeichern_1);
	}
}

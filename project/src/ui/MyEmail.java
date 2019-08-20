package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import business.MyMail;
import entity.MailMessage;
import entity.SMTPServer;

public class MyEmail implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	JTextField txtFrom, txtTo, txtSubject, txtUsername;
	JPasswordField txtPassword;
	JComboBox<String> cbxServer;
	JTextArea txtMessage;
	JButton btnSend, btnAttach;
	JPanel pnlTop;
	JLabel from, to, subject, smtp, name, password, msg, attachment;
	final String ssl = "smtp.gmail.com (Gmail-SSL)";
	final String tsl = "smtp.gmail.com (Gmail-TSL)";
	final String yahoo = "smtp.mail.yahoo.com (Yahoo-TSL)";
	final String hotmail = "smtp.live.com (Hotmail-TSL)";
	
	public void createAndShowGUI() {
		//Create and 	set up the Window
		JFrame frame = new JFrame("Send E-Mail Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600, 1600);
		frame.setResizable(false);
		//Set the content pane
		addComponentsToPanel(frame.getContentPane());
		//Use the content pane's default BorderLayout. No need for
		//setLayout(new BorderLayout());
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public void addComponentsToPanel(final Container c) {
		c.setLayout(new GridBagLayout());
		c.setSize(1600, 1600);
	    int width = 400;
	    int height = 20;
		from = new JLabel("From: ");
		to = new JLabel("To: ");
		subject = new JLabel("Subject: ");
		smtp = new JLabel("SMTP Server: ");
		name = new JLabel("Username: ");
		password = new JLabel("Password: ");
		msg = new JLabel("Message: ");
		attachment = new JLabel("File path here");
		JScrollPane scrollPath = new JScrollPane(attachment);
		scrollPath.setPreferredSize(new Dimension(width, height + 20));
		
		btnSend = new JButton("Send E-mail");
		btnSend.setPreferredSize(new Dimension(width + 100,height));
		
		btnAttach = new JButton("Attach");
		btnAttach.setPreferredSize(new Dimension(80, height));
		
		txtFrom = new JTextField("somone@gmail.com");
		txtFrom.setPreferredSize(new Dimension(width,height));
		
		txtTo = new JTextField("somone@gmail.com");
		txtTo.setPreferredSize(new Dimension(width,height));
		
		txtSubject = new JTextField("Test mail");
		txtSubject.setPreferredSize(new Dimension(width,height));
		
		
		String[] option = { ssl, tsl, yahoo, hotmail };
		cbxServer = new JComboBox<>(option);
		cbxServer.setPreferredSize(new Dimension(width,height + 10));
		
		txtUsername = new JTextField("somone@gmail.com");
		txtUsername.setPreferredSize(new Dimension(width,height));
		
		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(width,height));
		
		txtMessage = new JTextArea("This is a test mail");
		txtMessage.setColumns(20);
		txtMessage.setLineWrap(true);
		JScrollPane scrollMessage = new JScrollPane(txtMessage);
		scrollMessage.setPreferredSize(new Dimension(width + 100, height+100));
		
		
		GridBagConstraints constraints = new GridBagConstraints();
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.insets = new Insets(10, 10, 5, 10);
	    constraints.anchor = GridBagConstraints.WEST;
		c.add(from, constraints);
		constraints.gridx = 1;
		c.add(txtFrom, constraints);
		
		constraints.gridy = 1;
        constraints.gridx = 0;
		c.add(to, constraints);
		
		constraints.gridx = 1;
		c.add(txtTo, constraints);
		
		constraints.gridy = 2;
        constraints.gridx = 0;
		c.add(subject, constraints);
		
		constraints.gridx = 1;
		c.add(txtSubject, constraints);
		
		constraints.gridy = 3;
        constraints.gridx = 0;
		c.add(smtp, constraints);
		
		constraints.gridx = 1;
		c.add(cbxServer, constraints);
		
		constraints.gridy = 4;
        constraints.gridx = 0;
		c.add(name, constraints);
		
		constraints.gridx = 1;
		c.add(txtUsername, constraints);
		
		constraints.gridy = 5;
        constraints.gridx = 0;
		c.add(password, constraints);
		
		constraints.gridx = 1;
		c.add(txtPassword, constraints);
		
		constraints.gridy = 6;
        constraints.gridx = 0;
		c.add(msg, constraints);
		
		constraints.gridy = 7;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
		c.add(scrollMessage, constraints);
		
		constraints.gridy = 8;
        constraints.gridx = 0;
        c.add(btnAttach, constraints);
		
		constraints.gridx = 1;
		c.add(scrollPath, constraints);
		
		constraints.gridy = 9;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
		c.add(btnSend, constraints);
		
		btnAttach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JFileChooser  fileDialog = new JFileChooser();
				int returnVal = fileDialog.showOpenDialog(null);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               java.io.File file = fileDialog.getSelectedFile();
	               attachment.setText(file.getAbsolutePath());
	            }
	            else{
	            	//attachment.setText("Open command cancelled by user." );           
	            }    
			}
		});
		
		
		btnSend.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MailMessage mm = new MailMessage(txtFrom.getText(), txtTo.getText(), txtSubject.getText(), txtMessage.getText(), attachment.getText());
				
				SMTPServer mailServer = new SMTPServer("server", "authithencation", "port");
				String server = cbxServer.getSelectedItem().toString();
				switch (server) {
				case ssl:
					mailServer.setServer("smtp.gmail.com");
					mailServer.setAuthithencation("SSL");
					mailServer.setPort("465");
					break;
				case tsl:
					mailServer.setServer("smtp.gmail.com");
					mailServer.setAuthithencation("TSL");
					mailServer.setPort("587");
					break;
				case yahoo:
					mailServer.setServer("smtp.mail.yahoo.com");
					mailServer.setAuthithencation("TSL");
					mailServer.setPort("587");
					break;
				case hotmail:
					mailServer.setServer("smtp.live.com");
					mailServer.setAuthithencation("TSL");
					mailServer.setPort("587");
					break;	
				default:
					break;
				}
				
				MyMail myMail = new MyMail();
				try {
					Boolean b = myMail.sendMail(mm, myMail.getMailSession(mailServer, txtFrom.getText(), txtPassword.getText()));
					if (b) {
						JOptionPane.showMessageDialog(c, "Message sent to " + txtTo.getText());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(c, "Error while sending the e-mail to " + txtTo.getText());
					e.printStackTrace();
				}
				
			}
		});
	};
}
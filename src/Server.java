import javax.swing.*;
import java.net.*;
import javax.swing.border.EmptyBorder;
import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Calendar;
public class Server   implements ActionListener{
	
	JTextField text;
	JPanel top;
	static Box vertical = Box.createVerticalBox();
	JPanel background;
	static JFrame frame = new JFrame();
	static DataOutputStream out;
	Server(){
			
		frame.setLayout(null);
		top = new JPanel();
		top.setBackground(new Color(0x43CC47));
		top.setBounds(0, 0, 500, 60);
		top.setLayout(null);
		
		frame.add(top);
		
		ImageIcon backArrow = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
		Image backArrow1 = backArrow.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon backArrow2 = new ImageIcon(backArrow1);
		JLabel back = new JLabel(backArrow2);
		back.setBounds(7,15,30,30);
		top.add(back);
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent	ae) {
				System.exit(0);
			}
		});
		
		ImageIcon pfp = new ImageIcon(ClassLoader.getSystemResource("icons/eren.jpeg"));
		Image pfp1 = pfp.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		ImageIcon pfp2 = new ImageIcon(pfp1);
		JLabel profile = new JLabel(pfp2);
		profile.setBounds(50,0,60,60);
		top.add(profile);
		
		ImageIcon video = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
		Image video1 = video.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		ImageIcon video2 = new ImageIcon(video1);
		JLabel video3 = new JLabel(video2);
		video3.setBounds(350,15,35,35);
		top.add(video3);
		
		ImageIcon phone = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
		Image phone1 = phone.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		ImageIcon phone2 = new ImageIcon(phone1);
		JLabel phone3 = new JLabel(phone2);
		phone3.setBounds(405,15,35,35);
		top.add(phone3);
		
		ImageIcon option = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
		Image option1 = option.getImage().getScaledInstance(15, 25, Image.SCALE_DEFAULT);
		ImageIcon option2 = new ImageIcon(option1);
		JLabel option3 = new JLabel(option2);
		option3.setBounds(460,20,15,25);
		top.add(option3);
		
		JLabel name = new JLabel("Eren");
		name.setBounds(130,10,100,18);
		name.setForeground(Color.white);
		name.setFont(new Font("SAN_SHERIF",Font.BOLD,20));
		top.add(name);
		
		JLabel status = new JLabel("Active");
		status.setBounds(135,30,80,16);
		status.setForeground(Color.white);
		status.setFont(new Font("SAN_SHERIF",Font.BOLD,12));
		top.add(status);
		
		background = new JPanel();
		background.setBounds(0,61,500,590);
		//background.setBackground(Color.blue);
		frame.add(background);
		
		text = new JTextField();
		text.setBounds(5,655,360,40);
		text.setFont(new Font("SANS_SHERIF",Font.PLAIN,16));
		frame.add(text);
		
		
		JButton send = new JButton("Send");		
		send.setBounds(370,655,123,40);
		send.setBackground(new Color(0x43CC47));
		send.setForeground(Color.white);
		send.addActionListener(this);
		send.setFont(new Font("SANS_SHERIF",Font.PLAIN,16));
		frame.add(send);
		
		
		
		frame.setUndecorated(true);
		frame.setSize(500,700);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocation(150,60);
		frame.getContentPane().setBackground(Color.white);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		// TODO Auto-generated method stub
		String output = text.getText();
		JLabel out1 = new JLabel(output);
		JPanel p2 = formatLabel(output);
		
		
		
		background.setLayout(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		right.add(p2,BorderLayout.LINE_END);
		vertical.add(right);
		vertical.add(Box.createVerticalStrut(15));
		
		background.add(vertical,BorderLayout.PAGE_START);
		
		out.writeUTF(output);
		
		text.setText("");
		frame.repaint();
		frame.invalidate();
		frame.validate();
		
		}
		catch(Exception excep) {
			excep.printStackTrace();
		}
		
;	}
	public static JPanel formatLabel(String out) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel output = new JLabel(out);
		output.setFont(new Font("Tahoma",Font.PLAIN,16));
		output.setBackground(new Color(0x1982FC));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50));
		output.setForeground(Color.white);
		panel.add(output);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		JLabel time = new JLabel();
		time.setText(sdf.format(cal.getTime()));
		
		panel.add(time);
		
		
		return panel;
	}
	public static void main(String[] args) {
		new Server();
		try {
			ServerSocket skt = new ServerSocket(6001);
			while(true) {
				Socket s = skt.accept();
				DataInputStream in = new DataInputStream(s.getInputStream());
				out = new DataOutputStream(s.getOutputStream());
				
				while(true) {
					String message = in.readUTF();
					JPanel panel = formatLabel(message);
					JPanel left = new JPanel(new BorderLayout());
					left.add(panel, BorderLayout.LINE_START);
					vertical.add(left);
					frame.validate();
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

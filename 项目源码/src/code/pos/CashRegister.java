package code.pos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import code.goods.GoodManage;

public class CashRegister extends JFrame implements ActionListener,WindowListener {
	JTextField text;
	String time = null;
	JLabel label1 = new JLabel("日期：");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	JButton button1 = new JButton("新建购物车");
	JButton button2 = new JButton("管理商品");
	JButton button3 = new JButton("退出系统");
	JFrame jFrame=new JFrame();
	ImageIcon CashRegisterPic = new ImageIcon("imag/CashRegisterImag/CashRegisterImag.jpg");
	public CashRegister() {
		text = new JTextField();
		setLayout(null);
		text.setBounds(40, 10, 150, 20);
		jFrame.getContentPane().add(text);
		text.setText(getTime());
		Timer timer = new Timer();
		timer.schedule(new RemindTask(), 0, 1000);
		text.setEditable(false);
		jFrame.setTitle("收银台");
        jFrame.setSize(700,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().setLayout(null);
        JPanel imPanel=(JPanel) jFrame.getContentPane();
        imPanel.setOpaque(false);
        JLabel label = new JLabel(CashRegisterPic);
        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
        CashRegisterPic.setImage(CashRegisterPic.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
		label1.setBounds(10, 10, 50, 20);
		label1.setForeground(Color.white);
		button1.setBounds(150, 140, 140, 20);
		button1.addActionListener(this);
		button2.setBounds(150, 200, 140, 20);
		button2.addActionListener(this);
		button3.setBounds(150, 260, 140, 20);
		button3.addActionListener(this);
		jFrame.getContentPane().add(label1);
		jFrame.getContentPane().add(button1);
		jFrame.getContentPane().add(button2);
		jFrame.getContentPane().add(button3);
		jFrame.setVisible(true);
		jFrame.setResizable(false);
		jFrame.addWindowListener(this);
	}
	public static int carNum = 0;
	ShopCar s[] = new ShopCar[100];
	public void actionPerformed(ActionEvent e) {
		JdbcConnection jdbcConn = new JdbcConnection();
		Connection conn = jdbcConn.dbConnection();
		PreparedStatement stat = null;
		if (e.getActionCommand().equals("新建购物车")) {
			carNum++;
			s[carNum] = new ShopCar(carNum);
		}
		if (e.getActionCommand().equals("管理商品")) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GoodManage frame = new GoodManage();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		if (e.getActionCommand().equals("退出系统")) {
				jFrame.dispose();
				new SystemClose();
		}
	}
	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = (Date) calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = format.format(date);

		return time;
	}
	private class RemindTask extends TimerTask {
		public void run() {
			text.setText(getTime());
		}
	}
	public void windowOpened(WindowEvent e) {
		//此处无动作
	}
	public void windowClosing(WindowEvent e) {
			jFrame.dispose();
			new SystemClose();
	}
	public void windowClosed(WindowEvent e) {

	}
	public void windowIconified(WindowEvent e) {
	
	}
	public void windowDeiconified(WindowEvent e) {
		
	}
	public void windowActivated(WindowEvent e) {
		
	}
	public void windowDeactivated(WindowEvent e) {
	
	}
}
class SystemClose extends JFrame implements ActionListener {
	public int windowWidth = 300;
	public int windowHeight = 200;
	public SystemClose() {
		setTitle("再见！");

		JButton button1 = new JButton("退出");
		button1.setBounds(100, 70, 70, 30);
		button1.addActionListener(this);
		add(button1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(450, 150, windowWidth, windowHeight);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		dispose();
		System.exit(0);
	}
}

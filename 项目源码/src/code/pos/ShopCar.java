package code.pos;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;

import code.goods.GoodSearch;
import code.goods.GoodsInformation;

public class ShopCar extends JFrame implements ActionListener {
	public static double MONEY;
	JdbcConnection jdbcConn = new JdbcConnection();
	Connection conn = jdbcConn.dbConnection();
	PreparedStatement stat = null;
	private int countNum=0;
	JLabel label1 = new JLabel("��������Ʒ��ţ�");
	JTextField goodId = new JTextField("");
	JLabel label2 = new JLabel("��������Ʒ���ƣ�");
	JTextField goodName = new JTextField("");
	JLabel label3 = new JLabel("��Ʒ�۸�");
	JTextField goodPrice = new JTextField("");
	JLabel label4 = new JLabel("��Ʒ������");
	JTextField goodCount = new JTextField("");
	JLabel label5 = new JLabel("�������¼��ţ�");
	JTextField goodRecord = new JTextField("");
	JLabel label6 = new JLabel("���Է���localhost:8080/shoplist.html��ù���ƾ��~");
	JLabel label7 = new JLabel("������㹺�ﳵ�󡪡�");
	JTextArea showShopcar = new JTextArea("");
	JButton button1 = new JButton("����");
	JButton button2 = new JButton("ɾ��");
	JButton button3 = new JButton("�޸�");
	JButton button4 = new JButton("ˢ��");
	JButton button5 = new JButton("�鿴��Ʒ�б�");
	JButton button6 = new JButton("ɾ�����ﳵ");
	JButton button7 = new JButton("���㹺�ﳵ");
	JLabel warningText = new JLabel("");
	JFrame jFrame=new JFrame();
	ImageIcon shopCarImage=new ImageIcon("imag/ShopCarImage.jpg");
	public ShopCar(int carNum) {
		this.carNum = carNum;
		setCar();
		jFrame.setTitle("���ﳵ" + carNum + "��");
        jFrame.setSize(780,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().setLayout(null);
        JPanel imPanel=(JPanel) jFrame.getContentPane();
        imPanel.setOpaque(false);
        JLabel label = new JLabel(shopCarImage);
        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
        shopCarImage.setImage(shopCarImage.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//ͼƬ����Ӧ��ǩ��С
        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
        label1.setBounds(60, 20, 100, 20);
		label2.setBounds(60, 50, 100, 20);
		label3.setBounds(60, 80, 100, 20);
		label4.setBounds(60, 110, 100, 20);
		label5.setBounds(60, 140, 100, 20);
		label7.setBounds(10, 160, 300, 20);
		label6.setBounds(10, 170, 300, 20);
		goodId.setBounds(160, 20, 150, 20);
		goodName.setBounds(160, 50, 150, 20);
		goodPrice.setBounds(160, 80, 150, 20);
		goodCount.setBounds(160, 110, 150, 20);
		goodRecord.setBounds(160, 140, 150, 20);
		warningText.setBounds(180, 180, 150, 20);
		button1.setBounds(100, 210, 70, 20);
		button1.addActionListener(this);
		button2.setBounds(180, 210, 70, 20);
		button2.addActionListener(this);
		button3.setBounds(100, 240, 70, 20);
		button3.addActionListener(this);
		button4.setBounds(180, 240, 70, 20);
		button4.addActionListener(this);
		button5.setBounds(100, 270, 150, 20);
		button5.addActionListener(this);
		button6.setBounds(100, 300, 150, 20);
		button6.addActionListener(this);
		button7.setBounds(100, 330, 150, 20);
		button7.addActionListener(this);
		showShopcar.setBounds(340, 20, 400, 330);
		showShopcar.setEditable(false);
        jFrame.getContentPane().add(label1);
        jFrame.getContentPane().add(goodId);
        jFrame.getContentPane().add(label2);
        jFrame.getContentPane().add(goodName);
        jFrame.getContentPane().add(label3);
        jFrame.getContentPane().add(goodPrice);
        jFrame.getContentPane().add(label4);
        jFrame.getContentPane().add(goodCount);
        jFrame.getContentPane().add(label5);
        jFrame.getContentPane().add(label6);
        jFrame.getContentPane().add(label7);
        jFrame.getContentPane().add(goodRecord);
        jFrame.getContentPane().add(button1);
        jFrame.getContentPane().add(button2);
        jFrame.getContentPane().add(button3);
        jFrame.getContentPane().add(button4);
        jFrame.getContentPane().add(button5);
        jFrame.getContentPane().add(button6);
        jFrame.getContentPane().add(button7);
        jFrame.getContentPane().add(showShopcar);
        jFrame.getContentPane().add(warningText);
        jFrame.setVisible(true);//���ô��ڿɼ�
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//���ô��ڹرռ����X��û���κη�Ӧ
		jFrame.setResizable(false);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ɾ�����ﳵ")) {
			
			int n=0;//��=0����=1
			n = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ�����ﳵ��?", "",JOptionPane.YES_NO_OPTION); //����ֵΪ0��1
			System.out.println("n="+n);
			if(n==0) {
				goodName.setText("");
				goodPrice.setText("");
				goodId.setText("");

				String delete = "DROP TABLE shopcar" + carNum;
				try {
					stat = conn.prepareStatement(delete);
					stat.executeUpdate(delete);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				jFrame.dispose();
			}
			else {
				//�˴��޶���
			}	
		}
		if (e.getActionCommand().equals("����")) {
			
			String sql1 = "insert into shopcar" + carNum + " (goodid,goodname,goodprice) values(?,?,?)";
			if(goodRecord.getText().equals("")) {
				if(!goodId.getText().equals("") && goodName.getText().equals("") && goodPrice.getText().equals("")) {
					try {
						String check = "select * from good where id='" + goodId.getText() + "'";
						Statement stmt = conn.createStatement();
						ResultSet rsCheck = stmt.executeQuery(check);
						if (rsCheck.next()) {
							if(!goodCount.getText().equals("")){
								if(Integer.valueOf(rsCheck.getString("number")).intValue()!=0) {
									if(Double.valueOf(goodCount.getText()).doubleValue()<=Double.valueOf(rsCheck.getString("number")).doubleValue()) {
										stat = conn.prepareStatement(sql1);
										stat.setInt(1, Integer.valueOf(goodId.getText()).intValue());
										stat.setString(2, rsCheck.getString("name"));
										stat.setDouble(3, Double.valueOf(rsCheck.getString("price")).doubleValue()*Double.valueOf(goodCount.getText()).doubleValue());
										stat.executeLargeUpdate();						
										JOptionPane.showMessageDialog(this, "������һ����¼");
										System.out.println("������һ����¼");
										int goodid=0;
										goodid=Integer.valueOf(goodId.getText()).intValue();
										String name=rsCheck.getString("name");
										double price=0;
										price=Double.valueOf(rsCheck.getString("price")).doubleValue();
										int number=0;
										number=Integer.valueOf(rsCheck.getString("number")).intValue()-Integer.valueOf(goodCount.getText()).intValue();
										GoodsInformation good = new GoodsInformation(goodid,name,price,number);
										good.updata();
										Refresh();
										cleanText();
									}else {
										JOptionPane.showMessageDialog(this, "��Ʒ����ӦС�ڻ�������");
										System.out.println("��Ʒ����ӦС�ڻ�������");
										Refresh();
										cleanText();
									}
								}else {
									JOptionPane.showMessageDialog(this, "����Ʒ������");
									System.out.println("����Ʒ������");
									Refresh();
									cleanText();
								}
							}else{
								JOptionPane.showMessageDialog(this, "��������Ʒ����");
								System.out.println("��������Ʒ����");
								Refresh();
								cleanText();
							}
						}
						else {
							JOptionPane.showMessageDialog(this, "δ�鵽�ñ����Ʒ��", "����", JOptionPane.ERROR_MESSAGE);
							cleanText();
						}
						
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
				else if(!goodId.getText().equals("") && (!goodName.getText().equals("") || !goodPrice.getText().equals(""))) {
					JOptionPane.showMessageDialog(this, "�����Ʒ���빺�ﳵʱ������ͨ����Ʒ�����ӣ�", "����", JOptionPane.ERROR_MESSAGE);
					cleanText();
				}
				else {
					JOptionPane.showMessageDialog(this, "δ������Ʒ��ţ�", "����", JOptionPane.ERROR_MESSAGE);
					cleanText();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "�����Ʒ���빺�ﳵʱ������ͨ����Ʒ�����ӣ�\n��¼���ֻ�������޸��Լ�ɾ�����ﳵ����", "����", JOptionPane.ERROR_MESSAGE);
				cleanText();
			}
			
		}
		if (e.getActionCommand().equals("ɾ��")) {
			if (!goodRecord.getText().equals("") && goodId.getText().equals("") && goodName.getText().equals("") && goodPrice.getText().equals("")) {
				String delete = "delete from shopcar" + carNum + " where goodrecord=?";
				String check = "select * from shopcar" + carNum + " where goodrecord='" + goodRecord.getText() + "'";// ������ɾ������Ҫ�ݼ�
				try {
					Statement stmt = conn.createStatement();
					ResultSet rsCheck = stmt.executeQuery(check);
					if (rsCheck.next()) {
						System.out.println("delete");
						stat = conn.prepareStatement(delete);
						stat.setInt(1, Integer.valueOf(goodRecord.getText()).intValue());
						stat.executeLargeUpdate();
						JOptionPane.showMessageDialog(this, "ɾ����һ����¼");
						System.out.println("ɾ����һ����¼");//������������������ɾ�������Ʒ��¼��ſ��Լ������������߶������м��һ��
						//1,ɾ��ԭ��������
						String deleteKey="ALTER TABLE `shopcar" + carNum + "` DROP `goodrecord`;";
						stat = conn.prepareStatement(deleteKey);
						stat.executeLargeUpdate();
						//2,����������ֶΣ�
						String addKey="ALTER TABLE `shopcar" + carNum + "` ADD `goodrecord` int NOT NULL FIRST;";
						stat = conn.prepareStatement(addKey);
						stat.executeLargeUpdate();
						//3,������������
						String newKey="ALTER TABLE `shopcar" + carNum + "` MODIFY COLUMN `goodrecord` int NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY(goodrecord);";
						stat = conn.prepareStatement(newKey);
						stat.executeLargeUpdate();
						
						Refresh();
					} else {
						JOptionPane.showMessageDialog(this, "�����ڸü�¼��", "����", JOptionPane.ERROR_MESSAGE);
					}
					cleanText();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			} 
			else if(goodRecord.getText().equals("")&& goodId.getText().equals("") && goodName.getText().equals("") && goodPrice.getText().equals("")){
				JOptionPane.showMessageDialog(this, "δ�����¼��ţ�", "����", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "ɾ��ʱ������ͨ����¼���ɾ����", "����", JOptionPane.ERROR_MESSAGE);
			}
			cleanText();
		}
		if (e.getActionCommand().equals("�޸�")) {//������Ĭ�ϲ��޸�
			String check = "select * from shopcar" + carNum + " where goodrecord='" + goodRecord.getText() + "'";	
			if(goodId.getText().equals("")) {
				if(!goodRecord.getText().equals("")) {
					String modify = "update shopcar" + carNum + " set goodname=?,goodprice=? where goodrecord="+ goodRecord.getText();
					try {
						Statement stmt = conn.createStatement();
						ResultSet rsCheck = stmt.executeQuery(check);
						if (rsCheck.next()) {
							stat = conn.prepareStatement(modify);
							if (!goodName.getText().equals("")) {
								stat.setString(1, goodName.getText());
							} else {
								stat.setString(1, rsCheck.getString("goodName"));
							}
							if (!goodPrice.getText().equals("")) {
								stat.setDouble(2, Double.valueOf(goodPrice.getText()).doubleValue());
							} else {
								stat.setDouble(2, Double.valueOf(rsCheck.getString("goodprice")).doubleValue());
							}
							stat.executeLargeUpdate();
							JOptionPane.showMessageDialog(this, "�޸���һ����¼");
							System.out.println("�޸���һ����¼");
							Refresh();
						} else {
							JOptionPane.showMessageDialog(this, "�����ڸü�¼��", "����", JOptionPane.ERROR_MESSAGE);
						}
						cleanText();
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "������Ҫ�޸ĵĹ��ﳵ�е���Ʒ��¼��ţ�", "����", JOptionPane.ERROR_MESSAGE);
					cleanText();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "��ƷID�޷����ģ�", "����", JOptionPane.ERROR_MESSAGE);
				cleanText();
			}
			
		}
		if (e.getActionCommand().equals("ˢ��")) {
			Refresh();
		}
		if (e.getActionCommand().equals("�鿴��Ʒ�б�")) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GoodSearch frame = new GoodSearch();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		if (e.getActionCommand().equals("���㹺�ﳵ")) {
			countNum++;
			int n=0;//��=0����=1
			if(countNum>1) {
				n = JOptionPane.showConfirmDialog(null, "�Ѿ�������ˣ���ȷ��Ҫ�ٴν�����?", "��ʾ",JOptionPane.YES_NO_OPTION); //����ֵΪ0��1
			}
			System.out.println("n="+n);
			if(n==0) {
				new CountShopCar(carNum);
				String s="";
				s="<!DOCTYPE html>"+"\n"+"<html>"+"\n"+"<head>"+"\n"+"<meta charset=\"gbk\">"+"\n"+"<title>xx���н���ҳ��</title>"+"\n"+"</head>"+"\n"+"<body>"+"\n"+"<h1>�������</h1>"+"\n"+"<p>		��  ӭ  ��  ��  x  x  ��  ��<p>"+"\n"+"<p>---------------------------------------------------------------<p>"+"\n"+showShopcar.getText()+"\n"+"<p><p>"+"\n"+"<p>"+"�ܼ�Ϊ��"+String.valueOf(MONEY)+"Ԫ"+"<p>"+"\n"+"<p>---------------------------------------------------------------<p>"+"\n"+"<p>		��  ��  ��  ��  ��  ��  ��</p>"
						+ "\n"+"</body>"+"\n"+"</html>"+"\n";
				try {
				System.out.println(s);
				Writer fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("shoplist.html"), "UTF-8"));
				fw.write(s);
				fw.flush();
				fw.close();
				}catch(IOException e3) {
					System.out.println(e3);
				}
			}
			else {
				//�˴��޶���
			}
			
		}

	}
	public void cleanText() {
		goodId.setText("");
		goodName.setText("");
		goodPrice.setText("");
		goodRecord.setText("");
	}

	public void Refresh() {
		String search = "select * from shopcar" + carNum;
		String info = "";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rsCheck = stmt.executeQuery(search);
			while (rsCheck.next()) {
				info += "<p>"+"��¼��ţ�" + rsCheck.getString("goodrecord")
				+ "����Ʒ��ţ�" + rsCheck.getString("goodid")
				+ "����Ʒ���ƣ�"+ rsCheck.getString("goodname") 
				+ "����Ʒ�۸�" + rsCheck.getString("goodprice") + "<p>" + "\n";
				showShopcar.setText(info);
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}

	public static void main(String[] args) {
		
	}
	public int carNum = 0;
	public void setCar() {
		String check = "show tables like 'shopcar" + carNum + "';";
		String delete = "DROP TABLE shopcar" + carNum;
		String set = "CREATE TABLE shopcar" + carNum + " (" + "`goodrecord` INT UNSIGNED AUTO_INCREMENT,"
				+ "`goodid` INT(11)not null," + "`goodname` VARCHAR(255)not null,"
				+ "`goodprice` decimal(11,2)not null," + "PRIMARY KEY ( `goodrecord` )"
				+ ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		try {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();
			Statement stmt = conn.createStatement();
			System.out.println("check1");
			ResultSet rsCheck = stmt.executeQuery(check);
			System.out.println("check2");
			if (rsCheck.next()) {
				System.out.println("delete");
				stat = conn.prepareStatement(delete);
				stat.executeLargeUpdate();
			}
			System.out.println("creat");
			stat = conn.prepareStatement(set);
			stat.executeUpdate(set);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}

class CountShopCar extends JFrame implements ActionListener{
	JLabel money = new JLabel("");
	JButton alipay = new JButton("AliPay");
	JButton wechatpay = new JButton("WeChatPay");
	private int carNum;
	public double allMoney;
	ImageIcon PayWayPic = new ImageIcon("imag/PayWay.jpg");
	JFrame jFrame=new JFrame(); 
	public CountShopCar(int carNum) {
		this.carNum = carNum;
		countMoney();
	}

	public void countMoney() {
		try {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();
			Statement stmt = conn.createStatement();
			String search = "select * from shopcar" + carNum;
			ResultSet rsCheck = stmt.executeQuery(search);
			int flag=0;
			while (rsCheck.next()) {
				flag++;
				allMoney += Double.valueOf(rsCheck.getString("goodprice")).doubleValue();
			}
			if(flag==0) {
				JOptionPane.showMessageDialog(this, "���ﳵΪ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			}
			else {
				
				jFrame.setTitle("���ﳵ" + carNum + "��");
		        jFrame.setSize(400,600);
		        jFrame.setLocationRelativeTo(null);
		        jFrame.getContentPane().setLayout(null);
		        JPanel imPanel=(JPanel) jFrame.getContentPane();
		        imPanel.setOpaque(false);
		        JLabel label = new JLabel(PayWayPic);
		        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
		        PayWayPic.setImage(PayWayPic.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
		        
		        alipay.setBounds(60, 330, 120, 20);
				alipay.addActionListener(this);
				wechatpay.setBounds(210, 330, 120, 20);
				wechatpay.addActionListener(this);
				jFrame.getContentPane().add(money);
				jFrame.getContentPane().add(alipay);
				jFrame.getContentPane().add(wechatpay);
		        money.setText("��Ʒ�ܼ�Ϊ��"+allMoney);
		        money.setFont(new Font("��������", Font.PLAIN, 20));
		        money.setForeground(Color.white);
		        money.setBounds(jFrame.getWidth()/4,jFrame.getHeight()/2-28,jFrame.getWidth()/2,50);
		        money.setHorizontalAlignment(JLabel.CENTER);
		        jFrame.getContentPane().add(money);
		        jFrame.setVisible(true);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("allMoney="+allMoney);
		ShopCar.MONEY=allMoney;
	}
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("AliPay")) {
			jFrame.dispose();
			new AliPay(carNum);
		}
		if (e.getActionCommand().equals("WeChatPay")) {
			jFrame.dispose();
			new WeChatPay(carNum);
		}
	}
	
}
class AliPay extends JFrame implements ActionListener{
	JButton done=new JButton("֧�����");
	ImageIcon AliPayPic = new ImageIcon("imag/Gathering/AliPay.png");
	public AliPay(int carNum) {
		done.setBounds(160, 80, 120, 20);
		done.addActionListener(this);
		add(done);
		setTitle("���ﳵ" + carNum + "��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(550, 140, 450, 638);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	public void paint(Graphics g) {
		g.drawImage(AliPayPic.getImage(), 0, 20, null);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("֧�����")) {
			dispose();
		}
	}
}
class WeChatPay extends JFrame implements ActionListener{
	JButton done=new JButton("֧�����");
	ImageIcon WeChatPayPic = new ImageIcon("imag/Gathering/WeChatPay.png");
	public WeChatPay(int carNum) {
		done.setBounds(150, 450, 120, 20);
		done.addActionListener(this);
		add(done);
		setTitle("���ﳵ" + carNum + "��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(550, 140, 450, 638);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	public void paint(Graphics g) {
		g.drawImage(WeChatPayPic.getImage(), 0, 20, null);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("֧�����")) {
			dispose();
		}
	}
	
}

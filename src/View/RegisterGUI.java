package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import repository.HastaRepository;
import repository.HastaRepositoryImp;
import repository.UserRepository;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JLabel lblTcNumaras;
	private JTextField fld_tcno;
	private JLabel lblifre;
	private JPasswordField fld_password;
	private HastaRepository hastaRepository;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() { 
		hastaRepository = new HastaRepositoryImp();
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		label.setBounds(10, 10, 102, 22);
		w_pane.add(label);

		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 31, 266, 33);
		w_pane.add(fld_name);

		lblTcNumaras = new JLabel("T.C. Numarası");
		lblTcNumaras.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		lblTcNumaras.setBounds(10, 74, 102, 22);
		w_pane.add(lblTcNumaras);

		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 95, 266, 33);
		w_pane.add(fld_tcno);

		lblifre = new JLabel("Şifre");
		lblifre.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		lblifre.setBounds(10, 138, 102, 22);
		w_pane.add(lblifre);

		fld_password = new JPasswordField();
		fld_password.setBounds(10, 170, 266, 33);
		w_pane.add(fld_password);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_tcno.getText().length() == 0 || fld_password.getText().length() == 0
						|| fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = hastaRepository.register(fld_tcno.getText(), fld_password.getText(),
								fld_name.getText());							
						if (control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		btn_register.setBackground(Color.GRAY);
		btn_register.setBounds(10, 213, 266, 33);
		w_pane.add(btn_register);

		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		btn_backto.setBackground(Color.GRAY);
		btn_backto.setBounds(10, 256, 266, 33);
		w_pane.add(btn_backto);
	}
}

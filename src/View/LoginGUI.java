package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;
import repository.DoctorRepository;
import repository.HastaRepository;
import repository.HastaRepositoryImp;
import repository.UserRepository;
import repository.UserRepositoryImpl;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;

	private UserRepository userRepository;
	private HastaRepository hastaRepository;
	private JPasswordField fld_hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {

		this.userRepository = new UserRepositoryImpl();
		hastaRepository = new HastaRepositoryImp();

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 459);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("Hastane.png")));
		lbl_logo.setBackground(Color.WHITE);
		lbl_logo.setBounds(202, 10, 165, 167);
		w_pane.add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel.setBounds(94, 176, 390, 33);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 221, 522, 191);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lblTcNumaranz = new JLabel("T.C Numaranız : ");
		lblTcNumaranz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblTcNumaranz.setBounds(10, 9, 143, 33);
		w_hastaLogin.add(lblTcNumaranz);

		JLabel lblifre = new JLabel("Şifre :  ");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblifre.setBounds(10, 52, 143, 33);
		w_hastaLogin.add(lblifre);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		fld_hastaTc.setBounds(163, 9, 336, 33);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setBackground(Color.GRAY);
		btn_register.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btn_register.setBounds(34, 95, 222, 44);
		w_hastaLogin.add(btn_register);

		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hastaTc.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					String tcno = fld_hastaTc.getText();
					String password = fld_hastaPass.getText();

					if (hastaRepository.hastaCheckTcPassword(tcno, password)) {
						Hasta h;
						try {
							h = hastaRepository.getHastaByTc(tcno);
							HastaGUI hGUI = new HastaGUI(h);
							hGUI.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					} else {
						Helper.showMsg("error");

					}

				}

			}
		});
		btn_hastaLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_hastaLogin.setBackground(Color.WHITE);
		btn_hastaLogin.setBounds(277, 95, 222, 44);
		w_hastaLogin.add(btn_hastaLogin);

		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(163, 52, 337, 33);
		w_hastaLogin.add(fld_hastaPass);

		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);

		JLabel lblTcNumaranz_1 = new JLabel("T.C Numaranız : ");
		lblTcNumaranz_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblTcNumaranz_1.setBounds(10, 10, 143, 33);
		w_doctorLogin.add(lblTcNumaranz_1);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(163, 10, 336, 33);
		w_doctorLogin.add(fld_doctorTc);

		JLabel lblifre_1 = new JLabel("Şifre :  ");
		lblifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblifre_1.setBounds(10, 53, 143, 33);
		w_doctorLogin.add(lblifre_1);

		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					String tcno = fld_doctorTc.getText();
					String password = fld_doctorPass.getText();

					if (userRepository.basHekimCheckTcPassword(tcno, password)) {
						Bashekim b;
						try {
							b = userRepository.getBasHekimByTc(tcno);
							BashekimGUI bGUI = new BashekimGUI(b);
							bGUI.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					}
					if (userRepository.doctorCheckTcPassword(tcno, password)) {
						Doctor d;
						try {
							d = userRepository.getDoctorByTc(tcno);
							DoctorGUI dGUI = new DoctorGUI(d);
							dGUI.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					} else {
						Helper.showMsg("error");
					}

				}
			}
		});
		btn_doctorLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_doctorLogin.setBackground(Color.WHITE);
		btn_doctorLogin.setBounds(10, 96, 489, 44);
		w_doctorLogin.add(btn_doctorLogin);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(162, 53, 337, 33);
		w_doctorLogin.add(fld_doctorPass);
	}
}

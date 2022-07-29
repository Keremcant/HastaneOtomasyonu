package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;
import repository.ClinicRepository;
import repository.ClinicRepositoryImpl;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_clinicName;
	private ClinicRepository clinicRepository;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		
		clinicRepository = new ClinicRepositoryImpl();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPolikinlikAd = new JLabel("Polikinlik Adı");
		lblPolikinlikAd.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		lblPolikinlikAd.setBounds(10, 10, 236, 22);
		contentPane.add(lblPolikinlikAd);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(10, 42, 236, 33);
		fld_clinicName.setText(clinic.getName());
		contentPane.add(fld_clinicName);
		
		JButton btn_updateClinic = new JButton("Düzenle");
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						clinicRepository.uptadeClinic(clinic.getId(), fld_clinicName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateClinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		btn_updateClinic.setBackground(Color.GRAY);
		btn_updateClinic.setBounds(10, 85, 236, 33);
		contentPane.add(btn_updateClinic);
	}
}

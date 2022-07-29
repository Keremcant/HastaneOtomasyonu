package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;
import repository.ClinicRepository;
import repository.ClinicRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {
	Clinic clinic = new Clinic();
	static Bashekim bashekim = new Bashekim();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_dPass;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;
	
	private UserRepository userRepository;
	private ClinicRepository clinicRepository;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {

		userRepository = new UserRepositoryImpl();
		clinicRepository = new ClinicRepositoryImpl();
		
		// Doktor model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < userRepository.getDoctorList().size(); i++) {
			doctorData[0] = userRepository.getDoctorList().get(i).getId();
			doctorData[1] = userRepository.getDoctorList().get(i).getName();
			doctorData[2] = userRepository.getDoctorList().get(i).getTcno();
			doctorData[3] = userRepository.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

		// Clinic model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Polikinlik Adı";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinicRepository.getList().size(); i++) {
			clinicData[0] = clinicRepository.getList().get(i).getId();
			clinicData[1] = clinicRepository.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		
		//WorkerModel
		
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 540);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 598, 36);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnNewButton.setBounds(618, 21, 108, 21);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 56, 716, 437);
		w_pane.add(w_tab);

		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);

		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		label.setBounds(486, 10, 102, 22);
		w_doctor.add(label);

		fld_dName = new JTextField();
		fld_dName.setBounds(486, 31, 170, 33);
		w_doctor.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel label_1 = new JLabel("T.C. No");
		label_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		label_1.setBounds(486, 74, 102, 22);
		w_doctor.add(label_1);

		fld_dTcno = new JTextField();
		fld_dTcno.setBounds(486, 106, 170, 33);
		w_doctor.add(fld_dTcno);
		fld_dTcno.setColumns(10);

		JLabel label_2 = new JLabel("Şifre");
		label_2.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		label_2.setBounds(486, 149, 102, 22);
		w_doctor.add(label_2);

		fld_dPass = new JTextField();
		fld_dPass.setBounds(486, 181, 170, 33);
		w_doctor.add(fld_dPass);
		fld_dPass.setColumns(10);

		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dTcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = userRepository.addDoctor(fld_dTcno.getText(), fld_dPass.getText(),
								fld_dName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btn_addDoctor.setBackground(Color.GRAY);
		btn_addDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		btn_addDoctor.setBounds(486, 224, 170, 33);
		w_doctor.add(btn_addDoctor);

		JLabel label_3 = new JLabel("Kullanıcı ID");
		label_3.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		label_3.setBounds(486, 267, 102, 22);
		w_doctor.add(label_3);

		fld_doctorID = new JTextField();
		fld_doctorID.setBounds(486, 300, 170, 33);
		w_doctor.add(fld_doctorID);
		fld_doctorID.setColumns(10);

		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz.");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = userRepository.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delDoctor.setBackground(Color.GRAY);
		btn_delDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		btn_delDoctor.setBounds(486, 348, 170, 31);
		w_doctor.add(btn_delDoctor);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 10, 466, 390);
		w_doctor.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {
						boolean control = userRepository.uptadeDoctor(selectID, selectTcno, selectPass, selectName);
						if (control) {
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Polikinlikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 10, 265, 390);
		w_clinic.add(w_scrollClinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic;
				try {
					selectClinic = clinicRepository.getFetch(selID);
					UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
					updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					updateGUI.setVisible(true);
					updateGUI.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							try {
								updateClinicModel();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				

			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinicRepository.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		w_scrollClinic.setViewportView(table_clinic);

		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(285, 31, 170, 33);
		w_clinic.add(fld_clinicName);

		JLabel lblPolikinlikAd = new JLabel("Polikinlik Adı");
		lblPolikinlikAd.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		lblPolikinlikAd.setBounds(285, 10, 102, 22);
		w_clinic.add(lblPolikinlikAd);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinicRepository.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		btn_addClinic.setBackground(Color.GRAY);
		btn_addClinic.setBounds(285, 78, 170, 33);
		w_clinic.add(btn_addClinic);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(466, 10, 235, 390);
		w_clinic.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(285, 304, 170, 33);
		for(int i = 0; i < userRepository.getDoctorList().size(); i++) {
			select_doctor.addItem(new Item(userRepository.getDoctorList().get(i).getId(), userRepository.getDoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_clinic.add(select_doctor);
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow,0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = userRepository.addWorker(doctorItem.getKey(),selClinicID);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i =0; i < userRepository.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0] = userRepository.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = userRepository.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
								
							}
							table_worker.setModel(workerModel);
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen bir polikinlik seçiniz");
				}
				
			}
		});
		btn_addWorker.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		btn_addWorker.setBackground(Color.GRAY);
		btn_addWorker.setBounds(285, 347, 170, 33);
		w_clinic.add(btn_addWorker);
		
		JLabel lblPolikinlikAd_1 = new JLabel("Polikinlik Adı");
		lblPolikinlikAd_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		lblPolikinlikAd_1.setBounds(285, 151, 102, 22);
		w_clinic.add(lblPolikinlikAd_1);
		
		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow,0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i =0; i < userRepository.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = userRepository.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = userRepository.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				}else {
					Helper.showMsg("Lütfen bir polikinlik seçiniz");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		btn_workerSelect.setBackground(Color.GRAY);
		btn_workerSelect.setBounds(285, 183, 170, 33);
		w_clinic.add(btn_workerSelect);

	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < userRepository.getDoctorList().size(); i++) {
			doctorData[0] = userRepository.getDoctorList().get(i).getId();
			doctorData[1] = userRepository.getDoctorList().get(i).getName();
			doctorData[2] = userRepository.getDoctorList().get(i).getTcno();
			doctorData[3] = userRepository.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinicRepository.getList().size(); i++) {
			clinicData[0] = clinicRepository.getList().get(i).getId();
			clinicData[1] = clinicRepository.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}

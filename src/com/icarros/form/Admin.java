package com.icarros.form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.icarros.Correntista;
import com.icarros.db.CorrentistaDAO;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTable tabela;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		setTitle("Gerenciamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 86, 466, 355);
		contentPane.add(scrollPane);
		
		tabela = new JTable();
		scrollPane.setViewportView(tabela);
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//cria variavel para capturar o index do array
				int index = tabela.getSelectedRow();
			}
		});
		model = new DefaultTableModel();
		tabela.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ag", "conta", "nome", "email", "telefone", "saldo"
				}
			));
		
		JLabel lblNewLabel = new JLabel("Correntistas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(190, 35, 379, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exibir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CorrentistaDAO correntista = new CorrentistaDAO();
				ArrayList<Correntista> value = correntista.read(null);
				for(int i = 0; i < value.size(); i++) {	
					System.out.println(value.get(i));
					Correntista atual = value.get(i);
					Object[] row = {
							Integer.toString(atual.getAg()),
							Integer.toString(atual.getConta()),
							atual.getNome(),
							atual.getEmail(),
							atual.getTelefone(),
							Double.toString(atual.getSaldo())};
					System.out.println(row);
					model.addRow(row);
					System.out.println("add");
				}
			}
		});
		btnNewButton.setBounds(42, 109, 89, 23);
		contentPane.add(btnNewButton);
	}
}

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
import javax.swing.JOptionPane;
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
	CorrentistaDAO correntista = new CorrentistaDAO();

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
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ag", "conta", "nome", "email", "telefone", "saldo"
				});
		tabela.setModel(model);
		JLabel lblNewLabel = new JLabel("Correntistas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(190, 35, 379, 40);
		contentPane.add(lblNewLabel);
		
		//Exibindo os valores na tabela
		JButton btnNewButton = new JButton("Exibir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Correntista> value = correntista.read(null);
				int n = 0;
				for(int i = 0; i < value.size(); i++) {	
					System.out.println(value.get(i));
					Correntista atual = value.get(i);
					Object[] row = new Object[]{
							Integer.toString(atual.getAg()),
							Integer.toString(atual.getConta()),
							atual.getNome(),
							atual.getEmail(),
							atual.getTelefone(),
							Double.toString(atual.getSaldo())};
					model.addRow(row);
					n++;
				}
				if(n < 1){
					JOptionPane.showMessageDialog(null, "Nao existem registros!","Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(42, 109, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Deletar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tabela.getSelectedRow();
				int ag = Integer.parseInt((String) model.getValueAt(row, 0));
				int conta = Integer.parseInt((String) model.getValueAt(row, 1));
				correntista.delete(ag, conta);
				model.removeRow(row);				
			}
		});
		btnNewButton_1.setBounds(42, 151, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}

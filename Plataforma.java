import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings({ "unused", "serial" })
public class Plataforma extends JFrame {

	private JPanel contentPane;
	private static JTextField txtCod_Plataforma;
	private static JTextField txtPlataforma;
	private static JTable tblPlataforma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Plataforma frame = new Plataforma();
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
	
	
	private static void LimparRegistros() {
		
		txtPlataforma.setText("");
		txtCod_Plataforma.setText("");
		
	}
	
private static void PreencherTabela() {
		
		try {
			String jdbcurl = "jdbc:sqlserver://NÍCOLAS\\SQLEXPRESS:56348;user=sa;password=nicolas123;databasename=LOJA_JOGOS";
		    Connection con = null;
		    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		    con=DriverManager.getConnection(jdbcurl);
		    
		    Statement stmt = con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery("Select * from Plataforma");
		    
		    DefaultTableModel dtm = (DefaultTableModel) tblPlataforma.getModel();
		    
		    dtm.setNumRows(0);
		    while(rs.next()) {
		    	
		    	dtm.addRow(new Object[] {
		    	rs.getString("Plataforma"),
	    		rs.getInt("Cod_Plataforma")
	    		
	    		});
		    	
		    	}
		    
		    tblPlataforma.setModel(dtm);
		    
		    stmt.close();
		    con.close();
		
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	public Plataforma() {
		setTitle("Plataforma");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				PreencherTabela();
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Plataforma");
		lblNewLabel.setBounds(10, 11, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cod_Plataforma");
		lblNewLabel_1.setBounds(10, 38, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCod_Plataforma = new JTextField();
		txtCod_Plataforma.setBounds(120, 35, 99, 20);
		contentPane.add(txtCod_Plataforma);
		txtCod_Plataforma.setColumns(10);
		
		txtPlataforma = new JTextField();
		txtPlataforma.setBounds(120, 8, 182, 20);
		contentPane.add(txtPlataforma);
		txtPlataforma.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String jdbcurl = "jdbc:sqlserver://NÍCOLAS\\SQLEXPRESS:56348;user=sa;password=nicolas123;databasename=LOJA_JOGOS";
				    Connection con = null;
				    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				    con=DriverManager.getConnection(jdbcurl);
				    
				    Statement stmt = con.createStatement();
				    
				    if (txtPlataforma.getText().equals("") ||
				    	txtCod_Plataforma.getText().equals(""))
				    	JOptionPane.showMessageDialog(null, "Por favor, preencher todos os campos antes de cadastrar a plataforma!"); 
				    
				    else {
				    	stmt.executeUpdate("Insert into Plataforma (Plataforma,Cod_Plataforma) values("
					    		+ "'"+txtPlataforma.getText()+"',"
					    		+ "'"+txtCod_Plataforma.getText()+"')");
					    
					    JOptionPane.showMessageDialog(null, "Plataforma inserida com sucesso!");
					    
					    LimparRegistros();
					    PreencherTabela();
				    }
				    
				   stmt.close();
				   con.close();
				    
				   
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnCadastrar.setBounds(110, 95, 99, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String jdbcurl = "jdbc:sqlserver://NÍCOLAS\\SQLEXPRESS:56348;user=sa;password=nicolas123;databasename=LOJA_JOGOS";
				    Connection con = null;
				    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				    con=DriverManager.getConnection(jdbcurl);
				    
				    Statement stmt = con.createStatement();
				    
				    if(txtCod_Plataforma.getText().equals(""))
				    		JOptionPane.showMessageDialog(btnExcluir, "Por favor, preencha o campo de código");
				    else {
				    	stmt.executeUpdate("delete from Plataforma where Cod_Plataforma = '"+txtCod_Plataforma.getText()+"'");
				    	JOptionPane.showMessageDialog(btnExcluir, "Registro excluído com sucesso!");
				    	
				    	LimparRegistros();
				    	PreencherTabela();
				    	
				    }
				    
					}catch(SQLException eex) {
						eex.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnExcluir.setBounds(225, 95, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
try {
					
					String jdbcurl = "jdbc:sqlserver://NÍCOLAS\\SQLEXPRESS:56348;user=sa;password=nicolas123;databasename=LOJA_JOGOS";
				    Connection con = null;
				    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				    con=DriverManager.getConnection(jdbcurl);
				    
				    Statement stmt = con.createStatement();
				    
				    if (txtPlataforma.getText().equals("") ||
					    	txtCod_Plataforma.getText().equals(""))
					    	JOptionPane.showMessageDialog(null, "Por favor, preencher todos os campos antes de editar o registro!"); 
					    
					    else {
					   	stmt.executeUpdate("Update Plataforma set Plataforma = '"+txtPlataforma.getText()+"' where Cod_Plataforma = '"+txtCod_Plataforma.getText()+"'");
					    	JOptionPane.showMessageDialog(null, "Registro editado com successo!");
					    	
					    	LimparRegistros();
							PreencherTabela();
					    	} 
					
					}catch(SQLException ex) {
						ex.printStackTrace();
					} catch (ClassNotFoundException ee) {
						ee.printStackTrace();
						
					}
			}
		});
		btnEditar.setBounds(225, 61, 89, 23);
		contentPane.add(btnEditar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 304, 272);
		contentPane.add(scrollPane);
		
		tblPlataforma = new JTable();
		tblPlataforma.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Plataforma", "Cod Plataforma"
			}
		));
		scrollPane.setViewportView(tblPlataforma);
	}
}

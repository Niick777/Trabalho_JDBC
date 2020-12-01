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
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "unused", "serial" })
public class Jogo_Plataforma extends JFrame {

	private JPanel contentPane;
	private static JTextField txtJogo;
	private static JTextField txtPlataforma;
	private static JTable tblJogo_Plataforma;
	private static JTextField txtCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jogo_Plataforma frame = new Jogo_Plataforma();
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
		txtJogo.setText("");
		txtCodigo.setText("");
		
	}
	
private static void PreencherTabela() {
		
		try {
			String jdbcurl = "jdbc:sqlserver://NÍCOLAS\\SQLEXPRESS:56348;user=sa;password=nicolas123;databasename=LOJA_JOGOS";
		    Connection con = null;
		    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		    con=DriverManager.getConnection(jdbcurl);
		    
		    Statement stmt = con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery("Select * from Jogo_Plataforma");
		    
		    DefaultTableModel dtm = (DefaultTableModel) tblJogo_Plataforma.getModel();
		    
		    dtm.setNumRows(0);
		    while(rs.next()) {
		    	
		    	dtm.addRow(new Object[] {
		    	rs.getString("Jogo"),
	    		rs.getString("Plataforma"),
	    		rs.getInt("Codigo")
	    		
	    		});
		    	
		    	}
		    
		    tblJogo_Plataforma.setModel(dtm);
		    
		    stmt.close();
		    con.close();
		
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
	public Jogo_Plataforma() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				PreencherTabela();
				
			}
		});
		setTitle("Jogo-Plataforma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Jogo");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Plataforma");
		lblNewLabel_1.setBounds(10, 46, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		txtJogo = new JTextField();
		txtJogo.setBounds(84, 8, 307, 20);
		contentPane.add(txtJogo);
		txtJogo.setColumns(10);
		
		txtPlataforma = new JTextField();
		txtPlataforma.setBounds(84, 43, 181, 20);
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
				    	txtJogo.getText().equals("")||
				    	txtCodigo.getText().equals(""))
				    	JOptionPane.showMessageDialog(null, "Por favor, preencher todos os campos!"); 
				    
				    else {
				    	stmt.executeUpdate("Insert into Jogo_Plataforma (Jogo,Plataforma,Codigo) values("
				    			+ "'"+txtJogo.getText()+"',"
					    		+ "'"+txtPlataforma.getText()+"',"
					    		+ "'"+txtCodigo.getText()+"')");
					    
					    JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!");
					    
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
		btnCadastrar.setBounds(84, 110, 96, 23);
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
				    
				    if(txtCodigo.getText().equals(""))
				    		JOptionPane.showMessageDialog(btnExcluir, "Por favor, preencha o campo de código");
				    else {
				    	stmt.executeUpdate("delete from Jogo_Plataforma where Codigo = '"+txtCodigo.getText()+"'");
				    	JOptionPane.showMessageDialog(btnExcluir, "Registro excluído com sucesso!");
				    	
				    	LimparRegistros();
				    	PreencherTabela();
				    	
				    }
				    
				    stmt.close();
					con.close();
					
					}catch(SQLException eex) {
						eex.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
			
			}
		});
		btnExcluir.setBounds(272, 110, 89, 23);
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
			    
			    if (txtJogo.getText().equals("") ||
				    txtPlataforma.getText().equals("")||
				    txtCodigo.getText().equals(""))
				    	JOptionPane.showMessageDialog(null, "Por favor, preencher todos os campos antes de editar o registro!"); 
				    
				    else {
				   	stmt.executeUpdate("Update Jogo_Plataforma set Jogo = '"+txtJogo.getText()+"' where Codigo = '"+txtCodigo.getText()+"'");
				   	stmt.executeUpdate("Update Jogo_Plataforma set Plataforma = '"+txtPlataforma.getText()+"' where Codigo = '"+txtCodigo.getText()+"'");
				    	JOptionPane.showMessageDialog(null, "Registro editado com successo!");
				    	
				    	LimparRegistros();
						PreencherTabela();
						
				    	}
			    stmt.close();
				con.close();
				
				}catch(SQLException ex) {
					ex.printStackTrace();
				}catch (ClassNotFoundException ee) {
					ee.printStackTrace();
				}
		}});
		btnEditar.setBounds(272, 76, 89, 23);
		contentPane.add(btnEditar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 143, 478, 260);
		contentPane.add(scrollPane);
		
		tblJogo_Plataforma = new JTable();
		tblJogo_Plataforma.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Jogo", "Plataforma", "C\u00F3digo"
			}
		));
		tblJogo_Plataforma.getColumnModel().getColumn(0).setPreferredWidth(125);
		tblJogo_Plataforma.getColumnModel().getColumn(2).setPreferredWidth(25);
		scrollPane.setViewportView(tblJogo_Plataforma);
		
		JLabel lblNewLabel_2 = new JLabel("C\u00F3digo");
		lblNewLabel_2.setBounds(10, 80, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(84, 79, 96, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
	}
}

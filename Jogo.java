import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings({ "unused", "serial" })
public class Jogo extends JFrame {

	private JPanel contentPane;
	private static JTextField txtJogo;
	private static JTextField txtCod_jogo;
	private static JTextField txtQuant_estoque;
	private static JTextField txtPreco_unidade;
	private static JTextField txtQuant_vendida;
	private static JTable tblJogo;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jogo frame = new Jogo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	
	private static void LimparRegistros() {
		
		txtJogo.setText("");
		txtCod_jogo.setText("");
		txtQuant_estoque.setText("");
		txtPreco_unidade.setText("");
		txtQuant_vendida.setText("");		
	}
	
	private static void PreencherTabela() {
		
		try {
			String jdbcurl = "jdbc:sqlserver://NÍCOLAS\\SQLEXPRESS:56348;user=sa;password=nicolas123;databasename=LOJA_JOGOS";
		    Connection con = null;
		    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		    con=DriverManager.getConnection(jdbcurl);
		    
		    Statement stmt = con.createStatement();
		    
		    ResultSet rs = stmt.executeQuery("Select * from Jogo");
		    
		    DefaultTableModel dtm = (DefaultTableModel) tblJogo.getModel();
		    
		    dtm.setNumRows(0);
		    while(rs.next()) {
		    	
		    	dtm.addRow(new Object[] {
		    	rs.getString("Jogo"),
	    		rs.getInt("Cod_jogo"),
	    		rs.getInt("Quant_estoque"),
	    		rs.getDouble("Preço_unidade"),
	    		rs.getInt("Quant_vendida")
	    		
	    		});
		    	
		    	}
		    
		    tblJogo.setModel(dtm);
		    
		    stmt.close();
		    con.close();
		
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public Jogo() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				PreencherTabela();
			}
		});
		setTitle("Jogo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Jogo = new JLabel("Jogo");
		Jogo.setBounds(7, 26, 29, 14);
		contentPane.add(Jogo);
		
		txtJogo = new JTextField();
		txtJogo.setBounds(111, 23, 275, 20);
		contentPane.add(txtJogo);
		txtJogo.setColumns(10);
		
		txtCod_jogo = new JTextField();
		txtCod_jogo.setBounds(111, 51, 86, 20);
		contentPane.add(txtCod_jogo);
		txtCod_jogo.setColumns(10);
		
		JLabel Cod_jogo = new JLabel("Cod jogo");
		Cod_jogo.setBounds(7, 54, 63, 14);
		contentPane.add(Cod_jogo);
		
		JLabel Quant_estoque = new JLabel("Quant estoque");
		Quant_estoque.setBounds(7, 82, 94, 14);
		contentPane.add(Quant_estoque);
		
		txtQuant_estoque = new JTextField();
		txtQuant_estoque.setBounds(111, 79, 86, 20);
		contentPane.add(txtQuant_estoque);
		txtQuant_estoque.setColumns(10);
		
		JLabel Preço_unidade = new JLabel("Pre\u00E7o unidade");
		Preço_unidade.setBounds(7, 107, 94, 14);
		contentPane.add(Preço_unidade);
		
		JLabel Quant_vendida = new JLabel("Quant vendida");
		Quant_vendida.setBounds(7, 132, 94, 14);
		contentPane.add(Quant_vendida);
		
		txtPreco_unidade = new JTextField();
		txtPreco_unidade.setBounds(111, 104, 86, 20);
		contentPane.add(txtPreco_unidade);
		txtPreco_unidade.setColumns(10);
		
		txtQuant_vendida = new JTextField();
		txtQuant_vendida.setBounds(111, 129, 86, 20);
		contentPane.add(txtQuant_vendida);
		txtQuant_vendida.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String jdbcurl = "jdbc:sqlserver://NÍCOLAS\\SQLEXPRESS:56348;user=sa;password=nicolas123;databasename=LOJA_JOGOS";
				    Connection con = null;
				    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				    con=DriverManager.getConnection(jdbcurl);
				    
				    Statement stmt = con.createStatement();
				    
				    if (txtJogo.getText().equals("") ||
				    	txtCod_jogo.getText().equals("") ||
				    	txtQuant_estoque.getText().equals("") ||
				    	txtPreco_unidade.getText().equals("") ||
				    	txtQuant_vendida.getText().equals(""))
				    	JOptionPane.showMessageDialog(null, "Por favor, preencher todos os campos antes de cadastrar o produto!"); 
				    
				    else {
				    	stmt.executeUpdate("Insert into Jogo (Jogo,Cod_jogo,Quant_estoque,Preço_unidade,Quant_vendida) values("
					    		+ "'"+txtJogo.getText()+"',"
					    		+ "'"+txtCod_jogo.getText()+"',"
					    		+ "'"+txtQuant_estoque.getText()+"',"
					    		+ "'"+txtPreco_unidade.getText()+"',"
					    		+ "'"+txtQuant_vendida.getText()+"')");
					    
					    JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
					    
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
		btnCadastrar.setBounds(111, 160, 96, 23);
		contentPane.add(btnCadastrar);
		
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
					    	txtCod_jogo.getText().equals("") ||
					    	txtQuant_estoque.getText().equals("") ||
					    	txtPreco_unidade.getText().equals("") ||
					    	txtQuant_vendida.getText().equals(""))
					    	JOptionPane.showMessageDialog(null, "Por favor, preencher todos os campos antes de cadastrar o produto!"); 
					    
					    else {
					   	stmt.executeUpdate("Update Jogo set Jogo = '"+txtJogo.getText()+"' where Cod_jogo = '"+txtCod_jogo.getText()+"'");
					   	stmt.executeUpdate("Update Jogo set Quant_estoque = '"+txtQuant_estoque.getText()+"' where Cod_jogo = '"+txtCod_jogo.getText()+"'");
					   	stmt.executeUpdate("Update Jogo set Preço_unidade = '"+txtPreco_unidade.getText()+"' where Cod_jogo = '"+txtCod_jogo.getText()+"'");
					   	stmt.executeUpdate("Update Jogo set Quant_vendida = '"+txtQuant_vendida.getText()+"'where Cod_jogo = '"+txtCod_jogo.getText()+"'");
					 
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
		btnEditar.setBounds(480, 128, 96, 23);
		contentPane.add(btnEditar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 194, 746, 258);
		contentPane.add(scrollPane);
		
		tblJogo = new JTable();
		scrollPane.setViewportView(tblJogo);
		tblJogo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Jogo", "Cod Jogo", "Quant estoque", "Pre\u00E7o unidade", "Quant vendida"
			}
		));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				String jdbcurl = "jdbc:sqlserver://NÍCOLAS\\SQLEXPRESS:56348;user=sa;password=nicolas123;databasename=LOJA_JOGOS";
			    Connection con = null;
			    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			    con=DriverManager.getConnection(jdbcurl);
			    
			    Statement stmt = con.createStatement();
			    
			    if(txtCod_jogo.getText().equals(""))
			    		JOptionPane.showMessageDialog(btnExcluir, "Por favor, preencha o campo de código");
			    else {
			    	stmt.executeUpdate("delete from Jogo where Cod_jogo = '"+txtCod_jogo.getText()+"'");
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
		btnExcluir.setBounds(480, 160, 96, 23);
		contentPane.add(btnExcluir);
		tblJogo.getColumnModel().getColumn(0).setPreferredWidth(326);
		tblJogo.getColumnModel().getColumn(1).setPreferredWidth(55);
		tblJogo.getColumnModel().getColumn(2).setPreferredWidth(82);
		tblJogo.getColumnModel().getColumn(3).setPreferredWidth(78);
		tblJogo.getColumnModel().getColumn(4).setPreferredWidth(81);
	}
}

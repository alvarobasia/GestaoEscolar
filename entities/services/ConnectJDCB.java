package entities.services;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import entities.models.Address;
 
public class ConnectJDCB {
	private final static String url = "jdbc:sqlite:/home/alvaro/Área de Trabalho/Gestao_escolar/GestaoEscolar/Banco/banco.db";
	private static Connection conn = null;
	 
    public static void connect() {
        try {
            conn = DriverManager.getConnection(url);
            
            System.out.println("Conectado");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public static void desconect() {
    	try{
    		if(conn.isClosed() == false && conn != null) {
    			conn.close();
    		}
    		System.out.println("Desconectado");
    	}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public static void insertAdress(Address address) {
    	connect();
    	int insertRows = 0;
    	PreparedStatement ps = null;
    	String sql = "INSERT INTO Endereco(cidade, bairro, rua, numero, cep, complemento) VALUES(?,?,?,?,?,?)";
    	try {
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, address.getCity());
    		ps.setString(2, address.getDistrict());
    		ps.setString(3, address.getStreet());
    		ps.setInt(4, address.getNumber());
    		ps.setInt(5, address.getCep());
    		if(address.getComplement() != null)
    			ps.setString(6, address.getComplement());
    		insertRows = ps.executeUpdate();
    		System.out.println(insertRows);
    	}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			desconect();
		}
    }
    
    public static void creatNewTable(String string) {
    	connect();
    	try {
    		Statement statement = conn.createStatement();
    		statement.execute(string);
    		System.out.println("as");
    	}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			desconect();
		}
    }
    
    
    public static String generateAdressTable() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("CREATE TABLE IF NOT EXISTS `Endereco` ("+"\n");
    	sb.append("`cidade` VARCHAR(30)  NOT NULL," + "\n");
    	sb.append("`bairro` VARCHAR(20)  NOT NULL," + "\n");
    	sb.append("`rua` VARCHAR(20)   NOT NULL," + "\n");
    	sb.append("`numero` INT  NOT NULL," + "\n");
    	sb.append("`cep` INT(8)  NOT NULL," + "\n");
    	sb.append("`complemento` VARCHAR(5) DEFAULT NULL," + "\n");
    	sb.append("`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT" + "\n");
    	//sb.append("PRIMARY KEY (`id`)" + "\n");
    	sb.append(")");
    	return  sb.toString();
    }
    
    
}

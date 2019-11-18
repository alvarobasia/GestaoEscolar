package entities.services;

 
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entities.exeptions.infoBancoExeption;
import entities.models.Address;
import entities.models.Classmate;
import entities.models.Supplies;
import entities.models.Teacher;
import org.jetbrains.annotations.NotNull;

public class ConnectJDCB {
	private final static String url = "jdbc:sqlite:C:\\Users\\alvar\\IdeaProjects\\trabalhofinal\\GestaoEscolar\\src\\dataBase\\banco.db";
	private static Connection conn = null;

	public static Connection connect() throws infoBancoExeption {
		try {
			conn = DriverManager.getConnection(url);

			System.out.println("Conectado");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new infoBancoExeption("Erro ao salvar no banco de dados");
		}
		return null;
	}

	public static void desconect() throws infoBancoExeption {
		try {
			if (!conn.isClosed() && conn != null) {
				conn.close();
			}
			System.out.println("Desconectado");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new infoBancoExeption("Erro ao salvar no banco de dados");
		}
	}

	public static void insertAdress(@NotNull Address address) throws infoBancoExeption {
		connect();
		int insertRows = 0;
		String complement = null;
		if (address.getComplement() != null)
			complement = address.getComplement();
		PreparedStatement ps = null;
		String sql = "INSERT INTO Endereco(cidade, bairro, rua, numero, cep, complemento) VALUES(?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, address.getCity());
			ps.setString(2, address.getDistrict());
			ps.setString(3, address.getStreet());
			ps.setInt(4, address.getNumber());
			ps.setInt(5, address.getCep());
			ps.setString(6, complement);

			insertRows = ps.executeUpdate();
			System.out.println(insertRows);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new infoBancoExeption("Erro ao salvar no banco de dados");
		} finally {
			desconect();
		}
	}

	public static void insertClassmate(@NotNull Classmate classmate) throws infoBancoExeption {
		connect();
		int endereco = -1;
		String number = null;
		if (classmate.getAddress() != null) {
			String sql = "SELECT id, MAX(id) FROM Endereco";
			try (Statement stmt = conn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql)) {
				endereco = rs.getInt("id");
			} catch (SQLException e) {
				System.out.println(e);
			}

		}
		if (classmate.getTelNumber() != null)
			number = classmate.getTelNumber();
		//	String cpf = classmate.getCpf();
		//	String query = "SELECT cpf FROM Alunos WHERE cpf ==" + cpf;
//		try (Statement stmt  = conn.createStatement();
//			 ResultSet rs    = stmt.executeQuery(query)){
//			String c = rs.getString("cpf");
//			System.out.println(c);
//			if(c != null)
//				throw new infoBancoExeption("CPF já cadastrado");
//		}catch (SQLException e){
//			System.out.println(e);
//		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String data = formatter.format(classmate.getBirthDate());
		String entrada = formatter.format(classmate.getDataJoin());
		int insertRows = 0;
		PreparedStatement ps = null;
		String sql = "INSERT INTO Alunos(nome,matricula,nascimento,data_de_entrada," +
				"cpf,genero,curso,id_turma,id_endereco,id_notas,tel) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println(classmate.getID());
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, classmate.getName());
			ps.setString(2, classmate.getRegistration());
			ps.setString(3, data);
			ps.setString(4, entrada);
			ps.setString(5, classmate.getCpf());
			ps.setString(6, classmate.getGender().toString());
			ps.setString(7, classmate.getCourse().toString());
			ps.setInt(8, 8);
			ps.setInt(9, endereco);
			ps.setInt(10, 8);
			ps.setString(11, number);
			insertRows = ps.executeUpdate();
			System.out.println(insertRows);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new infoBancoExeption("Erro ao salvar no banco de dados");
		} finally {
			desconect();
		}
	}

	public static void insertSuplies(@NotNull Supplies supplies) throws infoBancoExeption {
		connect();
		int insertRows = 0;
		PreparedStatement ps = null;
		String sql = "INSERT INTO Materias(nome, codigo, professor_cpf, professor_nome, max_faltas, aprovacao) " +
				"VALUES(?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, supplies.getSupplieName());
			ps.setString(2, supplies.getSupplieID());
			ps.setString(3, supplies.getTeacher().getCpf());
			ps.setString(4, supplies.getTeacher().getName());
			ps.setInt(5, supplies.getMaxGap());
			ps.setFloat(6, supplies.getAprovedGrade());
			insertRows = ps.executeUpdate();
			System.out.println(insertRows);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("lll");
			throw new infoBancoExeption("Erro ao salvar no banco de dados");
		} finally {
			desconect();
		}
	}

	public static List<? super Supplies> getAllSupplies() throws infoBancoExeption {
		connect();
		Supplies supplies = null;
		List<? super Supplies> list = new ArrayList<>();
		String sql = "SELECT * FROM Materias";
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String name = rs.getString("nome");
				String codigo = rs.getString("codigo");
				String profCpf = rs.getString("professor_cpf");
				Integer maxgap = rs.getInt("max_faltas");
				float aproved = rs.getInt("aprovacao");
				supplies = new Supplies(name, codigo, null, aproved, maxgap);
				list.add(supplies);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	return list;

	}

   public static void creatNewTable(String string) throws infoBancoExeption {
    	connect();
    	try {
    		Statement statement = conn.createStatement();
    		statement.execute(string);
    	}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new infoBancoExeption("Erro ao salvar no banco de dados");
		}finally {
			desconect();
		}
    }
    
    
    @NotNull
    public static String generateAdressTable() {
		String sb = "CREATE TABLE IF NOT EXISTS `Endereco` (" + "\n" +
				"`cidade` VARCHAR(30)  NOT NULL," + "\n" +
				"`bairro` VARCHAR(20)  NOT NULL," + "\n" +
				"`rua` VARCHAR(20)   NOT NULL," + "\n" +
				"`numero` INT  NOT NULL," + "\n" +
				"`cep` INT(8)  NOT NULL," + "\n" +
				"`complemento` VARCHAR(5) DEFAULT NULL," + "\n" +
				"`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT" + "\n" +
				//sb.append("PRIMARY KEY (`id`)" + "\n");
				")";
		return sb;
    }
	@NotNull
	public static String generateClassmateTable() {
		String sb = "create table if not exists Alunos (" + "\n" +
				"nome varchar(30) not null," + "\n" +
				"matricula varchar(30) not null," + "\n" +
				"nascimento date not null," + "\n" +
				"data_de_entrada date not null," + "\n" +
				"cpf varchar(11) not null," + "\n" +
				"genero varchar(1) not null," + "\n" +
				"curso varchar(1) not null," + "\n" +
				"id_curso integer default null," + "\n" +
		       "id_turma integer default null," + "\n" +
				"id_endereco integer default null," + "\n" +
		       "id_notas integer default null," + "\n" +
				"tel varchar(20) default null," + "\n" +
		       "id integer not null primary key autoincrement," + "\n"+
				"foreign key (id_endereco) references Endereco(id));" ;
		return sb;
	}
    @NotNull
    public static String generateSuppliesTable() {
        String sb = "create table if not exists Materias (" + "\n" +
                "nome varchar(30) not null," + "\n" +
                "codigo varchar(30) not null primary key," + "\n" +
                "professor_cpf varchar(11) not null," + "\n" +
                "professor_nome varchar(50) not null," + "\n" +
                "max_faltas integer not null," + "\n" +
                "aprovacao integer not null);" + "\n" ;
        return sb;
    }


}

package entities.services;

 
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import entities.enums.Gender;
import entities.enums.Nomination;
import entities.enums.Situation;
import entities.enums.Turn;
import entities.exeptions.infoBancoExeption;
import entities.models.*;
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
				"cpf,genero,curso,id_curso,id_endereco,tel) VALUES(?,?,?,?,?,?,?,?,?,?)";
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
			ps.setInt(8,classmate.getCourse().getId());
			ps.setInt(9, endereco);
			ps.setString(10, number);
			insertRows = ps.executeUpdate();
			System.out.println(insertRows);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new infoBancoExeption("Erro ao salvar no banco de dados");
		} finally {
			desconect();
		}
	}
	public static int getLestIdClass() throws infoBancoExeption, SQLException {
		String result = null;
		int r = 5;
		connect();

		String sql = "SELECT COUNT (*) FROM Alunos ORDER BY matricula DESC " ;
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			 r = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e);
		}
		if(r == 0) {
			desconect();
			return 1;
		}
		sql = "SELECT * FROM Alunos ORDER BY matricula DESC " ;
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			result = rs.getString("matricula");
		} catch (SQLException e) {
			System.out.println(e);
		}
		desconect();
		System.out.println(r);
		String i = result.replace('.','#');
		System.out.println(i);
		String[] y = i.split("#");
		return Integer.parseInt(y[2])+ 1;
	}
	public static void insertTeacher(@NotNull Teacher teacher) throws infoBancoExeption{
	    connect();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data = formatter.format(teacher.getBirthDate());
        String entrada = formatter.format(teacher.getDataJoin());
        Float salary = null;
        String sql;
        if(teacher.getSalary() != null) {
            salary = teacher.getSalary();
            sql = "INSERT INTO Professores(nome, id, cpf, nascimento, entrada, genero, nomeacao, salario) " +
                    "VALUES(?,?,?,?,?,?,?,?)";
        }else {
			sql = "INSERT INTO Professores(nome, id, cpf, nascimento, entrada, genero, nomeacao) " +
					"VALUES(?,?,?,?,?,?,?)";
		}
	    int insertRows =0;
	    PreparedStatement ps = null;
        try {
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, teacher.getName());
	        ps.setInt(2, teacher.getTeacherID());
	        ps.setString(3, teacher.getCpf());
	        ps.setString(4, data );
	        ps.setString(5, entrada);
	        ps.setString(6, teacher.getGender().toString());
	        ps.setString(7, teacher.getNomination().toString());
	        if(salary != null)
	            ps.setFloat(8,  salary);
	        insertRows = ps.executeUpdate();
            System.out.println(insertRows);
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
	        desconect();
        }
    }

	public static int getCourseId() throws infoBancoExeption {
		String result = null;
		int r = 0;
		connect();

		String sql = "SELECT COUNT (*) FROM Cursos ORDER BY id DESC " ;
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			r = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("yyy"+ r);
		if(r == 0) {
			desconect();
			return 1;
		}
		sql = "SELECT id, MAX (id) FROM Cursos " ;
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			result = rs.getString("id");
		} catch (SQLException e) {
			System.out.println(e);
		}
		desconect();
		System.out.println(result+"ttt");
		System.out.println(r + "ooo");
		return Integer.parseInt(result)+1;
	}
    public static int getTeacherId() throws infoBancoExeption {
		String result = null;
		int r = 0;
		connect();

		String sql = "SELECT COUNT (*) FROM Professores ORDER BY id DESC " ;
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			r = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("yyy"+ r);
		if(r == 0) {
			desconect();
			return 1;
		}
		sql = "SELECT id, MAX (id) FROM Professores " ;
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			result = rs.getString("id");
		} catch (SQLException e) {
			System.out.println(e);
		}
		desconect();
		System.out.println(result+"ttt");
		System.out.println(r + "ooo");
		return Integer.parseInt(result)+1;
	}
	public static void insertSuplies(@NotNull Supplies supplies) throws infoBancoExeption {
		connect();
		int insertRows = 0;
		PreparedStatement ps = null;
		String sql = "INSERT INTO Materias(nome, codigo, id_prof, professor_nome, max_faltas, aprovacao) " +
				"VALUES(?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, supplies.getSupplieName());
			ps.setString(2, supplies.getSupplieID());
			if(supplies.getTeacher() !=  null) {
				ps.setInt(3, supplies.getTeacher().getTeacherID());
				ps.setString(4, supplies.getTeacher().getName());
			}else {
				ps.setString(3,null);
				ps.setString(4, null);
			}
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

	public static void insertCourse(Course course) throws infoBancoExeption {
		connect();
		String sql = "INSERT INTO Cursos(nome, duracao)" +
				"VALUES(?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getDuration());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new infoBancoExeption("Erro no banco de dados");
		}

	}
	public static void insertClassroom(Classroom classroom) throws infoBancoExeption {
		connect();
		PreparedStatement ps = null;
		String sql = "INSERT INTO Turma(nome, id, materia, turno) VALUES(?,?,?,?)";
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, classroom.getRoom());
			ps.setString(2,classroom.getID());
			ps.setString(3,classroom.getSupplies().getSupplieID());
			ps.setString(4,classroom.getTurn().toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconect();
		}
	}
	public static void ligaProfMat(String nome, int id, String codigo) throws infoBancoExeption {
		String k = "\'"+codigo+"\'";
		String sql = "UPDATE Materias SET id_prof = ?, professor_nome = ?" +
                "WHERE codigo ="+ k;
        PreparedStatement ps = null;
        connect();
		try {
		        ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, nome);
                ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
			desconect();
		}
	}

	public static void deleteGrade(Grades grades) throws infoBancoExeption {
		connect();
		String result = "\""+ grades.getMatricula()+"\"";
		String sql = "DELETE FROM Notas WHERE sala ="+ grades.getSala() + " AND matricula ="+ result;
		System.out.println(sql);
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			desconect();
		}
	}
	public static void deleteTeacher(Teacher teacher) throws infoBancoExeption {
		connect();
		String t = "UPDATE Materias SET  id_prof  = NULL, Materias.professor_nome = NULL " +
		"WHERE id_prof ="+ teacher.getTeacherID();
		try{
			PreparedStatement ps = conn.prepareStatement(t);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "DELETE FROM Professores WHERE id ="+ teacher.getTeacherID();
		System.out.println(sql);
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			desconect();
		}
	}


	public static void updateTeacher(Teacher teacher) throws infoBancoExeption {
		connect();
		String sql = "UPDATE Professores SET  nome = ?, cpf = ?, salario = ?" +
				"WHERE id ="+ teacher.getTeacherID();
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, teacher.getName());
			ps.setString(2,teacher.getCpf());
			ps.setFloat(3,teacher.getSalary());
			ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			desconect();
		}
	}
	public static void updateGrade(Grades grades) throws infoBancoExeption {
		connect();
		String result = "\""+ grades.getMatricula()+"\"";
		String sql = "UPDATE Notas SET  faltas = ?, nota = ?, situacao = ?" +
				"WHERE matricula ="+ result + "AND sala ="+ grades.getSala();
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, grades.getGap());
			ps.setFloat(2,grades.getGrade());
			ps.setString(3, grades.getSituation().toString());
			ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			desconect();
		}
	}
	public static void deleteClassmate(Classmate classmate) throws infoBancoExeption {
		connect();
		int end = -1;
		String result = "\""+ classmate.getRegistration()+"\"";
		if(classmate.getAddress() != null) {
			String s = "SELECT id_endereco FROM Alunos WHERE matricula ="+ result;
			try (Statement stmt = conn.createStatement();
				 ResultSet rs = stmt.executeQuery(s)){
				 end = rs.getInt("id_endereco");
			}catch (SQLException e){
				e.printStackTrace();
			}finally {
				desconect();
			}
		}
		if(end != -1){
			String t = "DELETE FROM Endereco WHERE id ="+ end;
			connect();
			try(PreparedStatement ps = conn.prepareStatement(t)){
				ps.executeUpdate();
			}catch (SQLException e){
				e.printStackTrace();
			}finally {
				desconect();
			}
		}
		connect();
		String d = "DELETE FROM Relacionamento WHERE id_aluno"+ result;
		try {
			PreparedStatement ps = conn.prepareStatement(d);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconect();
		}
		String sql = "DELETE FROM Alunos WHERE matricula ="+" "+result;
		System.out.println(sql);
		connect();
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			desconect();
		}
	}

	public static void updateClassmate(Classmate classmate, String name, String cpf) throws infoBancoExeption {
		connect();
		String result = "\""+ classmate.getRegistration()+"\"";
		String sql = "UPDATE Alunos SET  nome = ?, cpf = ?" +
				"WHERE matricula ="+ result;
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2,cpf);
			ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			desconect();
		}
	}
	public static void getAllAdress() throws infoBancoExeption {
		connect();
		Address address = null;
		List<Address> a = SaveAdresses.getInstance().getRegister();
		SaveAdresses saveAdresses = SaveAdresses.getInstance();
		String sql =  "SELECT * FROM Endereco";
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)){
			while (rs.next()){
				String city = rs.getString("cidade");
				String bairro = rs.getString("bairro");
				String street = rs.getString("rua");
				Integer numero = rs.getInt("numero");
				Integer cep = rs.getInt("cep");
				String comp = rs.getString("complemento");
				Integer id = rs.getInt("id");
				address = new Address(city, bairro,street,numero,cep,city,id);
				if(!a.contains(address))
					saveAdresses.add(address);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			desconect();
		}
	}
    public static void getAllGrades() throws infoBancoExeption {
        connect();
        Grades grades = null;
        List<Grades> a = SaveGrades.getInstance().getRegister();
        SaveGrades saveGrades = SaveGrades.getInstance();
        String sql =  "SELECT * FROM Notas";
        List<Supplies> sup = SaveSupplie.getInstance().getRegister();
        Supplies ss = null;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Float nota = rs.getFloat("nota");
                Integer faltas = rs.getInt("faltas");
                String mat = rs.getString("matricula");
                String sala = rs.getString("sala");
                String disci = rs.getString("disciplina");
                String situacao = rs.getString("situacao");
				String nome = rs.getString("nome");
                for(Supplies s : sup){
                    if(s.getSupplieID().equals(disci))
                        ss = s;
                }
                grades = new Grades(nome,nota,mat,ss,sala,faltas, Situation.valueOf(situacao));
                if(!a.contains(grades))
                    saveGrades.add(grades);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            desconect();
        }
    }
	public static void getAllClassmates() throws infoBancoExeption{
		connect();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Classmate classmate = null;
		List<Classmate> a = SaveClassemate.getInstance().getRegister();
		SaveClassemate saveClassemate = SaveClassemate.getInstance();
		List<Course> saveCourses = SaveCourses.getInstance().getRegister();
		Course course = null;
		List<Address> saveAdress = SaveAdresses.getInstance().getRegister();
		Address address = null;
		String sql =  "SELECT * FROM Alunos";
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)){
			while (rs.next()){
				String nome = rs.getString("nome");
				String mat = rs.getString("matricula");
				String nascimento = rs.getString("nascimento");
				String cpf = rs.getString("cpf");
				String genero = rs.getString("genero");
				Integer curso = rs.getInt("id_curso");
				Integer endereco = rs.getInt("id_endereco");
				String tel = rs.getString("tel");
				Integer id = rs.getInt("id");
				for (Course c : saveCourses){
					if(c.getId() == curso){
						course = c;
						break;
					}
				}
				if(endereco != -1){

					for (Address c : saveAdress){
						if(c.getId().equals(endereco)){
							address = c;
							break;
						}
					}
					classmate = new Classmate(nome,LocalDate.parse(nascimento,formatter),cpf,Gender.valueOf(genero),course,address,tel,mat);
				}else {
					classmate = new Classmate(nome,LocalDate.parse(nascimento,formatter),cpf,Gender.valueOf(genero),course,null,tel,mat);
				}
				if(!a.contains(classmate))
					saveClassemate.add(classmate);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			desconect();
		}
	}
	public static void getAllSupplies() throws infoBancoExeption {
		connect();
		Supplies supplies = null;
		List<Teacher> t1 = SaveTeachers.getInstance().getRegister();
		SaveSupplie saveSupplie = SaveSupplie.getInstance();
		List<Supplies> aux = saveSupplie.getRegister();
		String sql = "SELECT * FROM Materias";
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String name = rs.getString("nome");
				String codigo = rs.getString("codigo");
				String profCpf = rs.getString("id_prof");
                String nome = rs.getString("professor_nome");
				Integer maxgap = rs.getInt("max_faltas");
				float aproved = rs.getInt("aprovacao");
				if(profCpf == null) {
					supplies = new Supplies(name, codigo, null, aproved, maxgap);
                    if (!saveSupplie.getRegister().contains(supplies))
                        saveSupplie.add(supplies);
                    else {
						saveSupplie.getRegister().remove(supplies);
						saveSupplie.add(supplies);
					}
                }else {
					for(Teacher a : t1){
						System.out.println("tt"+ a.getTeacherID() + "rr"+profCpf);
						if(a.getTeacherID() == Integer.parseInt(profCpf)) {
							supplies = new Supplies(name, codigo, a, aproved, maxgap);
							if (!saveSupplie.getRegister().contains(supplies))
								saveSupplie.add(supplies);
							else {
								saveSupplie.getRegister().remove(supplies);
								saveSupplie.add(supplies);
							}
							break;
						}
					}
                }
			}
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
			desconect();
		}
	}

	public static void getAllTeachers() throws infoBancoExeption {
		connect();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Teacher teacher = null;
		SaveTeachers saveTeachers = SaveTeachers.getInstance();
		List<Teacher> aux = saveTeachers.getRegister();
		String sql = "SELECT * FROM Professores";
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String name = rs.getString("nome");
				Integer codigo = rs.getInt("id");
				String profCpf = rs.getString("cpf");
				String data = rs.getString("nascimento");
				String entrada = rs.getString("entrada");
				String genero = rs.getString("genero");
				String nomeacao = rs.getString("nomeacao");
				Float salario = rs.getFloat("salario");
				teacher = new Teacher(name, LocalDate.parse(data, formatter), salario, profCpf, Gender.valueOf(genero), Nomination.valueOf(nomeacao), codigo);
				System.out.println(!saveTeachers.getRegister().contains(teacher));
				saveTeachers.add(teacher);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}finally {
			desconect();
		}
	}
	public static void getAllCourses() throws infoBancoExeption, SQLException {
		connect();
		Course course = null;
	    SaveCourses saveCourses = SaveCourses.getInstance();
		String sql = "SELECT * FROM Cursos";
		try(Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql)){
			while (rs.next()){
				String nome = rs.getString("nome");
				Integer duracao = rs.getInt("duracao");
				Integer id = rs.getInt("id");
				course = new Course(nome, duracao, id);
				saveCourses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconect();
		}

	}
	public static void getAllClassrooms() throws infoBancoExeption {
		connect();
		Classroom classroom = null;
		SaveClassrooms saveClassrooms = SaveClassrooms.getInstance();
		SaveSupplie supplie = SaveSupplie.getInstance();
		Supplies supplies = null;
		String sql = "SELECT * FROM Turma";
		try(Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql)){
			while (rs.next()){
				String nome = rs.getString("nome");
				String id = rs.getString("id");
				String id_materia = rs.getString("materia");
				String turno = rs.getString("turno");
				for(Supplies supplies1 :supplie.getRegister()){
					if(supplies1.getSupplieID().equals(id_materia)){
                        System.out.println("GGGG");
						supplies = supplies1;
						break;
					}
				}
				classroom = new Classroom(nome,id, supplies, Turn.valueOf(turno));
				saveClassrooms.add(classroom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconect();
		}

	}
	public static void generateClass(String classroom, String matricula, Grades grades) throws infoBancoExeption {
		connect();
		PreparedStatement ps =null;
		String sql ="INSERT INTO Relacionamento(id_turma, id_aluno)" +
				"VALUES(?,?)";
			try{
				ps = conn.prepareStatement(sql);
				ps.setString(1, classroom);
				ps.setString(2,matricula);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				desconect();
			}
        connect();
        String s ="INSERT INTO Notas(nome,nota, faltas,matricula,sala, disciplina,situacao)" +
                "VALUES(?,?,?,?,?,?,?)";
        try{
            ps = conn.prepareStatement(s);
            ps.setString(1,grades.getName());
            ps.setFloat(2, grades.getGrade());
            ps.setInt(3,grades.getGap());
            ps.setString(4,matricula);
            ps.setString(5,classroom);
            ps.setString(6,grades.getDisciplina().getSupplieID());
            ps.setString(7, grades.getSituation().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconect();
        }
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
				"id_endereco integer default null," + "\n" +
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
                "id_prof integer default null," + "\n" +
                "professor_nome varchar(50) default null," + "\n" +
                "max_faltas integer not null," + "\n" +
                "aprovacao integer not null," + "\n" +
				"foreign key (id_prof) references Professor(id));" ;
        return sb;
    }

    public static String generateTeacherTable(){
		String sb = "create table if not exists Professores (" + "\n" +
				"nome varchar(30) not null," + "\n" +
				"id integer not null primary key," + "\n" +
				"cpf varchar(20) not null," + "\n" +
				"nascimento date not null," + "\n" +
                "entrada date not null," + "\n" +
				"genero varchar(11) not null," + "\n" +
				"nomeacao varchar(11) not null," + "\n" +
				"salario integer default null);" + "\n" ;
		return sb;
	}
	public static String generateCourseTable(){
		String sb = "create table if not exists Cursos (" + "\n" +
				"nome varchar(30) not null," + "\n" +
				"duracao integer not null," + "\n" +
				"id integer not null primary key autoincrement);";
		return sb;
	}
	public static String generateClassroomTable(){
		String sb = "create table if not exists Turma (" + "\n" +
				"nome varchar(30) not null," + "\n" +
				"id varchar(30) primary key not null," + "\n" +
				"materia varchar(30) not null," + "\n" +
				"turno varchar(30) not null," + "\n" +
				"foreign key(materia) references Materias(codigo));";
		return sb;
	}
	public static String generateGradeTable(){
		String sb = "create table if not exists Notas (" + "\n" +
				"nome varchar(30) default null," + "\n" +
				"nota integer default null," + "\n" +
				"faltas integer default null," + "\n" +
				"matricula varchar(30) default null," + "\n" +
				"sala varchar(30) default null," + "\n" +
                "disciplina varchar(30) default null," + "\n" +
				"situacao varchar(30) default null," + "\n" +
				"id integer not null primary key autoincrement," + "\n" +
                "foreign key(matricula) references Aluno(matricula)," + "\n" +
                "foreign key(disciplina) references Materia(codigo)," + "\n" +
                "foreign key(sala) references Turma(id));";
		return sb;
	}
	public static String generateRelashipTable(){
		String sb = "create table if not exists Relacionamento (" + "\n" +
				"id_turma varchar(30) not null," + "\n" +
				"id_aluno varchar(30) not null ," + "\n" +
				"id integer not null primary key autoincrement," + "\n" +
				"foreign key(id_turma) references Turma(id)," + "\n" +
				"foreign key(id_aluno) references Aluno(matricula));";
		return sb;
	}
}

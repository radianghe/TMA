package Database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import Clase.Training;

public class Db {

	public static Connection connection = null;
	public static Statement statement=null;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/useriincrys";
	static final String USER = "root";
	static final String PASS = "root";
	public File f;
	
	public void getConnected() throws ClassNotFoundException, SQLException {

		Class.forName(DRIVER);
		System.out.println("Connecting to a selected database...");
		try {
			connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);
			System.out.println("Connected database successfully !");
		} catch (SQLException sql) {
			sql.printStackTrace();
			System.out.println("Connection error !");
		}
	}
	
	public List<Training> getTraining() throws ClassNotFoundException {
        List<Training> listaTraining = new ArrayList<Training>();
        try {
       
        	statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id_curs,nume_curs,nume_provider,durata,perioada,tip from training");
            while (rs.next()) {
            	Training training = new Training();
            	training.setId(rs.getInt("id_curs"));
            	training.setNumeCurs(rs.getString("nume_curs"));
            	training.setNumeProvider(rs.getString("nume_provider"));
            	training.setDurata(rs.getInt("durata"));
            	training.setPerioada(rs.getString("perioada"));
              	training.setTip(rs.getString("tip"));
            	
                
              	listaTraining.add(training);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaTraining;
    }
	 public void exportToJSON(List<Training> ls) {
		 f=new File("D:\\intership java\\AplicatieInCrys\\WebContent\\traininguri.json");
		 ObjectMapper mapper = new ObjectMapper();
		 JsonGenerator g;
		 try {
		 g = mapper.getJsonFactory().createJsonGenerator(new FileWriter(f));
		 for (Training stud : ls)
		 {
		 mapper.writeValue(g, stud);
		 }
		 g.close();
		 }
		 catch (IOException e1) {
		 e1.printStackTrace();
		 }
		 }


}

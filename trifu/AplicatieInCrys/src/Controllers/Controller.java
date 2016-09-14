package Controllers;

import Database.Db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Clase.Training;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Db database = new Db();
		if (request.getParameter("LogIn") != null) {

			List<Training> listaTraining = null;

			try {
				database.getConnected();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				listaTraining = database.getTraining();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			//database.exportToJSON(listaTraining);

			Statement stm;
			try {
				stm = database.connection.createStatement();
				ResultSet a = stm.executeQuery(
						"select nume,prenume from useri where id_manager='5807C359-5B21-431A-9CCF-7B0D67124CB2'");
				while(a.next())
				{
					String updateTableSQL = "UPDATE useri SET statut = ? WHERE id_angajat = ?";
					PreparedStatement preparedStatement = database.connection.prepareStatement(updateTableSQL);
					preparedStatement.setString(1, "manager");
					preparedStatement.setString(2, "5807C359-5B21-431A-9CCF-7B0D67124CB2");
					preparedStatement .executeUpdate();
				}

			String username = request.getParameter("Username");
			String password = request.getParameter("Password");

			if (!username.equals("") && !password.equals("")) {
				try {

					database.statement = database.connection.createStatement();

					String sql = "SELECT username,password,statut FROM useri ";
					ResultSet rs = database.statement.executeQuery(sql);
					int contor = 0;
					String statut = null;
					int semafor = 0;
					try {

						while (rs.next() && semafor == 0) {

							String uss = rs.getString("username");
							String pass = rs.getString("password");

							if (uss.equals(username) && pass.equals(password)) {
								contor++;
								semafor++;
								statut = rs.getString("statut");

							} 
							
							

						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(semafor==0)
					{
					
					RequestDispatcher view = request.getRequestDispatcher("loginFail.html");
					view.forward(request, response);
					}
					else if (contor != 0 && statut.equals("user")) {
						RequestDispatcher view1 = request.getRequestDispatcher("userNormal.html");
						view1.forward(request, response);
					} else if (contor != 0 && statut.equals("teamleader")) {
						RequestDispatcher view2 = request.getRequestDispatcher("userManager.html");
						view2.forward(request, response);
					} else if (contor != 0 && statut.equals("manager")) {
						RequestDispatcher view3 = request.getRequestDispatcher("userTL.html");
						view3.forward(request, response);
					} else if (contor != 0 && statut.equals("director")) {
						RequestDispatcher view4 = request.getRequestDispatcher("userDirector.html");
						view4.forward(request, response);
					} else if (contor != 0 && statut.equals("hr")) {
						RequestDispatcher view5 = request.getRequestDispatcher("userHr.html");
						view5.forward(request, response);
					} else if (contor != 0 && statut.equals("admin")) {
						RequestDispatcher view6 = request.getRequestDispatcher("userAdmin.html");
						view6.forward(request, response);
					}
				} catch (SQLException s) {
					s.printStackTrace();
				}

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			
		}
		}else if (request.getParameter("Forgot") != null) {
			RequestDispatcher view5 = request.getRequestDispatcher("resetareParola.html");
			view5.forward(request, response);
		} else if (request.getParameter("acasa") != null) {
			RequestDispatcher view11 = request.getRequestDispatcher("/AplicatieInCrys/userNormal.html");
			view11.forward(request, response);
		} else if (request.getParameter("Training") != null) {
			RequestDispatcher view12 = request.getRequestDispatcher("www.facebook.com");
			view12.forward(request, response);
		} else if (request.getParameter("Cerere") != null) {
			RequestDispatcher view13 = request.getRequestDispatcher("/AplicatieInCrys/Cerere.html");
			view13.forward(request, response);
		} else if (request.getParameter("ContulMeu") != null) {
			RequestDispatcher view14 = request.getRequestDispatcher("www.facebook.com");
			view14.forward(request, response);
		} else if (request.getParameter("ViewTraining") != null) {
			List<Training> listaTraining;
			try {
				listaTraining = database.getTraining();
				request.setAttribute("listaTraining", listaTraining);
				RequestDispatcher view1 = request.getRequestDispatcher("/AplicatieInCrys/Traininguri.html");
				view1.forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

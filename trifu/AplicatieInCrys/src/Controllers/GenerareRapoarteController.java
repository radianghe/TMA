package Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.mysql.jdbc.PreparedStatement;

import Database.Db;

/**
 * Servlet implementation class GenerareRapoarteController
 */
@WebServlet("/GenerareRapoarteController")
public class GenerareRapoarteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerareRapoarteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Db databd=new Db();
		File f=new File("Raport.txt");
		try {
			databd.getConnected();
		} catch (ClassNotFoundException | SQLException e1) {
		
			e1.printStackTrace();
		}
		String option=request.getParameter("raport");
		
		switch(option){
			
		case "Utilizator":
		{
			String numeUser=request.getParameter("numeu");
			String prenumeUser=request.getParameter("prenumeu");
			String trimestru=request.getParameter("trimestru");
			int an=Integer.parseInt(request.getParameter("an"));
			String status=request.getParameter("status");
			String tip=request.getParameter("tip");
			
			try {
				java.sql.PreparedStatement stmt=databd.connection.prepareStatement("select * from traininguri where numeUser='?' and prenumeUser='?' and perioada='?' and tipcurs='?'");
				stmt.setString(0, numeUser);
				stmt.setString(1, prenumeUser);
				stmt.setString(2, trimestru);
				stmt.setInt(3, an);
				stmt.setString(4, status);
				stmt.setString(5, tip);
				
				ResultSet rs=stmt.executeQuery();
				 PDDocument doc = null;
			      PDPage page = null;

			       try{
			           doc = new PDDocument();
			           page = new PDPage();

			           doc.addPage(page);
			           PDFont font = PDType1Font.HELVETICA_BOLD;

			           PDPageContentStream content = new PDPageContentStream(doc, page);
			           content.beginText();
			           content.setFont( font, 12 );
				while(rs.next()){
					//#cleancode
					  String line=new String(rs.getString("id") + ' ' + rs.getString("numeTl") + ' ' + rs.getString("numeUser") + ' ' + rs.getString("prenumeUser") + ' ' +
							  rs.getString("tipTraining") + ' ' + rs.getString("numeCurs") + ' ' + rs.getString("numeProvider") + ' ' + rs.getString("moneda") + ' ' + rs.getFloat("cost") + ' ' + rs.getInt("an") + ' ' + 
							  rs.getInt("zile") + ' ' + rs.getString("perioada") + ' ' + rs.getString("tipcurs") + ' ' + rs.getString("desfasurare") + ' ' + rs.getString("suport") + ' ' + rs.getString("mail") + ' ' + rs.getString("status"));
					  content.drawString(line + System.lineSeparator());
					
				}

				           content.endText();
				           content.close();
				          doc.save("PDFWithText.pdf");
				          doc.close();
				   

				
			} catch (Exception e) {
				
				e.printStackTrace();
			
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			System.out.println("Operation done.");
		}
	}
}
}
}
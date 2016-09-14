package Controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Clase.CerereTraining;
import Clase.EmailUtility;
import Database.Db;

/**
 * Servlet implementation class CerereNouaController
 */
@WebServlet("/CerereNouaController")
public class CerereNouaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CerereNouaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Db databd=new Db();
		try {
			databd.getConnected();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String numeTl=request.getParameter("cerereNouaNumeTl");
		String numeUser=request.getParameter("cerereNouaNumeUser");
		String prenumeUser=request.getParameter("cerereNouaPrenumeUser");
		String tipTraining=request.getParameter("cerereNouaTipTraining");
		String numeCurs=request.getParameter("cerereNouaNumeCurs");
		String numeProvider=request.getParameter("cerereNouaNumeProvider");
		String monedaCurs=request.getParameter("cerereNouaNMoneda");
		Float costCurs=Float.parseFloat(request.getParameter("cerereNouaCostCurs"));
		int anCurs=Integer.parseInt(request.getParameter("cerereNouaAnCurs"));
		int nrZileCurs=Integer.parseInt(request.getParameter("cerereNouaZileCurs"));
		String perioadaCurs=request.getParameter("cerereNouaPerioadaCurs");
		String tipCurs=request.getParameter("cererNouaTipCurs");
		String desfasurareCurs=request.getParameter("cerereNouaDesfasurareCurs");
		String suportCurs=request.getParameter("cerereNouaSuport");
		String emailUser=request.getParameter("cerereNouaEmailIbm");
		
		CerereTraining cerereNoua=new CerereTraining(numeTl, numeUser, prenumeUser, tipTraining, numeCurs, numeProvider, monedaCurs, costCurs, anCurs, nrZileCurs, perioadaCurs, tipCurs, desfasurareCurs, suportCurs, emailUser);
		
		try{
			PreparedStatement prepstm=databd.connection.prepareStatement("insert into traininguri values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			prepstm.setInt(1, 0);
			prepstm.setString(2, cerereNoua.getNumeTl());
			prepstm.setString(3, cerereNoua.getNumeUser());
			prepstm.setString(4, cerereNoua.getPrenumeUser());
			prepstm.setString(5, cerereNoua.getTipTraining());
			prepstm.setString(6, cerereNoua.getNumeCurs());
			prepstm.setString(7, cerereNoua.getNumeProvider());
			prepstm.setString(8, cerereNoua.getMonedaCurs());
			prepstm.setFloat(9, cerereNoua.getCostCurs());
			prepstm.setInt(10, cerereNoua.getAnCurs());
			prepstm.setInt(11, cerereNoua.getNrZileCUrs());
			prepstm.setString(12, cerereNoua.getPerioada());
			prepstm.setString(13, cerereNoua.getTipCurs());
			prepstm.setString(14, cerereNoua.getDesfasurareCurs());
			prepstm.setString(15, cerereNoua.getSuportCurs());
			prepstm.setString(16, cerereNoua.getMailUser());
			prepstm.setString(17, "Pending");
			prepstm.executeUpdate();
			
			String selectSQL = "SELECT * FROM traininguri WHERE numeUser = ? and prenumeUser= ?";
			PreparedStatement preparedStatement = databd.connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cerereNoua.getNumeUser());
			preparedStatement.setString(2, cerereNoua.getPrenumeUser());
			ResultSet rs = preparedStatement.executeQuery();
			String curs="Ati aplicat la trainingul cu numele : "+cerereNoua.getNumeCurs()+" organizat de : "+cerereNoua.getNumeProvider()+" !";
			int contor=0;
			while (rs.next() &&contor==0) {
				contor++;
				
			}
			if(contor!=0)
			{ EmailUtility.sendEmail("smtp.gmail.com", "587", "test.incrys@gmail.com","incrys1234",cerereNoua.getMailUser(), "Cerere Training Nou",
		                    curs);
				RequestDispatcher view = request.getRequestDispatcher("cerereReusita.html");
				view.forward(request, response);
			}
			else
			{
				RequestDispatcher view = request.getRequestDispatcher("cerereEsuata.html");
				view.forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}

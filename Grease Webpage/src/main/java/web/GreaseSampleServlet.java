package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GreaseSampleDAO;
import model.GreaseSample;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/")
public class GreaseSampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GreaseSampleDAO greaseSampleDAO;
	
	public void init() {
		greaseSampleDAO = new GreaseSampleDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertSample(request, response);
				break;
			case "/delete":
				deleteSample(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateSample(request, response);
				break;
			case "/clear":
				clearTable(request, response);
				break;
			default:
				listSample(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listSample(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<GreaseSample> listSample = greaseSampleDAO.selectAllSamples();
		request.setAttribute("listSample", listSample);
		RequestDispatcher dispatcher = request.getRequestDispatcher("grease-sample-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("grease-sample-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int sampleId = Integer.parseInt(request.getParameter("sampleId"));
		GreaseSample existingSample = greaseSampleDAO.selectSample(sampleId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("grease-sample-form.jsp");
		request.setAttribute("sample", existingSample);
		dispatcher.forward(request, response);

	}

	private void insertSample(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int barcodeId = Integer.parseInt(request.getParameter("barcodeId"));
		double mass = Double.parseDouble(request.getParameter("mass"));
		int PPM = Integer.parseInt(request.getParameter("PPM"));
		String color = request.getParameter("color");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		
		GreaseSample newSample = new GreaseSample(barcodeId, mass, PPM, color, date, time);
		greaseSampleDAO.insertSample(newSample);
		response.sendRedirect("list");
	}

	private void updateSample(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int sampleId = Integer.parseInt(request.getParameter("sampleId"));
		int barcodeId = Integer.parseInt(request.getParameter("barcodeId"));
		double mass = Double.parseDouble(request.getParameter("mass"));
		int PPM = Integer.parseInt(request.getParameter("PPM"));
		String color = request.getParameter("color");
		String date = request.getParameter("date");
		String time = request.getParameter("time");

		

		GreaseSample book = new GreaseSample(sampleId, barcodeId, mass, PPM, color, date, time);
		greaseSampleDAO.updateSample(book);
		response.sendRedirect("list");
	}

	private void deleteSample(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int sampleId = Integer.parseInt(request.getParameter("sampleId"));
		greaseSampleDAO.deleteSample(sampleId);
		response.sendRedirect("list");

	}
	private void clearTable(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		greaseSampleDAO.clearTable();
		response.sendRedirect("list");

	}

}
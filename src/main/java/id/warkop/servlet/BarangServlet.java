package id.warkop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import id.warkop.model.barang.Barang;
import id.warkop.model.barang.BarangCtrl;
import id.warkop.model.barang.minuman.MinumanCtrl;

@SuppressWarnings("serial")
public class BarangServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		List<Barang> daftarBarang = new BarangCtrl().daftar(0, 20);
		req.setAttribute("daftarBarang", daftarBarang);
		// Panggil halamanlogin.jsp
		resp.setContentType("text/html");
		RequestDispatcher jsp = req
				.getRequestDispatcher(KonstantaServlet.JSP_FOLDER+"barang.jsp");
		jsp.forward(req, resp);
		
	}
	  // Process the http POST of the form
	  @Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws IOException {

	    String nama = req.getParameter("namaBarang");
	    try {
			new MinumanCtrl().baru(nama, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    resp.sendRedirect("/barang");
	  }

}

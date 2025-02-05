package code.servletdemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestParamServlet
 */
@WebServlet("/TestParamServlet")
public class TestParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestParamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		ServletContext context = getServletContext();
		
		String maxCartSize = context.getInitParameter("max-shopping-cart-size");
		String maxCartPrice = context.getInitParameter("max-shopping-cart-price");
		
		
		out.println("<html><body>");
		out.println("<h2>Hello World <h2>");
		out.println("<hr>");
		
		out.println("Max Shopping Cart Size :" + maxCartSize);
		out.println("<br/>");
		out.println("Max Shopping Cart Price :" + maxCartPrice);
		
		out.println("<br/><br/>");
		out.println("Time on the server is : " + new java.util.Date());
		out.println("</html></body>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

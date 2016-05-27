package stepladder;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StepladderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("<body><h1>Hello, ");
		
		resp.getWriter().println(req.getUserPrincipal().getName());
		resp.getWriter().println("<h1><body");
		resp.addHeader("StepLadder", "");
		resp.sendRedirect("other.html");
	}
}

package stepladder;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StepladderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, ");
		
		resp.getWriter().println(req.getUserPrincipal().getName());
		
		resp.addHeader("StepLadder", "");
	}
}

package stepladder;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StepladderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello World ");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		DataUser d = new DataUser("sdusowitz@gmail.com", "shira!", "Dusowitz", "Shira");

		try {
			pm.makePersistent(d);
		} finally {
			pm.close();
		}
	}
}

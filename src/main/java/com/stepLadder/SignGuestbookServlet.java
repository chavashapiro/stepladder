
//[START all]
package com.stepLadder;



import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

/**
 * Form Handling Servlet Most of the action for this sample is in
 * webapp/guestbook.jsp, which displays the {@link Greeting}'s. This servlet has
 * one method {@link #doPost(<#HttpServletRequest req#>, <#HttpServletResponse
 * resp#>)} which takes the form data and saves it.
 */
public class SignGuestbookServlet extends HttpServlet {

	// Process the http POST of the form
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Greeting greeting;
		String groupID = req.getParameter("guestbookName");
		String bookmarkURL = req.getParameter("bookmarkURL");
		greeting = new Greeting(groupID, bookmarkURL);
		/*			 
		*/

		// Use Objectify to save the greeting and now() is used to make the call
		// synchronously as we
		// will immediately get a new page using redirect and we want the data
		// to be present.
		ObjectifyService.ofy().save().entity(greeting).now();

		resp.sendRedirect("/bookmark.jsp?guestbookName=" + groupID);
		
	}
}
// 
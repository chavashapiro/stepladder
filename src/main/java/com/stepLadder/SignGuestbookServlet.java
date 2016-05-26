//[START all]
package com.stepLadder;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

/**
 * Form Handling Servlet Most of the action for this sample is in
 * webapp/guestbook.jsp, which displays the {@link Bookmark}'s. This servlet has
 * one method {@link #doPost(<#HttpServletRequest req#>, <#HttpServletResponse
 * resp#>)} which takes the form data and saves it.
 */
public class SignGuestbookServlet extends HttpServlet {

	// Process the http POST of the form
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Bookmark bookmark;
		String groupID = req.getParameter("guestbookName");
		String bookmarkURL = req.getParameter("bookmarkURL");
		String bookmarkTitle = req.getParameter("bookmarkTitle");
		bookmark = new Bookmark(groupID, bookmarkTitle, bookmarkURL);
		/*			 
		*/

		// Use Objectify to save the bookmark and now() is used to make the call
		// synchronously as we
		// will immediately get a new page using redirect and we want the data
		// to be present.
		ObjectifyService.ofy().save().entity(bookmark).now();

		resp.sendRedirect("/bookmark.jsp?guestbookName=" + groupID);

	}
}
//
//[START all]
package com.stepLadder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Iterator;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Key;

/**
 * Form Handling Servlet Most of the action for this sample is in
 * webapp/guestbook.jsp, which displays the {@link Bookmark}'s. This servlet has
 * one method {@link #doPost(<#HttpServletRequest req#>, <#HttpServletResponse
 * resp#>)} which takes the form data and saves it.
 */
public class AddDeleteBookmarkServlet extends HttpServlet {

	// Process the http POST of the form
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Bookmark bookmark;
		String bookmarkURL = req.getParameter("bookmarkURL");
		String groupID = req.getParameter("guestbookName");
		String bookmarkTitle = req.getParameter("bookmarkTitle");
		String val = req.getParameter("button");
		String message;
		if (val.equals("Add Bookmark")) {

			try {
				bookmark = new Bookmark(groupID, bookmarkTitle, bookmarkURL);
				ObjectifyService.ofy().save().entity(bookmark).now();
			} catch (InvalidURLException e) {
				message = "INVALID URL";
				redirectToPreviousPageWithError(req, resp, message);
				// resp.sendRedirect("bookmark.jsp?message=" +
				// URLEncoder.encode("INVALID URL", "UTF-8"));
				return;
			}
			/*			 
			*/

			// Use Objectify to save the bookmark and now() is used to make the
			// call
			// synchronously as we
			// will immediately get a new page using redirect and we want the
			// data
			// to be present.

		} else if (val.equals("Delete Bookmark")) {

			Key<Group> group = Key.create(Group.class, groupID);
			List<Bookmark> bookmarks = ObjectifyService.ofy().load().type(Bookmark.class).ancestor(group).list();
			Iterator<Bookmark> iter = bookmarks.iterator();
			boolean found = false;
			while (iter.hasNext()) {
				Bookmark b = iter.next();
				if (b.bookmarkURL.contains(req.getParameter("bookmarkURL"))) {
					ObjectifyService.ofy().delete().entity(b).now();
					found = true;
				}
			}
			if (found == false) {
				message = "BOOKMARK NOT FOUND";
				this.redirectToPreviousPageWithError(req, resp, message);
				/*
				 * resp.sendRedirect("bookmark.jsp?message=" +
				 * URLEncoder.encode("BOOKMARK NOT FOUND", "UTF-8"));
				 */
				return;
			}
		}
		req.getSession().removeAttribute("Message");
		resp.sendRedirect("/bookmark.jsp?guestbookName=" + groupID);
	}

	private void redirectToPreviousPageWithError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws IOException, UnsupportedEncodingException, ServletException {

		// redirect to previous page and display message
		String referer = req.getHeader("Referer");
		// redirect to prev page
		req.getSession().setAttribute("Message", message);
		resp.sendRedirect(referer);

	}
}
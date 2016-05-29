package com.stepLadder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

public class CreateGroupServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		GroupEntity group;
		String groupPassword = req.getParameter("groupPassword");
		String groupID = req.getParameter("guestbookName");
		String act = req.getParameter("act");
		List<GroupEntity> groups = ObjectifyService.ofy().load()
				.type(GroupEntity.class).list();
		String message;
		if (act.equals("Create New Group")) {
			// create button was pressed
			if (checkIfGroupExists(groupID, groups)) {
				message = "group already exists";
				redirectToPreviousPageWithError(req, resp, message);

			} else {
				// create new group
				group = new GroupEntity(groupID, groupPassword);
				ObjectifyService.ofy().save().entity(group).now();
				resp.sendRedirect("/bookmark.jsp?guestbookName=" + groupID);

			}

		}
		if (act.equals("Switch Group")) {
			// switch button was pressed
			if (checkGroupPassword(groupID, groupPassword, groups)) {
				resp.sendRedirect("/bookmark.jsp?guestbookName=" + groupID);

			} else {
				message = "invalid group and password";
				redirectToPreviousPageWithError(req, resp, message);
			}

		}

	}

	private void redirectToPreviousPageWithError(HttpServletRequest req,
			HttpServletResponse resp, String message) throws IOException,
			UnsupportedEncodingException {

		// redirect to previous page and display message
		String referer = req.getHeader("Referer");
		resp.sendRedirect(referer + URLEncoder.encode(message, "UTF-8"));
	}

	private boolean checkIfGroupExists(String groupID, List<GroupEntity> groups) {
		// check if the group exists

		for (GroupEntity g : groups) {
			if (groupID.equals(g.name)) {
				// the group exits
				return true;
			}
		}
		// no group
		return false;
	}

	private boolean checkGroupPassword(String groupID, String password,
			List<GroupEntity> groups) {
		// check if the group exists

		for (GroupEntity g : groups) {
			if (groupID.equals(g.name)) {
				// the group exits
				// return password.equals(g.password);
				return g.samePassword(password);

			}
		}
		// no group
		return false;
	}

}

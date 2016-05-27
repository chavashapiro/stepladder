package com.stepLadder;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class CreateGroupServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		boolean correct = false;
		String groupPassword = req.getParameter("groupPassword");
		String groupID = req.getParameter("guestbookName");
		
		
		// check if the password

		List<Group> groups = ObjectifyService.ofy().load()
				.type(Group.class).list();
		for(Group g: groups){
			if(groupID.equals(g)){
				//must check the password
				correct = true;
			}
		}
		

		// ObjectifyService.ofy().load().type(Bookmark.class);
		if (correct) {
			resp.sendRedirect("/bookmark.jsp?guestbookName=" + groupID);
		} else {
			//also happens if group id null
			resp.sendRedirect("/bookmark.jsp");
		}

	}
}

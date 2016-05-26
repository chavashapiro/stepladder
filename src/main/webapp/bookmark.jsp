<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%-- //[START imports]--%>

<%@ page import="com.stepLadder.Greeting"%>
<%@ page import="com.stepLadder.Guestbook"%>
<%@ page import="com.googlecode.objectify.Key"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>
<%-- //[END imports]--%>

<%@ page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<title>View BookMarks</title>





</head>

<body>

	<%
		String guestbookName = request.getParameter("guestbookName");
		if (guestbookName == null) {
			guestbookName = "My Home Group";
		}
		pageContext.setAttribute("guestbookName", guestbookName);
		//Get the email address from the html and then use it to look up which groups 
		//the user is part of - they should only be abble to accces those groups
	%>

	<%-- //[START datastore]--%>
	<%
		// Create the correct Ancestor key
		Key<Guestbook> theBook = Key.create(Guestbook.class, guestbookName);

		// Get list of all bookmarks for current group
		List<Greeting> greetings = ObjectifyService.ofy().load().type(Greeting.class) // only bookmarks
				.ancestor(theBook).order("-date")
				//.limit(15)  //we dont want limmit            
				.list();

		if (greetings.isEmpty()) {
	%>
	<p>Group ${fn:escapeXml(guestbookName)} has no bookmarks.</p>
	<%
		} else {
	%>
	<p>List of bookmarks for group ${fn:escapeXml(guestbookName)}.</p>
	<%
		// Look at all of our greetings
			for (Greeting greeting : greetings) {
				pageContext.setAttribute("greeting_content", greeting.bookmarkURL);
	%>
	<!-- turn this into url link and maybe add title of bookmark so it will display the title 
you need to add title to greetings class and then to singservlet has to getParameter(title) and the form in the jsp needs a title -->
	<p>
		<b>${fn:escapeXml(greeting_user)}</b>
	</p>
	<blockquote>${fn:escapeXml(greeting_content)}</blockquote>
	<%
		}
		}
	%>

	<form action="/sign" method="post">
		<div>
			<textarea name="bookmarkURL" rows="3" cols="60"></textarea>
		</div>
		<div>
			<input type="submit" value="Add bookmark" />
		</div>
		<input type="hidden" name="guestbookName"
			value="${fn:escapeXml(guestbookName)}" />
	</form>

	<%-- //[END datastore]--%>
	<form action="/bookmark.jsp" method="get">
		<div>
			<input type="text" name="guestbookName"
				value="${fn:escapeXml(guestbookName)}" />
		</div>
		<div>
			<input type="submit" value="Switch Group" />
		</div>
	</form>

</body>
</html>
<%-- //[END all]--%>

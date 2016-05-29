//
package com.stepLadder;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Bookmark {
	@Parent
	Key<Group> aGroup;
	@Id
	public Long id;

	public String bookmarkTitle;
	public String bookmarkURL;
	public String faviconURL;
	@Index
	public Date date;


	public Bookmark(String groupID, String bookmarkTitle,
			String bookmarkURL) {

		aGroup = Key.create(Group.class, groupID); // Creating the Ancestor
														// key
		this.bookmarkTitle = bookmarkTitle;
		this.bookmarkURL = "http://" + bookmarkURL;
		this.faviconURL = "http://www.google.com/s2/favicons?domain_url=" + bookmarkURL;
	}

	public Bookmark() {

	}
	
	

}
//

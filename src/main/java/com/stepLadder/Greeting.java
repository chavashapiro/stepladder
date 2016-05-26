//
package com.stepLadder;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Greeting {
	@Parent
	Key<Guestbook> aGroup;
	@Id
	public Long id;

	public String bookmarkTitle;

	public String bookmarkURL;
	@Index
	public Date date;

	/**
	 * 
	 * 
	 * /** A convenience constructor
	 **/
	public Greeting(String groupID, String bookmarkTitle, String bookmarkURL) {

		aGroup = Key.create(Guestbook.class, groupID); // Creating the Ancestor
														// key

		this.bookmarkTitle = bookmarkTitle;
		this.bookmarkURL = "http://" + bookmarkURL;
	}

	public Greeting() {

	}

}
//

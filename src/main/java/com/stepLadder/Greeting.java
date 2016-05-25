
//
package com.stepLadder;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.lang.String;
import java.util.Date;

@Entity
public class Greeting {
	@Parent
	Key<Guestbook> aGroup;
	@Id
	public Long id;

	public String bookmarkURL;
	@Index
	public Date date;

	/**
	 * 
	 * 
	 * /** A convenience constructor
	 **/
	public Greeting(String groupID, String bookmarkURL) {

		aGroup = Key.create(Guestbook.class, groupID); // Creating the Ancestor
														// key

		this.bookmarkURL = bookmarkURL;
	}
	
	public Greeting(){
		
	}

}
//

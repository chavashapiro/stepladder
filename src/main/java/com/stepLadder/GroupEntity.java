//
package com.stepLadder;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class GroupEntity {
	@Parent
	Key<Group> aGroup;
	@Id
	public Long id;

	
	public String password;
	public String name;
	


	public GroupEntity(String groupID, String groupPassword) {

		aGroup = Key.create(Group.class, groupID); // Creating the Ancestor
														// key
		this.password = groupPassword;
		this.name = groupID;
	}

	public GroupEntity() {

	}
	
	public boolean samePassword(String code){
		return this.password.equals(code);
	}

}
//

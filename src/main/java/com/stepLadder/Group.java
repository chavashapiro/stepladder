package com.stepLadder;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Group {
	@Id
	public String group;

	private String password;

	/*public Group(String groupName, String password) {
		this.group = groupName;
		this.password = password;
	}*/

	public String getGroupName() {
		return group;
	}
}
// [END all]

package com.stepLadder;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Group {
	@Id
	public String group;

	public String getGroupName() {
		return group;
	}
}
// [END all]

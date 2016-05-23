package stepladder;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DataUser {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String email;

	@Persistent
	private String password;

	@Persistent
	private String lastName;

	@Persistent
	private String firstName;

	@Persistent
	private List<Integer> groups;

	public DataUser(String email, String password, String lastName, String firstName) {
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Key getKey() {
		return key;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public List<Integer> getGroups() {
		return groups;
	}
}
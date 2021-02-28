package in.nareshit.aashish.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "users_tab")
public class User {
	
	@Id
	@GeneratedValue(generator = "user_seq_gen")
	@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq")
	@Column(name = "uid")
	private Integer id;
	
	@Column(name = "uname")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "pwd")
	private String pwd;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles_tab", joinColumns = @JoinColumn(name = "uid"))
	@Column(name = "roles")
	private List<String> roles;
	
	/**
	 * To activate or in-activate the user
	 */
	@Column(name = "uactive")
	private boolean active;

}

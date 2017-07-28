package sdu.sc.personal.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_admin")
public class UserAdmin {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String username;

    private String password;
    
    @OneToMany
    private Set<Comment> comments;

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }


    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

}

package sdu.sc.personal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_person_info")
public class Person {

    @Id
    @GeneratedValue
    private long personid;

    @OneToOne
    @JoinColumn(name="id")
    private UserAdmin user;

    private String name;

    private int age;

    //1表示男性，0表示女性
    private String sex;

    private String number;

    private String email;

    private String hobby;

    private String url;

    public long getPersonid() {
	return personid;
    }

    public void setPersonid(long personid) {
	this.personid = personid;
    }

    public UserAdmin getUser() {
	return user;
    }

    public void setUser(UserAdmin user) {
	this.user = user;
    }

    @Column(name="name")
    @NotNull
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Column(name="age",length=3)
    @NotNull
    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    @Column(name="sex",length=1)
    @NotNull
    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    @Column(name="number",length=12)
    @NotNull
    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    @Column
    @NotNull
    public String getEmail() {
	return email;
    }


    public void setEmail(String email) {
	this.email = email;
    }

    @Column
    @NotNull
    public String getHobby() {
	return hobby;
    }

    public void setHobby(String hobby) {
	this.hobby = hobby;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

}

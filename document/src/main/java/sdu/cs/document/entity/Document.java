package sdu.cs.document.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_doc")
public class Document {
    
    @Id
    @GeneratedValue
    private int docid;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    private String context;

    @NotNull
    @Column
    private String author;

    @NotNull
    @Column
    private String pubtab;

    @NotNull
    @Column
    private Timestamp pubtime;

    @NotNull
    @Column
    private int privillage;

    public int getDocid() {
	return docid;
    }

    public void setDocid(int docid) {
	this.docid = docid;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getContext() {
	return context;
    }

    public void setContext(String context) {
	this.context = context;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getPubtab() {
	return pubtab;
    }

    public void setPubtab(String pubtab) {
	this.pubtab = pubtab;
    }

    public Timestamp getPubtime() {
	return pubtime;
    }

    public void setPubtime(Timestamp pubtime) {
	this.pubtime = pubtime;
    }

    public int getPrivillage() {
	return privillage;
    }

    public void setPrivillage(int privillage) {
	this.privillage = privillage;
    }
    
}

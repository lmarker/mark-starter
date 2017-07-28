package sdu.sc.personal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_comment")
public class Comment {

    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    @JoinColumn(name="adminid")
    @NotNull
    private UserAdmin cadmin;
    
    @NotNull
    private String comment;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserAdmin getCadmin() {
        return cadmin;
    }

    public void setCadmin(UserAdmin cadmin) {
        this.cadmin = cadmin;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}

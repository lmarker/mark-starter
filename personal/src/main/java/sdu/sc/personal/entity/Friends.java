package sdu.sc.personal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_friend")
public class Friends {

    @Id
    @GeneratedValue
    private long id;
    
    @Column
    private long foid;
    
    @Column
    private long frid;
    
    @Column
    private short frelate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getFrelate() {
        return frelate;
    }

    public void setFrelate(short frelate) {
        this.frelate = frelate;
    }

    public long getFoid() {
        return foid;
    }

    public void setFoid(long foid) {
        this.foid = foid;
    }

    public long getFrid() {
        return frid;
    }

    public void setFrid(long frid) {
        this.frid = frid;
    }
    
}

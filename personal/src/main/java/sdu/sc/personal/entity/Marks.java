package sdu.sc.personal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_person_marks")
public class Marks {

    @Id
    @GeneratedValue
    private long coin_id;
    
    @OneToOne
    @JoinColumn(name="admin_id")
    private UserAdmin useradmin;
    
    private long amount;

    public long getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(long coin_id) {
        this.coin_id = coin_id;
    }

    public UserAdmin getUseradmin() {
        return useradmin;
    }

    public void setUseradmin(UserAdmin useradmin) {
        this.useradmin = useradmin;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
    
}

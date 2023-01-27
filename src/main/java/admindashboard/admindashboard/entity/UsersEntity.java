package admindashboard.admindashboard.entity;

import admindashboard.admindashboard.entity.AutoID.AutoID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class UsersEntity extends AutoID {
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "create_date", nullable = false)
    private Date create_date;

    @Column(name = "update_date", nullable = false)
    private Date update_date;

    @Column(name = "picture", nullable = true)
    private String picture ;
}

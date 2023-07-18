package BackEnd.BackEnd.entity;

import BackEnd.BackEnd.entity.AutoID.AutoID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class UsersEntity extends AutoID {
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "create_date", nullable = false)
    private Date create_date;

    @Column(name = "update_date", nullable = false)
    private Date update_date;

    @Column(name = "picture", nullable = true)
    private String picture ;
}

package admindashboard.admindashboard.entity;

import admindashboard.admindashboard.entity.AutoID.AutoID;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AdminEntity extends AutoID {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}

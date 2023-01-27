package admindashboard.admindashboard.entity.AutoID;



import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Data
@MappedSuperclass
@EnableAutoConfiguration
public abstract class AutoID {
  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @Column(nullable = false, updatable = false, length = 36, unique = true)
  private String id;
}

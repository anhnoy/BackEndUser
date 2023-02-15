package admindashboard.admindashboard.repository;

import admindashboard.admindashboard.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity,String> {

    Optional<UsersEntity>findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = """
            select a.username,b.email
            from users_entity as a,
            admin_entity as b
            where a.id = b.user_id

            """,nativeQuery = true)
    List<Map <String,Object>>test();
}

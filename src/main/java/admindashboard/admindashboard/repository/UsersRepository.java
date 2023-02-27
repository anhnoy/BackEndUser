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
            select * from users_entity

            """,nativeQuery = true)
    List<Map <String,Object>>test();
}

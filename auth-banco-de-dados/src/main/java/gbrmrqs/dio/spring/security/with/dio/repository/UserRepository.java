package gbrmrqs.dio.spring.security.with.dio.repository;

import gbrmrqs.dio.spring.security.with.dio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

public interface UserRepository extends JpaRepository<User, Integer>{
    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username = (:username)")
    public User findByUsername(@Param ("username") String username);
}

package th.mfu.englishquiz.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.englishquiz.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
    
}

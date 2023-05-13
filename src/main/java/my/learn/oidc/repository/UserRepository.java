package my.learn.oidc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.learn.oidc.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsername(String username);
}

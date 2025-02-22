package id.my.agungdh.linechat.repository;

import id.my.agungdh.linechat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

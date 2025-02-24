package id.my.agungdh.linechat.repository;

import id.my.agungdh.linechat.entity.LineToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LineTokenRepository extends JpaRepository<LineToken, Long> {
    Optional<LineToken> findByToken(String token);
    Optional<LineToken> findByUserId(Long userId);
}

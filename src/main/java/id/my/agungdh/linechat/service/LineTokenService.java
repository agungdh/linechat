package id.my.agungdh.linechat.service;

import id.my.agungdh.linechat.dto.LineTokenDTO;
import id.my.agungdh.linechat.mapper.LineTokenMapper;
import id.my.agungdh.linechat.entity.LineToken;
import id.my.agungdh.linechat.entity.User;
import id.my.agungdh.linechat.repository.LineTokenRepository;
import id.my.agungdh.linechat.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LineTokenService {
    private final LineTokenRepository lineTokenRepository;
    private final UserRepository userRepository;
    private final LineTokenMapper lineTokenMapper;

    public LineTokenDTO save(Long userId, LineTokenDTO lineTokenDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with id " + userId + " not found.");
        }

        LineToken lineToken = lineTokenMapper.toLineToken(lineTokenDTO);
        lineToken.setUserId(userId);

        return lineTokenMapper.toLineTokenDTO(lineTokenRepository.save(lineToken));
    }
}

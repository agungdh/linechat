package id.my.agungdh.linechat.service;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.model.User;
import id.my.agungdh.linechat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDTO create(UserDTO userRequest) {
        User userEntity = new User();
        userEntity.setUsername(userRequest.username());
        userEntity.setPassword(userRequest.password());
        userEntity.setName(userRequest.name());

        userRepository.save(userEntity);

        return new UserDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getName()
        );
    }
}

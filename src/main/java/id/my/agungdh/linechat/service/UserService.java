package id.my.agungdh.linechat.service;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.model.User;
import id.my.agungdh.linechat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();

        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getName()));
        }

        return userDTOList;
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

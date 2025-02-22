package id.my.agungdh.linechat.service;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.mapper.UserMapper;
import id.my.agungdh.linechat.model.User;
import id.my.agungdh.linechat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO create(UserDTO userRequest) {
        User userEntity = userMapper.toUser(userRequest);

        userRepository.save(userEntity);

        return userMapper.toUserDTO(userEntity);
    }
}

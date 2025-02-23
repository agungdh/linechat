package id.my.agungdh.linechat.service;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.mapper.UserMapper;
import id.my.agungdh.linechat.model.User;
import id.my.agungdh.linechat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO create(UserDTO userRequest) {
        User userEntity = userMapper.toUser(userRequest);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);

        return userMapper.toUserDTO(userEntity);
    }


    public UserDTO findById(Long id) {
        return userMapper.toUserDTO(userRepository.findById(id).orElse(null));
    }

    public UserDTO update(Long id, UserDTO userRequest) {
        User userCheck = userRepository.findById(id).orElse(null);

        if (userCheck == null) {
            return null;
        }

        User userEntity = userMapper.toUser(userRequest);

        userEntity.setId(userCheck.getId());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);

        return userMapper.toUserDTO(userEntity);
    }
}

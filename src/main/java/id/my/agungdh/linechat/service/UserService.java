package id.my.agungdh.linechat.service;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.mapper.UserMapper;
import id.my.agungdh.linechat.model.User;
import id.my.agungdh.linechat.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found.");
        }

        User user = optionalUser.get();

        return userMapper.toUserDTO(user);
    }

    public UserDTO update(Long id, UserDTO userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found.");
        }

        User user = optionalUser.get();

        User userEntity = userMapper.toUser(userRequest);

        userEntity.setId(user.getId());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);

        return userMapper.toUserDTO(userEntity);
    }

    public void delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found.");
        }

        userRepository.delete(optionalUser.get());
    }
}

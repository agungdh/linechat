package id.my.agungdh.linechat.controller;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.model.User;
import id.my.agungdh.linechat.repository.UserRepository;
import id.my.agungdh.linechat.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public UserDTO create(@Valid @RequestBody UserDTO user) {
        return userService.create(user);
    }
}

package id.my.agungdh.linechat.controller;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

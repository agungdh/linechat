package id.my.agungdh.linechat.controller;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect error messages and return a bad request response
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.findById(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect error messages and return a bad request response
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        UserDTO userDTO = userService.update(id, user);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDTO);
    }
}

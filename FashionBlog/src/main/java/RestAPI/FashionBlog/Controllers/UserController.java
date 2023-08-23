package RestAPI.FashionBlog.Controllers;

import RestAPI.FashionBlog.DTOs.UserDTO;
import RestAPI.FashionBlog.Services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<String> getUser(@RequestParam String email, @RequestParam String password, HttpSession session){
        return userService.loginUser(email, password, session);
    }
    @PutMapping("edit/profile/{userId}")
    public ResponseEntity<String> editProfile (@RequestParam UserDTO userDTO, @PathVariable Long userId){
        return userService.editProfile(userId, userDTO);
    }
}

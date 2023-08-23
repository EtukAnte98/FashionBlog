package RestAPI.FashionBlog.Services;

import RestAPI.FashionBlog.DTOs.UserDTO;
import RestAPI.FashionBlog.Entities.User;
import RestAPI.FashionBlog.Repositories.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<String> createUser(UserDTO userDTO) {
        userDTO.setPassword(BCrypt.hashpw(userDTO.getPassword(),BCrypt.gensalt()));
        User user = new User(userDTO);
        try{
            userRepo.save(user);
            return new ResponseEntity<> ("User has been successfully created", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something wrong occurred", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> loginUser(String email, String password, HttpSession session) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            User user1 = user.get();
            if(BCrypt.checkpw(password, user1.getPassword())){
                session.setAttribute("user", user1);
                log.info("user in service: " + session.getAttribute("user"));
                return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> editProfile(Long userId, UserDTO userDTO) {
        Optional<User> user1 = userRepo.findById(userId);
        if (user1.isPresent()) {
            User user2 = user1.get();
            user2.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()));
            user2.setEmail(userDTO.getEmail());
            userRepo.save(user2);
            return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong, Couldn't updated profile", HttpStatus.BAD_REQUEST);
    }

    public void deleteUser(Long userId) {
    userRepo.deleteById(userId);
    }
}

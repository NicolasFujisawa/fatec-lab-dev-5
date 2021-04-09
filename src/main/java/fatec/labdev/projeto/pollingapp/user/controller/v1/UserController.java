package fatec.labdev.projeto.pollingapp.user.controller.v1;

import com.fasterxml.jackson.annotation.JsonView;

import fatec.labdev.projeto.pollingapp.user.controller.v1.converter.UserConverter;
import fatec.labdev.projeto.pollingapp.user.controller.v1.request.UserRequest;
import fatec.labdev.projeto.pollingapp.user.controller.v1.response.UserResponse;
import fatec.labdev.projeto.pollingapp.user.enums.UserRole;
import fatec.labdev.projeto.pollingapp.user.model.User;
import fatec.labdev.projeto.pollingapp.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @JsonView(UserView.FullUser.class)
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.userService.findById(id));
    }

    @PostMapping("/create")
    @JsonView(UserView.CreationUser.class)
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User user = UserConverter.convertFrom(userRequest);
        user.setRole(UserRole.MEMBER);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.userService.save(user));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserResponse> signIn(@RequestBody UserRequest loginRequest) {
        User user = UserConverter.convertFrom(loginRequest);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(this.userService.signInUser(user));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

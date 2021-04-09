package fatec.labdev.projeto.pollingapp.user.controller.v1;

import fatec.labdev.projeto.pollingapp.user.controller.v1.converter.UserConverter;
import fatec.labdev.projeto.pollingapp.user.controller.v1.request.UserRequest;
import fatec.labdev.projeto.pollingapp.user.controller.v1.response.UserResponse;
import fatec.labdev.projeto.pollingapp.user.model.User;
import fatec.labdev.projeto.pollingapp.user.service.UserService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserConverter.convertFrom(this.userService.findById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = UserConverter.convertFrom(userRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserConverter.convertFrom(this.userService.save(user)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserResponse> signIn(@RequestBody UserRequest loginRequest) {
        User user = UserConverter.convertFrom(loginRequest);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(this.userService.signInUser(user));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

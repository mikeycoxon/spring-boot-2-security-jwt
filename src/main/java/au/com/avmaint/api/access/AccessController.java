package au.com.avmaint.api.access;

import au.com.avmaint.api.access.model.User;
import au.com.avmaint.api.access.repository.UserRepository;
import au.com.avmaint.api.common.Code;
import au.com.avmaint.api.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Michael Coxon on 17/4/18.
 */
@Controller
public class AccessController {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccessController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    /**
     * Root and admin function to create a new user. Admins can only create users that have an organisation
     * value and that the value is the same as their own.
     * @param user
     */
    @PostMapping("/api/user")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @GetMapping("/api/users")
    public Response<List<User>> getUsers(){
        List<User> users = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return Response.of(users, Code.OK);
    }

    @GetMapping("/api/user/{name}")
    public Response<User> getUser(@PathVariable("name") String name){
        User user = userRepository.findByUsername(name);
        return Response.of(user, Code.OK);
    }


}

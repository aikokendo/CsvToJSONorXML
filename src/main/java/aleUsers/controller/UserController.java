package aleUsers.controller;

import aleUsers.errorHandling.BadRequestException;
import aleUsers.model.User;
import aleUsers.service.StrategyFinder;
import aleUsers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StrategyFinder myStrategy;


    @PostMapping
    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN_ROLE')")  //verify user has admin role to run this
    public String addNewUser(@Valid @RequestBody User user, BindingResult myBR) throws Exception{
        if (!myBR.hasErrors()) {
            return userService.createUser(user);
        }
        else{
            throw new BadRequestException("User provided is not correctly defined.");
        }
    }

    @GetMapping(value="{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") int id) {
        Optional<User> optionalUser = userService.getUser(id);
        return optionalUser.get();
    }

    @GetMapping(value="/parse/{id}")
    @ResponseBody
    public String getParsedUser(@PathVariable("id") int id, @RequestParam(required = false) Map<String,String> query){
        if (!query.containsKey("type")){
            //            return "text error error";
            throw new BadRequestException("A type must be provided");
        }
        else{
            return myStrategy.parseFromCsv(userService.getCSVUser(id), query.get("type"));
        }
    }

    @GetMapping()
    @ResponseBody
    public Iterable<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value="/parse")
    @ResponseBody
    public String getParsedUsers(@RequestParam(required = false) Map<String,String> query){
        if (!query.containsKey("type")){
            //            return "text error error";
            throw new BadRequestException("A type must be provided.");
        }
        else{
            return myStrategy.parseFromCsv(userService.getCSVUsers(), query.get("type"));
        }
    }

}

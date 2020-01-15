package aleUsers.service;

import aleUsers.model.User;
import aleUsers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(int id){
        if (userRepository.existsById(id)){
            return userRepository.findById(id);
        }
        throw new IllegalArgumentException("No user found for the id:" + id +"." );
    }

    public String createUser(User user){
        user = userRepository.save(user);
        return "User created with id:" + user.getId()+ ".";
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public String getCSVUser(int id){
        Optional<User> optionalUser = getUser(id);
        User user = optionalUser.get();
        return user.toCSVHeader() + '\n' + user.toCSVBody();
    }

    public String getCSVUsers(){
        Iterable<User> allUsers = getAllUsers();
        String outputHead = "";
        String outputBody = "";
        for (User user: allUsers){
            outputHead = user.toCSVHeader() + "\n";
            outputBody += user.toCSVBody() + "\n";
        }
        return outputHead + outputBody;
    }

}

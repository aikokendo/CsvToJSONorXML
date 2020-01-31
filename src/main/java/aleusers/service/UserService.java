package aleusers.service;

import aleusers.model.User;
import aleusers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QueueService queueService;

    @Value("${primaryQueue.name}")
    protected String primaryQueueName;


    @Cacheable(cacheNames = "user")
    public Optional<User> getUser(int id){
        if (userRepository.existsById(id)){
            return userRepository.findById(id);
        }
        throw new IllegalArgumentException("No user found for the id:" + id +"." );
    }

    @CacheEvict(cacheNames = {"user","users","userCSV","usersCSV"}, allEntries = true)
    public String createUser(User user){
        queueService.addToQueue(primaryQueueName, user);
        return "Success";
    }

    @Cacheable(cacheNames = "users")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Cacheable(cacheNames = "userCSV")
    public String getCSVUser(int id){
        Optional<User> optionalUser = getUser(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.toCSVHeader() + '\n' + user.toCSVBody();
        }
        else{
            return "user not found";
        }
    }

    @Cacheable(cacheNames = "usersCSV")
    public String getCSVUsers(){
        Iterable<User> allUsers = getAllUsers();
        String outputHead = "";
        StringBuilder bld = new StringBuilder();
        for (User user: allUsers){
            outputHead = user.toCSVHeader() + "\n";
            bld.append(user.toCSVBody());
            bld.append("\n");
        }
        if (bld.length() > 0) {
            bld.deleteCharAt(bld.length() - 1); //delete last skip of line
        }
        return outputHead + bld.toString();
    }

}

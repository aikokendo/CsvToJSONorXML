package aleusers.converter;

import aleusers.model.User;
import aleusers.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO convertToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }

    public User convertToEntity(UserDTO userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }
}

package aleusers.converter;

import aleusers.model.User;
import aleusers.model.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserConverterTest {

    private UserConverter userConverter = new UserConverter();

    @Test
    public void convertToDtoShouldConvertUserToUserDto() {
        User user = new User();
        user.setId(1);
        user.setFirstName("first_name");
        user.setLastName("last_name");

        UserDTO userDTO = userConverter.convertToDto(user);
        Assertions.assertEquals(user.getId(),userDTO.getId());
        Assertions.assertEquals(user.getFirstName(),userDTO.getFirstName());
        Assertions.assertEquals(user.getLastName(),userDTO.getLastName());
    }

    @Test
    public void convertToEntityShouldConvertUserDtoToUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setFirstName("first_name");
        userDTO.setLastName("last_name");

        User user = userConverter.convertToEntity(userDTO);
        Assertions.assertEquals(user.getId(),userDTO.getId());
        Assertions.assertEquals(user.getFirstName(),userDTO.getFirstName());
        Assertions.assertEquals(user.getLastName(),userDTO.getLastName());
    }
}

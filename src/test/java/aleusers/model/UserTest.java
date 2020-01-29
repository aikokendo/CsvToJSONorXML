package aleusers.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

//    @Test
//    void getId() {
//
//    }

    @Test
    public void testId_setsProperly() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
//        given
        final User user = new User();
        int a = 1;

//        when
        user.setId(a);

//        then
        int id = user.getId();
        Assertions.assertEquals(id,a);
    }
}
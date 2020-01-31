package aleusers.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testId_setsProperly() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
//        given
        final User user = new User();
        int id = 1;
        String fn = "john";
        String ln = "doe";

//        when
        user.setId(id);
        user.setFirstName(fn);
        user.setLastName(ln);

//        then
        Assertions.assertEquals(id,user.getId());
        Assertions.assertEquals(fn,user.getFirstName());
        Assertions.assertEquals(ln,user.getLastName());
    }

    @Test
    void testToString() {
        //        given
        int id = 1;
        String fn = "john";
        String ln = "doe";

//        when
        User user = new User(fn,ln);
        user.setId(id);

//        then
        String expectedOutput = "User{id=1, firstName='john', lastName='doe'}";
        String expectedCSVBody = "1,'john','doe'";

        Assertions.assertEquals(user.toString(),expectedOutput);
        Assertions.assertEquals(user.CSVHEADER,user.toCSVHeader());
        Assertions.assertEquals(user.toCSVBody(),expectedCSVBody);


    }


}
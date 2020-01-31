package aleusers.model;

import javax.validation.constraints.NotNull;

public class UserDTO {
    @NotNull
    private String firstName;
    private String lastName;
    private int id;


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }
}

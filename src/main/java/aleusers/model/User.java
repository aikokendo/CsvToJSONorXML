package aleusers.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private static final String CSVHEADER = "'id','first_name','last_name'";


    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String toSqlInsert(){
        return "INSERT INTO users (id,first_name,last_name) VALUES(" + getId() + ",'" + getFirstName() + "','" + getLastName() + "');";
    }

    public String toPipe(){
        return getId() + "|" + getFirstName() + "|" + getLastName();
    }

    public String toCSVHeader(){
        return CSVHEADER;
    }

    public String toCSVBody(){
        return getId() + ",'" + getFirstName() + "','" + getLastName()+"'";
    }

}

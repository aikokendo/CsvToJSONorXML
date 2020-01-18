package aleUsers.repository;

        import aleUsers.model.User;
        import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {


}

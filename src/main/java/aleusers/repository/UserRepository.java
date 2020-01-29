package aleusers.repository;

        import aleusers.model.User;
        import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {


}

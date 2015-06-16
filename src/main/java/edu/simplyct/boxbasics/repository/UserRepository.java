package edu.simplyct.boxbasics.repository;

import edu.simplyct.boxbasics.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by cyril on 5/25/15.
 */
@Service
public interface UserRepository extends CrudRepository<User, Long> {
}

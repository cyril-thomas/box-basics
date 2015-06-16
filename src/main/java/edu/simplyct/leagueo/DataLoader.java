package edu.simplyct.leagueo;

import edu.simplyct.leagueo.model.User;
import edu.simplyct.leagueo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by cyril on 6/11/15.
 */
@Component
public class DataLoader {
    @Autowired
    UserRepository userRepository;

    //@PostConstruct
    public void init() {
        loadUsers();
    }

    private void loadUsers() {
        User user = null;
        int count = 0;
        while (count < 10) {
            user = new User();
            user.setFirstName("James "+count);
            user.setLastName("Brown ");
            user.setFirstName(String.format("james.brown_%s@gmail.com",count));
            userRepository.save(user);
        }

    }
}

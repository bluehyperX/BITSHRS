package com.app.hotelmanagementsystem;

import com.app.hotelmanagementsystem.entity.Role;
import com.app.hotelmanagementsystem.entity.User;
import com.app.hotelmanagementsystem.repository.RoleRepository;
import com.app.hotelmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Disabled
    public void testCreateUser() {
        User user = new User();
        user.setFirstName("Marko");
        user.setLastName("Markovic");
        user.setEmailAddress("marko@gmail.com");
        user.setPassword("marko");

        User savedUser = userRepository.save(user);
        User existUser = entityManager.find(User.class, savedUser.getUserId());

        assertThat(existUser.getEmailAddress()).isEqualTo(user.getEmailAddress());
    }

    @Test
    @Disabled
    public void testFindUserByEmail() {
        String emailAddress = "snezana@gmail.com";
        User user = userRepository.findByEmailAddress(emailAddress);
        assertThat(user).isNotNull();
    }

    @Test
    @Disabled
    public void testAddRoleToNewUser() {
        User user = new User();
        user.setFirstName("Petar");
        user.setLastName("Petrovic");
        user.setEmailAddress("petar@gmail.com");
        user.setPassword("petar");

        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }

    @Test
    @Disabled
    public void testAddRolesToExistingUser() {
        User user = userRepository.findById(19L).get();

        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);

        Role roleAdmin = roleRepository.findById(21L).get();
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);
        assertThat(savedUser.getRoles().size()).isEqualTo(3);
    }
}

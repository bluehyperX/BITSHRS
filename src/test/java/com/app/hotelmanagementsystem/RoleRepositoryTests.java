package com.app.hotelmanagementsystem;

import com.app.hotelmanagementsystem.entity.Role;
import com.app.hotelmanagementsystem.repository.RoleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Disabled
    public void testCreateRoles() {
        Role user = new Role("User");
        Role admin = new Role("Admin");

        roleRepository.saveAll(List.of(user, admin));

        List<Role> listRoles = roleRepository.findAll();
        assertThat(listRoles.size()).isEqualTo(2);
    }
}

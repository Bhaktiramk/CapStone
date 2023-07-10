package com.capstone.repo;

import com.capstone.dto.UserDto;
import com.capstone.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // Create a test user
        User user = new User();
        user.setName("John");
         
        user.setEmail("johndoe@example.com");
        user.setPassword("password");
        entityManager.persist(user);
        entityManager.flush();

        // Call the repository method
        User foundUser = userRepository.findByEmail("johndoe@example.com");

        // Verify the result
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals("John", foundUser.getName());
         
    }

    @Test
    public void testFindByEmailAndPassword() {
        // Create a test user
        User user = new User();
        user.setName("Jane");
        user.setEmail("janesmith@example.com");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        // Call the repository method
        Optional<UserDto> optionalUser = userRepository.findByEmailAndPassword("janesmith@example.com", "password123");

        // Verify the result
        Assertions.assertTrue(optionalUser.isPresent());
        UserDto userDto = optionalUser.get();
        Assertions.assertEquals("Jane", userDto.getFirstName());
        Assertions.assertEquals("Smith", userDto.getLastName());
        Assertions.assertEquals("janesmith@example.com", userDto.getEmail());
    }
}

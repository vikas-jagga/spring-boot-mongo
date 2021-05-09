package com.assessment.user.business;

import com.assessment.user.business.impl.UserBusinessImpl;
import com.assessment.user.domain.User;
import com.assessment.user.repository.UserRepository;
import com.assessment.user.service.impl.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserBusinessTest {

    @InjectMocks
    UserBusiness userBusiness = new UserBusinessImpl();

    @Mock
    UserRepository userRepository;
    @Mock
    SequenceGeneratorService sequenceGeneratorService;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(userBusiness, "userRepository", userRepository);
        ReflectionTestUtils.setField(userBusiness, "sequenceGeneratorService", sequenceGeneratorService);
    }

    @Test
    public void testAddUser() {
        String DB_SEQUENCE = "db_sequence";
        User userStub = new User();
        when(userRepository.save(userStub)).thenReturn(userStub);
        when(sequenceGeneratorService.getSequenceNumber(DB_SEQUENCE)).thenReturn(1);
        User user = userBusiness.createUser(userStub);

        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetUser() {
        User mockUser = new User();
        mockUser.setId(1);
        Optional<User> userStub = Optional.of(mockUser);
        when(userRepository.findById(1)).thenReturn(userStub);
        User user = userBusiness.getUser(1);

        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    public void testUpdateUser() {
        User userStub = new User();
        userStub.setId(1);
        when(userRepository.save(userStub)).thenReturn(userStub);
        User user = userBusiness.updateUser(userStub);

        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    public void testDeleteUser() {
        User userStub = new User();
        userStub.setId(1);
        userBusiness.deleteUser(1);
    }
}

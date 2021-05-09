package com.assessment.user.service;

import com.assessment.user.business.UserBusiness;
import com.assessment.user.domain.User;
import com.assessment.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Mock
    UserBusiness userBusiness;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(userService, "userBusiness", userBusiness);
    }

    @Test
    public void testAddUser() {
        User userInput = new User();
        User userOutput = new User();
        userOutput.setId(1);
        when(userBusiness.createUser(userInput)).thenReturn(userOutput);
        User user = userService.createUser(userInput);

        assertNotNull(user);
        assertEquals(1, user.getId());
    }
}

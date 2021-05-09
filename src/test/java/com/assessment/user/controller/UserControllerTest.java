package com.assessment.user.controller;

import com.assessment.user.domain.User;
import com.assessment.user.service.UserService;
import com.assessment.user.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void testAddUser() throws Exception {

        // prepare data and mock's behaviour
        User userStub = new User();
        userStub.setId(1);
        when(userService.createUser(any(User.class))).thenReturn(userStub);

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/users/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonString(userStub))).andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(result.getResponse().getContentAsString(), User.class);
        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetUser() throws Exception {

        // prepare data and mock's behaviour
        User userStub = new User();
        userStub.setId(1);
        when(userService.getUser(1)).thenReturn(userStub);

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/getUser/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(result.getResponse().getContentAsString(), User.class);
        assertNotNull(user);
        assertEquals(1, user.getId());
    }

}

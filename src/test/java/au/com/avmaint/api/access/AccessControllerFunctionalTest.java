package au.com.avmaint.api.access;

import au.com.avmaint.api.access.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static au.com.avmaint.api.access.SecurityConstants.HEADER_STRING;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Michael Coxon on 27/5/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("embedded")
@AutoConfigureMockMvc
public class AccessControllerFunctionalTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void doSignup() throws Exception {
        String requestString = "{\"username\": \"mike@gmail.com\",\"password\": \"password\"}";
        mvc.perform(post("/api/user").contentType(APPLICATION_JSON)
                .content(requestString))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void doLoginFailsWithUserNotExists() throws Exception {
        String requestString = "{\"username\": \"mike@gmail.com\",\"password\": \"password\"}";
        mvc.perform(post("/login").contentType(APPLICATION_JSON)
                .content(requestString))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void doLoginSuccessWithUserExists() throws Exception {
        String requestString = "{\"username\": \"rmjcoxon@gmail.com\",\"password\": \"password\"}";
        mvc.perform(post("/login").contentType(APPLICATION_JSON)
                .content(requestString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists(HEADER_STRING));
    }

}

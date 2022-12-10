package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.exceptions.UsernameAlreadyTaken;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.services.CreateUserService;
import kr.megaptera.makaobank.services.GetUserService;
import kr.megaptera.makaobank.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private CreateUserService createUserService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void user() throws Exception {
        given(getUserService.detail(any())).willReturn(User.fake());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"amount\"")
                ));
    }

    @Test
    void userWithoutToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/me"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signup() throws Exception {
        given(createUserService.create(any()))
                .willReturn(User.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"name\":\"전제나\","
                                + "\"username\":\"test1\","
                                + "\"password\":\"Test123!\","
                                + "\"passwordCheck\":\"Test123!\""
                                + "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\"")
                ));
    }

    @Test
    void signUpWithShortName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"제나\"," +
                                "\"username\":\"test1\"," +
                                "\"password\":\"Test123!\"," +
                                "\"passwordCheck\":\"Test123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithLongName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"제나제나제나제나\"," +
                                "\"username\":\"test1\"," +
                                "\"password\":\"Test123!\"," +
                                "\"passwordCheck\":\"Test123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithEnglishName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"Jenna\"," +
                                "\"username\":\"test1\"," +
                                "\"password\":\"Test123!\"," +
                                "\"passwordCheck\":\"Test123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithKoreanUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"전제나\"," +
                                "\"username\":\"제나전\"," +
                                "\"password\":\"Test123!\"," +
                                "\"passwordCheck\":\"Test123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithShortUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"전제나\"," +
                                "\"username\":\"Hi\"," +
                                "\"password\":\"Test123!\"," +
                                "\"passwordCheck\":\"Test123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithLongUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"전제나\"," +
                                "\"username\":\"Hiiiiiiiiiiii\"," +
                                "\"password\":\"Test123!\"," +
                                "\"passwordCheck\":\"Test123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithExistingUsername() throws Exception {
        given(createUserService.create(any()))
                .willThrow(new UsernameAlreadyTaken("Test1"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"전제나\"," +
                                "\"username\":\"test1\"," +
                                "\"password\":\"Test123!\"," +
                                "\"passwordCheck\":\"Test123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithShortPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"전제나\"," +
                                "\"username\":\"test1\"," +
                                "\"password\":\"Test1!\"," +
                                "\"passwordCheck\":\"Test1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutUppercase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"전제나\"," +
                                "\"username\":\"test1\"," +
                                "\"password\":\"test123!\"," +
                                "\"passwordCheck\":\"test123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutLowercase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"전제나\"," +
                                "\"username\":\"test1\"," +
                                "\"password\":\"TEST123!\"," +
                                "\"passwordCheck\":\"TEST123!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"전제나\"," +
                                "\"username\":\"test1\"," +
                                "\"password\":\"TESTasdf!\"," +
                                "\"passwordCheck\":\"TESTasdf!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutSpecialCharacter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"전제나\"," +
                                "\"username\":\"test1\"," +
                                "\"password\":\"TESTasdf1\"," +
                                "\"passwordCheck\":\"TESTasdf1\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}

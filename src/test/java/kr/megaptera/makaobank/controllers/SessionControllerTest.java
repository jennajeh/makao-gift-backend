package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.exceptions.LoginFailed;
import kr.megaptera.makaobank.models.User;
import kr.megaptera.makaobank.services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    void loginSuccess() throws Exception {
        given(loginService.login(any(), any())).willReturn(User.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"username\":\"Jenna1234!\","
                                + "\"password\":\"Asdf1234!\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void loginFail() throws Exception {
        given(loginService.login(any(), any())).willThrow(new LoginFailed());

        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"username\":\"Jenna1234!\","
                                + "\"password\":\"xxx\""
                                + "}"))
                .andExpect(status().isBadRequest());
    }
}

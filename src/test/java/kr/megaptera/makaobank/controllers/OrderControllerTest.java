package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.OrderDto;
import kr.megaptera.makaobank.dtos.ProductDto;
import kr.megaptera.makaobank.models.Order;
import kr.megaptera.makaobank.services.CreateOrderService;
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

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@ActiveProfiles("test")
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateOrderService createOrderService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;
    private ProductDto productDto;
    private OrderDto orderDto;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
        productDto = new ProductDto(1L, "소파", "제조사", 10_000L, "편안한 소파", "imageUrl");
        orderDto = new OrderDto(1L, 1, 10_000L, "강보니", "서울시 성동구 성수동", "생일 축하해!", productDto, LocalDateTime.now());
    }

    @Test
    void order() throws Exception {
        given(createOrderService.order(any(), any()))
                .willReturn(Order.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"quantity\":1," +
                                "\"receiver\":\"강보니\"," +
                                "\"address\":\"서울시 성동구 성수동\"," +
                                "\"message\":\"생일 축하해!\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\"")
                ));
    }
}

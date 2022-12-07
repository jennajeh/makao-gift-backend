package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.models.Product;
import kr.megaptera.makaobank.services.GetProductService;
import kr.megaptera.makaobank.services.GetProductsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductsService getProductsService;

    @MockBean
    private GetProductService getProductService;

    @Test
    void lists() throws Exception {
        Product product = mock(Product.class);

        given(getProductsService.list()).willReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("products")
                ));
    }
}

package com.tcs.denmail.online.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import com.tcs.denmail.online.app.model.RenrakuDetailModel;
import com.tcs.denmail.online.domain.entity.RenrakuViewEntity;
import com.tcs.denmail.online.domain.repository.RenrakuViewRepository;
import com.tcs.denmail.online.domain.service.renraku.RenrakuDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * RenrakuDetailServiceTest
 */
@SpringBootTest
public class RenrakuDetailServiceTest {

    MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Autowired
    private RenrakuDetailService renrakuDetailService;

    @Autowired
    private RenrakuViewRepository renrakuViewRepository;
    
    @Test
    public void test001() {

        RenrakuDetailModel renrakuDetail =
                renrakuDetailService.getRenrakuDetail("R16081865", "135");

        System.out.println(renrakuDetail.toString());
    }

    @Test
    public void test002() throws Exception {

        MvcResult andReturn = this.mockMvc
                .perform(get("/renraku/{kanriNo}", "R16081865").param("atesakiTenpoCd", "135"))
                .andExpect(status().isOk()).andReturn();
        byte[] contents = andReturn.getResponse().getContentAsByteArray();
        System.out.println(new String(contents,StandardCharsets.UTF_8));
    }

    @Test
    public void testRepository(){
        Optional<RenrakuViewEntity> findById = renrakuViewRepository.findById("R16081865");
        if(findById.isPresent()) {
            System.out.println(findById.get());
        } else{
            System.out.println("not found");
        }
    }
}

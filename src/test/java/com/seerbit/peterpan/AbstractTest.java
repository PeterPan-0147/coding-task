package com.seerbit.peterpan;

import com.seerbit.peterpan.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
public abstract class AbstractTest {
    @Autowired
    protected MockMvc mockMvc;

}



package com.foamfish.spock.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import spock.lang.Specification

class BaseWebMvcSpec extends Specification {

    @Autowired
    protected MockMvc mvc

    ResultActions doRequest(MockHttpServletRequestBuilder requestBuilder) throws Exception {
        return mvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
    }

    ResultActions asyncResult(MvcResult mvcResult) {
        mvc.perform(asyncDispatch(mvcResult))
                .andDo(MockMvcResultHandlers.print())
    }
}

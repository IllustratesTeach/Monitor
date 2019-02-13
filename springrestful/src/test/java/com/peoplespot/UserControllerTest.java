package com.peoplespot;

import com.peoplespot.controller.UserController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author <a href="yuchen_1997_200486@126.com">yuchen</a>.
 * @since 2018/11/19
 */
public class UserControllerTest {

    @Test
    public void test_AddUserInfo() throws Exception{
        UserController controller = new UserController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        //mockMvc.perform(get("/")).andExpect(view().name(""));
    }
}

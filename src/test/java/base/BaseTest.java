package base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = { "classpath*:**/springmvc-config.xml", "classpath*:**/mybatis-config.xml" })
public abstract class BaseTest {

    //    @Autowired
    //    protected MockHttpServletRequest request;
    //
    //    @Autowired
    //    protected MockHttpSession session;
    //
    //    @Autowired
    //    protected MockHttpServletResponse response;

}

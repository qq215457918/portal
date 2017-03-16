
package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.portal.service.ReceptionInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceptionInfoServiceTest {

    @Autowired
    protected ReceptionInfoService receptionInfoService;

    HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

    @Test
    public void ReceptionInfoINGTest() {
        //        HttpServletRequest request, HttpServletResponse response
        Mockito.when(httpServletRequest.getParameter("create_date")).thenReturn("20161024");
        Mockito.when(httpServletRequest.getParameter("staff_name")).thenReturn("3");
        Mockito.when(httpServletRequest.getParameter("iDisplayStart")).thenReturn("1");
        Mockito.when(httpServletRequest.getParameter("iDisplayLength")).thenReturn("10");

        //JSONObject result = receptionInfoService.receptionING(httpServletRequest, httpServletResponse);
        //log.debug("ReceptionInfoINGTest: {}", result.toString());
        //Assert.assertNotNull(result);
    }
}

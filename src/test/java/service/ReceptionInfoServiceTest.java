
package service;

import base.BaseTest;
import com.portal.bean.result.ReceptionInfoForm;
import com.portal.service.ReceptionInfoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ReceptionInfoServiceTest extends BaseTest {

    @Autowired
    protected ReceptionInfoService receptionInfoService;

    @Test
    public void ReceptionInfoINGTest() {
        String date = "20161024";
        List<ReceptionInfoForm> info = receptionInfoService.receptionING(null, null, date);
        log.debug("ReceptionInfoINGTest: {}", info.toString());
        Assert.assertNotNull(info);
    }
}

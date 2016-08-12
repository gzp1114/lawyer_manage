

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawyer.cores.result.Results;
import com.lawyer.cores.tools.jackson.JacksonUtil;
import com.lawyer.system.usercenter.domain.SysMenuFunction;
import com.lawyer.system.usercenter.service.SysMenuFunctionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext.xml"})
public class TreeTest {
	private static Log log = LogFactory.getLog(TreeTest.class);
	
	@Autowired
    private SysMenuFunctionService sysMenuFunctionService;
 	@Test
    public void testTree() {
 		Results results = sysMenuFunctionService.getMenuTree((long) 132);
 		String  html=  (String) results.getData();
         System.out.println(html);  
    }
 	
}
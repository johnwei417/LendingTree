import com.honglin.dao.UserDao;
import com.honglin.model.entity.User;
import com.honglin.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * honglinwei created on 2/13/20 inside the package - PACKAGE_NAME
 * this class only for unit testing using mockito
 *
 * @Author: Honglin Wei
 */

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();
    @InjectMocks
    UserServiceImpl service;
    @Mock
    UserDao userDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFind() {
        User user = new User("honglin", "asdqwe");
        Mockito.when(userDao.findByUsername("honglin")).thenReturn(user);

        User userTest = service.findOne("honglin");
        //Assert.assertNotNull(user);
        Assert.assertEquals(userTest, user);
        System.out.println("Test finished");


    }


}

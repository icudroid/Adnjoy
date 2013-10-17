import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.k2i.adnjoy.business.user.Right;
import fr.k2i.adnjoy.business.user.Sex;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dao.CountryDao;
import fr.k2i.adnjoy.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-*.xml")  
public class UserManagerTest {
	
	
    private UserDao userDao;
    
    @Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
    
    private CountryDao countryDao;
    
    @Resource
	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	@Test
	public void testAddUser() throws Exception {
		for (int i = 1; i <= 20; i++) {
			User u = new User();
			u.setEmail("testuser"+i+"@adbeback.fr");
			u.setFirstName("Test");
			u.setLastName("TEST"+i);
			u.setPassword("1234");
			u.setPseudo("test"+i);
			u.setSex(Sex.MR);
			u.setValidated(true);
			u.setAddress1("add");
			u.setBirthday(new GregorianCalendar(1977,4,1).getTime());
			u.setCity("Paris");
			u.setValidateUrl(Integer.toString(i)+"test");
			u.setZipCode("75000");
			u.setRight(Right.User);
			u.setCountry(countryDao.getAll().get(0));
			userDao.save(u);
		}
	}




}

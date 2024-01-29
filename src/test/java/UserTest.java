import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.shi.dao.UserDao;
import xyz.shi.domain.QueryResult;
import xyz.shi.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {
    @Autowired
    private UserDao dao;

    @Test
    @Transactional
    @Rollback(false) // 不需要回滚
    public void save() {
        User user = new User();
        user.setUserName("test789");
        user.setPassword("777777");
        dao.save(user);
    }
    @Test
    @Transactional
    // 如果不加这个,以上就是回滚,这样的好处就是在单元测试中能测试通过，但是最终不修改数据库的值
    @Rollback(false)
    public void update() {
        User user = new User();
        user.setUserId(52);
        user.setUserName("test8888");
        user.setPassword("888888");
        dao.update(user);
    }
    @Test
    @Transactional
    public void findId() {
        User user = dao.findById(52);
        System.out.println(user.getUserName());
    }
    @Test
    @Transactional
    public void findAll() {
        QueryResult result = dao.findAll(0, 5);
        System.out.println(result.getCount());
        for (Object o : result.getList()) {
            User user = (User)o;
            System.out.println(user.getUserName());
        }
    }
    @Test
    @Transactional
    public void delete() {
        User user = new User();
        user.setUserId(53);
        dao.delete(user);
    }
}

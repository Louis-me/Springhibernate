import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.shi.dao.PersonDao;
import xyz.shi.domain.IdCard;
import xyz.shi.domain.Person;
import xyz.shi.domain.QueryResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PersonTest {
    @Autowired
    private PersonDao dao;

    @Test
    @Transactional
//    @Rollback(false) // 不需要回滚
    public void save() {
        IdCard idCard = new IdCard();
        idCard.setId(17);
        Person person = new Person();
        person.setName("王大伟7");
        person.setIdCard(idCard);
        dao.save(person);
    }
    @Test
    @Transactional
//    @Rollback(false)
    public void find() {
        QueryResult result = dao.findAll(0, 5);
        System.out.println(result.getCount());
        for (Object o : result.getList()) {
            Person person = (Person) o;
            System.out.println(person.getName());
            System.out.println(person.getIdCard().getCardNo());
        }

    }
    @Test
    @Transactional
    @Rollback(false)
    public void update() {
        IdCard idCard = new IdCard();
        idCard.setId(17);
        Person person = new Person();
        person.setName("王大伟16");
        person.setIdCard(idCard);
        person.setId(16);
        dao.update(person);
    }
}

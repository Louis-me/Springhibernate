import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.shi.dao.StudentDao;
import xyz.shi.domain.Grade;
import xyz.shi.domain.QueryResult;
import xyz.shi.domain.Student;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StudentTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    @Transactional
    @Rollback(false) // 不需要回滚
    public void save() {
        Grade grade = new Grade();
        grade.setId(36);

        Student student = new Student();
        student.setName("王大伟362");
        //设置学生的班级
        student.setGrade(grade);

        Student student2 = new Student();
        student2.setGrade(grade);
        student2.setName("小红362");

        studentDao.save(student);
        studentDao.save(student2);
    }
    @Test
    @Transactional
    public void findAll() {
        QueryResult result = studentDao.findAll(0, 5);
        System.out.println(result.getCount());
        List<Object[]> list  = result.getList();
        for (Object[] arr : list) {
            Student student = (Student) arr[0];
            System.out.println("Student: " + student.getName() + ", Grade: " + student.getGrade().getName());

        }
    }
}

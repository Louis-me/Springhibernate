import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.shi.dao.StudentDao;
import xyz.shi.dao.StudentManyToManyDao;
import xyz.shi.domain.Course;
import xyz.shi.domain.Grade;
import xyz.shi.domain.QueryResult;
import xyz.shi.domain.Student;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StudentManyToManyTest {
    @Autowired
    private StudentManyToManyDao studentDao;
    @Test
    @Transactional
    @Rollback(false) // 不需要回滚
    public void save() {
        //-----新增两个学生，关联到一个班级下面，同时新增的两个学生关联不同的课程（需要用到第三方表）

        //一个班级
        Grade grade = new Grade();
        grade.setId(36);
        // 新建两个学生
        Student student1 = new Student();
        student1.setName("王大伟36121");
        //设置学生的班级
        student1.setGrade(grade);

        Student student2 = new Student();
        student2.setGrade(grade);
        student2.setName("小红36121");
        // 两个学生各自对应两个课程
        Course course1 = new Course();
        course1.setId(17);

        Course course2 = new Course();
        course2.setId(18);


        studentDao.save(student1);
        studentDao.save(student2);

        //学生选课的关系相互关联
        course1.getStudents().add(student1);
        student1.getCourses().add(course1);

        course2.getStudents().add(student2);
        student2.getCourses().add(course2);
    }
    @Test
    @Transactional
    public void findAll() {
        QueryResult qresult = studentDao.findAll(0, 5);
        System.out.println(qresult.getCount());
        List<Object[]> list  = qresult.getList();
        for (Object[] result : list) {
            Student student = (Student) result[0];
            Course course = (Course) result[1];
            Grade grade = (Grade) result[2];
            System.out.println(student);
            System.out.println(course);
            System.out.println(grade);
        }
    }
}


import model.Class;
import model.Student;
import utils.ListUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Student tom = new Student();
        tom.setClassId(1);
        tom.setName("TOM");
        tom.setAge(12);
        Student jerry = new Student();
        jerry.setClassId(2);
        jerry.setName("JERRY");
        jerry.setAge(13);
        Student lili = new Student();
        lili.setClassId(1);
        lili.setName("LILI");
        lili.setAge(14);
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(tom);
        studentList.add(jerry);
        studentList.add(lili);

        Class classOne = new Class();
        classOne.setClassId(1);
        classOne.setClassName("one");
        Class classTwo = new Class();
        classTwo.setClassId(2);
        classTwo.setClassName("two");
        List<Class> classList = new ArrayList<Class>();
        classList.add(classOne);
        classList.add(classTwo);

        ListUtil.mergeIntoParentList("classId", "studentList",
                classList, studentList);
        System.out.println(classList);

    }
}

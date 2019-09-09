package generic;

/**
 * @author by liuweiliang1
 * @date 2019/9/9 14:27
 * @description
 */
public class Student {

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                '}';
    }
}

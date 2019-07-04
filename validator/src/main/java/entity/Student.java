package entity;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

public class Student {

    @NotBlank
    private String name;

    /**
     *the attribute values of the constraint mapped to the attribute names
     *the currently validated value (property, bean, method parameter etc.) under the name validatedValue
     * */
    @Range(min = 16, max = 30, message = "%s必须在{min}-{max}之间,当前值为${validatedValue}")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

package demo;

import io.reactivex.Observable;


/**
 * zip 组合两个observable
 * 1.如果一个多一个少 以少的为准
 * 2.组合严格按照两个observable的发射顺序
 * */
public class ReactZip {
    public static void main(String[] args) {

        Observable observable1 = Observable.create(emitter -> {
            emitter.onNext("liuweilinag");
            emitter.onNext("chenlong");
            emitter.onNext("wangshuxi");
            emitter.onNext("songyang");
            emitter.onComplete();
        });

        Observable observable2 = Observable.create(emitter -> {
            emitter.onNext(29);
            emitter.onNext(23);
            emitter.onComplete();
        });


        Observable zipObserval = Observable.zip(observable1, observable2, (o1, o2) -> {
            Person person = new Person();
            person.setName((String)o1);
            person.setAge((Integer) o2);
            return person;
        });

        zipObserval.subscribe(System.out::println)
        .dispose();

    }
}

class Person {
    private String name;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

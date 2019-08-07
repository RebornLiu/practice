package jdk8;

import jdk8.impl.TestImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        TestImpl test = new TestImpl();
        test.selfEcho();

        Collector.of(ArrayList::new, List::add, (l, r) -> {l.addAll(r); return l;});

        List<Person> people = new ArrayList<>();
        people.add(new Person(1, 1, "first"));
        people.add(new Person(1, 2, "second"));
        people.add(new Person(1, 2, "third"));
        people.add(new Person(2, 1, "forth"));
        people.add(new Person(2, 2, "five"));

        Map<Integer, List<Person>> mm = people.stream().map(ele -> {
            Map<Integer, List<Person>> map = new HashMap<>();
            List<Person> one = new ArrayList<>(1);
            one.add(ele);
            map.put(ele.getAge(), one);
            return map;
        }).reduce(new HashMap<>(), (m1, m2) -> {
                    for (Map.Entry<Integer, List<Person>> entry : m1.entrySet()) {
                        if (m2.containsKey(entry.getKey())) {
                            m2.get(entry.getKey()).addAll(entry.getValue());
                        } else {
                            m2.put(entry.getKey(), entry.getValue());
                        }
                    }
                    return m2;
                }
            );
    }
}

class  Person {
    Integer age;
    Integer score;
    String name;

    public Person(Integer age, Integer score, String name) {
        this.age = age;
        this.score = score;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

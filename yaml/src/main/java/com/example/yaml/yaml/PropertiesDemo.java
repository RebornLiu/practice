package com.example.yaml.yaml;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("demo")
@ConfigurationProperties(prefix = "person")
@PropertySource(value = {"classpath:config/person.properties"})
//PropertySource注解不支持yaml文件
public class PropertiesDemo {
    private Integer age;

    private String name;

    private List<String> childs;

    private Map<Integer, String> keyWords;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getChilds() {
        return childs;
    }

    public void setChilds(List<String> childs) {
        this.childs = childs;
    }

    public Map<Integer, String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Map<Integer, String> keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public String toString() {
        return "PropertiesDemo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", childs=" + childs +
                ", keyWords=" + keyWords +
                '}';
    }
}

package jdk8.api;

public interface ITest {
    static String echo(String str) {
        return str;
    }

    default String echoInt(Integer i) {
        return String.valueOf(i);
    }
}

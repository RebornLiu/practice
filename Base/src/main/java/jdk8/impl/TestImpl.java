package jdk8.impl;

import jdk8.api.ITest;

public class TestImpl implements ITest {

    public void selfEcho() {
        System.out.println(ITest.echo("itest.echo"));
        System.out.println(echoInt(111));
    }
}

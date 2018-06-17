package com.self;


import org.junit.Assert;
import org.junit.Test;

public class ClientTest {

    @Test
    public void testException() {
        Client client = new Client("exception");
        String rs = client.execute();
        Assert.assertEquals("exception", rs);

    }

    @Test
    public void testTimeOut() {
        Client client = new Client("timeout");
        String rs = client.execute();
        Assert.assertEquals("timeout", rs);
    }

}

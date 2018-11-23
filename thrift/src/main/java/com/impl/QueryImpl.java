package com.impl;

import com.thrift.QryResult;
import com.thrift.TestQry;
import org.apache.thrift.TException;

public class QueryImpl implements TestQry.Iface {


    @Override
    public QryResult qryTest(int qryCode) throws TException {
        QryResult qryResult = new QryResult();
        qryResult.setCode(qryCode);
        qryResult.setMsg("哈哈哈哈");
        System.out.println("in and leave query");
        return qryResult;
    }
}

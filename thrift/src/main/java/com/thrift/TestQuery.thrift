namespace java com.thrift
struct QryResult {
        1:i32 code;
        2:string msg;
}
service TestQry{
        QryResult qryTest(1:i32 qryCode)
}
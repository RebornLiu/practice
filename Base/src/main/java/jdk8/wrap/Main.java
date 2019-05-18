package jdk8.wrap;

public class Main {
    public static void main(String[] args) {
        Response<String> response = new Response<>();
        response.setSuccess(true);
        response.setMsg("error");
        Response response1 = response.handleResp(r -> {
            Response<Integer> iR = new Response<>();
            iR.setResult(1);
            iR.setSuccess(true);
            return iR;
        }).handleResp(r -> {
            Response<String> sR = new Response<>();
            sR.setSuccess(false);
            sR.setResult("this is string");
            return sR;
        }).handleSuccess(str -> {
            Response<String> sR = new Response<>();
            sR.setSuccess(true);
            sR.setResult("this is string" + str);
            return sR;
        }).handleFail(r -> System.out.println(r.toString()));


       // System.out.println(response1);
    }

}

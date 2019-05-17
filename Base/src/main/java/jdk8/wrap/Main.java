package jdk8.wrap;

public class Main {
    public static void main(String[] args) {
        Response<String> response = new Response<>();
        response.setSuccess(true);
        response.setMsg("error");
        Response response1 = response.withHandler(r -> {
            Response<Integer> iR = new Response<>();
            iR.setResult(1);
            iR.setSuccess(false);
            return iR;
        }).withHandler(r -> {
            Response<String> sR = new Response<>();
            sR.setSuccess(true);
            sR.setResult("this is string");
            return sR;
        });


        System.out.println(response1);
    }

}

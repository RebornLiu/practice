package jdk8.wrap;

import java.util.function.Function;

public class Response<T> {
    private boolean success;
    private T result;
    private String code;
    private String msg;


    /**
     * 顺序调用 如果中间出现false 则最终返回第一个false
     * */
    @SuppressWarnings("unchecked")
    public <E> Response<E> withHandler(Function<Response<T>, Response<E>> function) {
        if (!this.isSuccess()) {
            return (Response<E>) this;
        }
        return function.apply(this);
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", result=" + result +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}

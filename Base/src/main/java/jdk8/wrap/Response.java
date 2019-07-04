package jdk8.wrap;

import java.util.function.Consumer;
import java.util.function.Function;

public class Response<T> {
    private boolean success;
    private T result;
    private String code;
    private String msg;


    /**
     * 处理成功时的result字段 如果时false 直接跳过返回当前的Response
     * */
    @SuppressWarnings("unchecked")
    public <E> Response<E> handleSuccess(Function<T, Response<E>> function) {
        if (!this.isSuccess()) {
            return (Response<E>) this;
        }
        return function.apply(this.getResult());
    }


    /**
     * 处理response
     * */
    public <E> Response<E> handleResp(Function<Response<T>, Response<E>> function) {
        return function.apply(this);
    }


    /**
     * 处理 错误返回当前的response
     * */
    public Response<T> handleFail(Consumer<Response<T>> consumer) {
        if (!this.isSuccess()) {
            consumer.accept(this);
        }

        return this;
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

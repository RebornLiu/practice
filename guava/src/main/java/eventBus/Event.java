package eventBus;

public class Event {
    private Integer code;

    private String message;

    public Event() {
    }

    public Event(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Event{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}

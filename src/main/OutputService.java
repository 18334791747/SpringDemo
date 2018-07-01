package main;

/**
 * Created by MiaoZhuang on 2016/5/26.
 */
public class OutputService {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void outputMessage() {
        System.out.println(message);
    }
}

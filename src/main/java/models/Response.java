package models;



/**
 * The purpose of the Response class is for the frontend to get data from a response and determine if the operation was
 * successful.  This class was shown by Kevin Childs during his implementation of the grocery list application.
 * Thank you Kevin!
 */
public class Response {
    private String message;
    private Boolean success;
    private Object data;

    public Response() {
    }

    public Response(String message, Boolean success, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}

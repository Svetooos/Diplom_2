package data;

public class BaseResponse {
    private Boolean success;
    private String message;
    private int code;

    public BaseResponse() {
    }

    public BaseResponse(Boolean success) {
        this.success = success;
    }

    public BaseResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

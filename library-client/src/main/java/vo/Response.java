package vo;

import java.util.Map;

public class Response {

    private Map<String, Object> result;

    private String returnCode;

    private String returnMsg;

    public Response() {

    }

    public Response(String returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public Response(Map<String, Object> result, String returnCode, String returnMsg) {
        this.result = result;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}

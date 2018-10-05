package dto;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDTO {

    private int statusCode;
    private String message;
    private String description;
    private String stackTrace;

    public ExceptionDTO(Throwable ex, int code, boolean debug) {
        this.statusCode = code;
        this.message = ex.getMessage();
        this.description = ex.getMessage();
        if (debug) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            this.stackTrace = sw.toString();
        }
    }

    public void setDescription(String desc) {
        this.description = desc;
    }
}

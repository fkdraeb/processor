package pt.hip.soap.response;

import jakarta.xml.bind.annotation.XmlElement;

public class ErrorResponse {
    private String errorID;
    private String errorMessage;

    @XmlElement(name = "ErrorID")
    public String getErrorID() {
        return errorID;
    }

    public void setErrorID(String errorID) {
        this.errorID = errorID;
    }

    @XmlElement(name = "ErrorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
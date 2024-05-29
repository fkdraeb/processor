package pt.hip.soap.response;

import jakarta.xml.bind.annotation.XmlElement;

public class Error {

    private String errorId;
    private String errorMessage;

    @XmlElement(name = "ErrorID")
    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @XmlElement(name = "ErrorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

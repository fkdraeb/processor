package pt.hip.soap.response;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType (propOrder = { "error", "response", "search", "applicationID" })
public class GenericResponse {
    private ErrorResponse errorResponse;
    private String response;
    private String search;
    private String applicationID;

    @XmlElement(name = "Error")
    public ErrorResponse getError() {
        return errorResponse;
    }

    public void setError(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    @XmlElement(name = "Response")
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @XmlElement(name = "Search")
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @XmlElement(name = "ApplicationID")
    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }
}

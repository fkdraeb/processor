package pt.hip.soap.response;

import jakarta.xml.bind.annotation.XmlElement;

public class GenericResponse {

    private Error error;
    private String response;
    private String search;
    private String applicationId;

    @XmlElement(name = "Error")
    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
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
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}

package pt.hip.soap;

import jakarta.annotation.Resource;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "pt.hip.soap.SoapService")
public class SoapServiceImpl implements SoapService {

    private String jdbcUrl = "jdbc:mysql://mysql:3306/HIP";
    private String username = "root";
    private String password = "password";

    @Resource
    WebServiceContext wsctx;

    @Override
    public String acceptMessage(String soapMessage) {

        /**TODO Here should be created the logic to create a new event
         * Should validate the fields on the message
         * Should validate the subscription is active
         * More ...
         * Should return is the event was created successfully, if not should be in the response info
         */



        MessageContext mctx = wsctx.getMessageContext();

        //get detail from request headers
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");

        String username = "";
        String password = "";

        if(userList!=null){
            //get username
            username = userList.get(0).toString();
        }

        if(passList!=null){
            //get password
            password = passList.get(0).toString();
        }

        //Should validate username and password with database
        if (username.equals("pis") && password.equals("pis")){
            return "Hello World JAX-WS - Valid User!";
        }else{
            return "Unknown User!";
        }
/*
        try {
            saveMessageToDatabase(soapMessage);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "The message was acknowledge";*/
    }

    private void saveMessageToDatabase(String message) throws SQLException {
        String sql = "INSERT INTO EVENTS_MESSAGES (EVENT_IN) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, message);
            statement.executeUpdate();
        }
    }

}

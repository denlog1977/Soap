package com.example.soap;

import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.ServiceConnection;
import org.ksoap2.transport.ServiceConnectionSE;

import java.io.IOException;

public class HttpTransportBasicAuthSE extends HttpTransportSE {
    private String username;
    private String password;

    /**
     * Constructor with username and password
     *
     * @param url
     *            The url address of the webservice endpoint
     * @param username
     *            Username for the Basic Authentication challenge RFC 2617
     * @param password
     *            Password for the Basic Authentication challenge RFC 2617
     */
    public HttpTransportBasicAuthSE(String url, String username, String password) {
        super(url);  /// http://kamaz.ddns.net:10100/ut10tfk/ws/ExchangeTFK.1cws?wsdl
        this.username = username;  /// wsChangeServis
        this.password = password;  /// Service2018
    }

    public ServiceConnection getServiceConnection() throws IOException {
        ServiceConnectionSE midpConnection = new ServiceConnectionSE(url);
        addBasicAuthentication(midpConnection);
        return midpConnection;
    }

    protected void addBasicAuthentication(ServiceConnection midpConnection) throws IOException {
        if (username != null && password != null) {
            StringBuffer buf = new StringBuffer(username);
            buf.append(':').append(password);
            byte[] raw = buf.toString().getBytes();
            buf.setLength(0);
            buf.append("Basic ");
            org.kobjects.base64.Base64.encode(raw, 0, raw.length, buf);
            midpConnection.setRequestProperty("Authorization", buf.toString());
        }
    }
}

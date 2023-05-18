package com.kal.leet.com.kal.livy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.auth.login.Configuration;

import sun.security.jgss.GSSCredentialImpl;
import sun.security.jgss.GSSUtil;

public class SpnegoKerberosAuthenticator {

    public static void main(String[] args) throws IOException, LoginException, PrivilegedActionException {
        String serviceUrl = "http://example.com/myrestservice";
        String username = "myuser@MYREALM.COM";
        String password = "mypassword";

        // Set the Kerberos login configuration
        Configuration.setConfiguration(new KerberosLoginConfig());

        // Create a Kerberos login context
        LoginContext lc = new LoginContext("myloginmodule", new KerberosCallbackHandler(username, password));

        // Authenticate to Kerberos
        lc.login();

        // Get the authenticated Subject
        Subject subject = lc.getSubject();

        // Get the GSSCredential for the authenticated Subject
        GSSCredentialImpl credential = Subject.doAs(subject, new PrivilegedExceptionAction<GSSCredentialImpl>() {
            @Override
            public GSSCredentialImpl run() throws Exception {
                return GSSCredentialImpl.getInstance(GSSUtil.getDefaultMechanism(), subject);
            }
        });

        // Create a URL object for the REST service endpoint
        URL url = new URL(serviceUrl);

        // Open a connection to the REST service endpoint
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Set the SPNEGO authentication header using the GSSCredential
        conn.setRequestProperty("Authorization", "Negotiate " + new sun.misc.BASE64Encoder().encode(credential.export()));

        // Send an HTTP GET request to the REST service endpoint
        conn.setRequestMethod("GET");

        // Read the response from the REST service endpoint
        int responseCode = conn.getResponseCode();
        String responseMessage = conn.getResponseMessage();

        // Display the response from the REST service endpoint
        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Message: " + responseMessage);
    }
}

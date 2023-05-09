package com.kal.leet;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class WebHDFSClient {

    private static final String WEBHDFS_ENDPOINT = "http://<HOSTNAME>:<PORT>/webhdfs/v1/";
    private static final String USERNAME = "<USERNAME>";
    private static final String PASSWORD = "<PASSWORD>";

    public static void main(String[] args) throws LoginException, IOException {
        // Login to Kerberos using the login context
        LoginContext lc = new LoginContext("<KRB5_LOGIN_MODULE_NAME>");
        lc.login();
        Subject subject = lc.getSubject();

        // Create a configuration object and set the required properties
        Configuration conf = new Configuration();
        conf.set("hadoop.security.authentication", "kerberos");
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation ugi = UserGroupInformation.getUGIFromSubject(subject);

        // Use the UGI to execute the HTTP request with Kerberos credentials
        ugi.doAs((PrivilegedExceptionAction<Void>) () -> {
            try (CloseableHttpClient httpClient = createHttpClientWithKerberosCredentials()) {
                HttpGet request = new HttpGet(WEBHDFS_ENDPOINT + "path/to/file?op=GETFILESTATUS");
                HttpResponse response = httpClient.execute(request, createHttpClientContext());
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity);
                System.out.println(responseString);
                EntityUtils.consume(entity);
            } catch (IOException | ClientProtocolException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private static CloseableHttpClient createHttpClientWithKerberosCredentials() {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USERNAME, PASSWORD));
        return HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
    }

    private static HttpClientContext createHttpClientContext() {
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(createCredentialsProvider());
        return context;
    }

    private static CredentialsProvider createCredentialsProvider() {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USERNAME, PASSWORD));
        return credsProvider;
    }
}

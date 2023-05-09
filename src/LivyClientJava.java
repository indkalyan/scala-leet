package com.kal.leet;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.security.PrivilegedExceptionAction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.livy.LivyClient;
import org.apache.livy.LivyClientBuilder;
import org.apache.livy.LivyClientException;
import org.apache.livy.client.common.ClientConf;
import org.apache.livy.client.common.HttpClientBuilder;
import org.apache.livy.client.http.HttpClientConfig;
import org.apache.livy.examples.PiJob;

public class LivyKerberosExample {

    public static void main(String[] args) throws Exception {
        final String livyUrl = "http://livyserver:8998";
        final String principal = "livyuser@EXAMPLE.COM";
        final String keytabPath = "/path/to/livyuser.keytab";
        System.setProperty( "java.security.krb5.conf", krbPath);
        SecurityUtil.setSecurityInfoProviders(new AnnotatedSecurityInfo());
        // Set up the Kerberos configuration
        Configuration conf = new Configuration();
        conf.set("hadoop.security.authentication", "kerberos");
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation.loginUserFromKeytab(principal, keytabPath);

        // Create a Livy client with Kerberos authentication
        LivyClientBuilder builder = new LivyClientBuilder()
                .setURI(new URI(livyUrl))
                .setConf(new ClientConf().set("livy.rsc.rpc.server.address", livyUrl))
                .setHttpClientBuilder(new HttpClientBuilder()
                        .setConf(new HttpClientConfig()
                                .set("livy.server.session.timeout", "10m")
                                .set("livy.server.connect.timeout", "1m"))
                        .setAuthenticator(HttpClientBuilder.Authenticator.KERBEROS));

        // Submit a Spark job using the Livy client
        try (LivyClient client = builder.build()) {
            double pi = client.submit(new PiJob(10000)).get();
            System.out.println("Pi is roughly " + pi);
        } catch (LivyClientException e) {
            System.err.println("Failed to create Livy client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

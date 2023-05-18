package com.kal.leet.com.kal.livy;

import java.net.URL;
import java.net.HttpURLConnection;
import java.security.Principal;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import sun.security.jgss.spnego.SpNegoContext;

public class KerberosRestClient {

    private static final String REST_URL = "http://example.com/rest/service";
    private static final String PRINCIPAL_NAME = "user@EXAMPLE.COM";
    private static final String KEYTAB_FILE = "/path/to/keytab/file";

    public static void main(String[] args) {
        try {
            Subject subject = loginWithKeytab();
            URL url = new URL(REST_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // Create GSS context for SPNEGO authentication
            GSSManager manager = GSSManager.getInstance();
            GSSCredential credential = manager.createCredential(null, GSSCredential.DEFAULT_LIFETIME, SpNegoContext.SPNEGO_MECHANISM_OID, GSSCredential.INITIATE_ONLY);
            GSSContext context = manager.createContext(credential);

            // Authenticate with SPNEGO
            byte[] token = new byte[0];
            while (!context.isEstablished()) {
                token = context.initSecContext(token, 0, token.length);
                if (token != null) {
                    conn.setRequestProperty("Authorization", "Negotiate " + new sun.misc.BASE64Encoder().encode(token));
                }
            }

            // Send request
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Handle response
            } else {
                // Handle error
            }
        } catch (Exception e) {
            // Handle exception
        }
    }

    private static Subject loginWithKeytab() throws LoginException, PrivilegedActionException {
        LoginContext loginContext = new LoginContext("", new KerberosCallbackHandler(PRINCIPAL_NAME, KEYTAB_FILE));
        loginContext.login();
        return loginContext.getSubject();
    }

    private static class KerberosCallbackHandler implements javax.security.auth.callback.CallbackHandler {
        private final String principalName;
        private final String keytabFile;

        public KerberosCallbackHandler(String principalName, String keytabFile) {
            this.principalName = principalName;
            this.keytabFile = keytabFile;
        }

        public void handle(javax.security.auth.callback.Callback[] callbacks) {
            for (javax.security.auth.callback.Callback callback : callbacks) {
                if (callback instanceof javax.security.auth.callback.NameCallback) {
                    ((javax.security.auth.callback.NameCallback) callback).setName(principalName);
                } else if (callback instanceof javax.security.auth.callback.PasswordCallback) {
                    // No password needed for keytab authentication
                } else if (callback instanceof javax.security.auth.callback.KeyStoreCallback) {
                    // No key store needed for keytab authentication
                } else if (callback instanceof javax.security.auth.callback.TextOutputCallback) {
                    // Ignore text output callbacks
                } else {
                    throw new UnsupportedCallbackException(callback, "Unsupported callback");
                }
            }
        }
    }
}

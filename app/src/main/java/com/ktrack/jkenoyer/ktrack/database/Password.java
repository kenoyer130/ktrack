package com.ktrack.jkenoyer.ktrack.database;

import android.content.Context;

import com.ktrack.jkenoyer.ktrack.R;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * Created by jkenoyer on 1/11/15.
 */
public class Password {

    public String encrypt(String password) {

        try {
            AesCbcWithIntegrity.SecretKeys key = AesCbcWithIntegrity.keys(Ctx.getText(R.string.key));

            return AesCbcWithIntegrity.encrypt(password, key).toString();

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String decrypt(String password) {
        try {
            AesCbcWithIntegrity.SecretKeys key = AesCbcWithIntegrity.keys(Ctx.getText(R.string.key));

            AesCbcWithIntegrity.CipherTextIvMac cipherTextIvMac = new AesCbcWithIntegrity.CipherTextIvMac(password);

            return AesCbcWithIntegrity.decryptString(cipherTextIvMac, key).toString();

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}

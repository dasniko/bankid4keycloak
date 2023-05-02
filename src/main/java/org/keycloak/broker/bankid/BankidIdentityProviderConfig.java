package org.keycloak.broker.bankid;

import java.security.KeyStore;

import org.keycloak.common.util.KeystoreUtil;
import org.keycloak.models.IdentityProviderModel;

public class BankidIdentityProviderConfig extends IdentityProviderModel {
	/**
	 *
	 */
	private static final long serialVersionUID = 3849007589404817838L;

	private static final String BANKID_APIURL_PROPERTY_NAME = "BANKID_APIURL";
	private static final String BANKID_KEYSTORE_FILE_PROPERTY_NAME = "BANKID_KEYSTORE_FILE";
	private static final String BANKID_KEYSTORE_PASSWORD_PROPERTY_NAME = "BANKID_KEYSTORE_PASSWORD";
	private static final String BANKID_TRUSTSTORE_FILE_PROPERTY_NAME = "BANKID_TRUSTSTORE_FILE";
	private static final String BANKID_TRUSTSTORE_PASSWORD_PROPERTY_NAME = "BANKID_TRUSTSTORE_PASSWORD";
	private static final String BANKID_PRIVATEKEY_PASSWORD_PROPERTY_NAME = "BANKID_PRIVATEKEY_PASSWORD";
	private static final String BANKID_REQUIRE_NIN = "BANKID_REQUIRE_NIN";
	private static final String BANKID_SHOW_QR_CODE = "BANKID_SHOW_QR_CODE";
	private static final String BANKID_SAVE_NIN_HASH = "BANKID_SAVE_NIN_HASH";

	private KeyStore keyStore;
	private KeyStore truststore;

	public BankidIdentityProviderConfig() {
		super();
	}

	public BankidIdentityProviderConfig(IdentityProviderModel model) {
		super(model);
	}

	public String getApiUrl() {
		return System.getenv(BANKID_APIURL_PROPERTY_NAME);
	}

	public KeyStore getKeyStore() throws Exception {
		if ( keyStore == null ) {
			keyStore = KeystoreUtil.loadKeyStore(
					System.getenv(BANKID_KEYSTORE_FILE_PROPERTY_NAME),
					System.getenv().getOrDefault(BANKID_KEYSTORE_PASSWORD_PROPERTY_NAME, "changeit"));
		}
		return keyStore;
	}

	public KeyStore getTrustStore() throws Exception {
		if ( truststore == null ) {
			truststore = KeystoreUtil.loadKeyStore(
					System.getenv(BANKID_TRUSTSTORE_FILE_PROPERTY_NAME),
					System.getenv().getOrDefault(BANKID_TRUSTSTORE_PASSWORD_PROPERTY_NAME, "changeit"));
		}
		return truststore;
	}

	public String getPrivateKeyPassword() {
		return System.getenv(BANKID_PRIVATEKEY_PASSWORD_PROPERTY_NAME);
	}

	public boolean isShowQRCode() {
		return Boolean.parseBoolean(System.getenv().getOrDefault(BANKID_SHOW_QR_CODE, "false"));
	}

	public boolean isRequiredNin() {
		return Boolean.parseBoolean(System.getenv().getOrDefault(BANKID_REQUIRE_NIN, "false"));
	}

	public boolean isSaveNinHashed() {
		return Boolean.parseBoolean(System.getenv().getOrDefault(BANKID_SAVE_NIN_HASH, "false"));
	}
}

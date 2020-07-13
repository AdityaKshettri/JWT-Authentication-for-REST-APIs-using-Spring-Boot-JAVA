package restapi.jwtauthentication.model;

import java.io.Serializable;

public class AuthResponse implements Serializable
{
	private final String jwtToken;

	public AuthResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getToken() {
		return this.jwtToken;
	}
}

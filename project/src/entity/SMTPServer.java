package entity;

public class SMTPServer {
	private String server, authithencation, port;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getAuthithencation() {
		return authithencation;
	}

	public void setAuthithencation(String authithencation) {
		this.authithencation = authithencation;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public SMTPServer(String server, String authithencation, String port) {
		super();
		this.server = server;
		this.authithencation = authithencation;
		this.port = port;
	}
	
	
}

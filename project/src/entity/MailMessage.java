package entity;

public class MailMessage {
	private String from, to, subject, message, path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MailMessage(String from, String to, String subject, String message, String path) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.path = path;
	}
	
	
}
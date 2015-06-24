package jp.gr.java_conf.uzresk.exconv.parser;

public class ConvertRuleParseException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConvertRuleParseException() {
	}

	public ConvertRuleParseException(String message) {
		super(message);
	}

	public ConvertRuleParseException(Throwable cause) {
		super(cause);
	}

	public ConvertRuleParseException(String message, Throwable cause) {
		super(message, cause);
	}

}

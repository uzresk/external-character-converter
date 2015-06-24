package jp.gr.java_conf.uzresk.exconv.parser;

import jp.gr.java_conf.uzresk.exconv.ConvertRuleHolder;

public interface ConvertRuleParser {

	public ConvertRuleHolder parse() throws ConvertRuleParseException;
}

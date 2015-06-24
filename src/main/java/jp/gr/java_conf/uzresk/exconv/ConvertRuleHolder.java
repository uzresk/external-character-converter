package jp.gr.java_conf.uzresk.exconv;

import java.util.HashMap;
import java.util.Map;

public class ConvertRuleHolder {

	private String inputFile = null;

	private String inputFileCharasetName = null;

	private String outputFile = null;

	private String outputFileCharasetName = null;

	private Map<String, String> mappings = new HashMap<String, String>();

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getInputFileCharasetName() {
		return inputFileCharasetName;
	}

	public void setInputFileCharasetName(String inputFileCharasetName) {
		this.inputFileCharasetName = inputFileCharasetName;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getOutputFileCharasetName() {
		return outputFileCharasetName;
	}

	public void setOutputFileCharasetName(String outputFileCharasetName) {
		this.outputFileCharasetName = outputFileCharasetName;
	}

	public Map<String, String> getMappings() {
		return mappings;
	}

	public void setMappings(Map<String, String> mappings) {
		this.mappings = mappings;
	}

}

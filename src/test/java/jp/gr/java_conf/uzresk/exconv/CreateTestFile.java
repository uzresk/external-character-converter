package jp.gr.java_conf.uzresk.exconv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import jp.gr.java_conf.uzresk.exconv.parser.ConvertRuleParseException;
import jp.gr.java_conf.uzresk.exconv.parser.ConvertRuleParser;
import jp.gr.java_conf.uzresk.exconv.parser.XmlConvertRuleParser;

public class CreateTestFile {

	public static void main(String[] args) throws Exception {
		new CreateTestFile().createFile();

		System.out.println("create file.");
	}

	public void createFile() {

		ConvertRuleParser parser = new XmlConvertRuleParser();
		ConvertRuleHolder holder = null;

		try {
			holder = parser.parse();
		} catch (ConvertRuleParseException e) {
			System.err.println(e);
			System.exit(1);
		}
		File file = new File("/test.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Map<String, String> mappings = holder.getMappings();
			for (Iterator<String> ite = mappings.keySet().iterator(); ite.hasNext();) {
				String inputHexStr = ite.next();

				System.out.println(inputHexStr);
				byte[] bytes = DatatypeConverter.parseHexBinary(inputHexStr);
				fos.write(bytes);
				fos.write(new String("\r\n").getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("io exception");
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				// nothing todo
			}
		}
	}

	public static byte[] fromInt(int value) {
		int arraySize = Integer.SIZE / Byte.SIZE;
		ByteBuffer buffer = ByteBuffer.allocate(arraySize);
		return buffer.putInt(value).array();
	}

}

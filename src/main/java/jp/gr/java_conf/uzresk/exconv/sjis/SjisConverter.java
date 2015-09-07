package jp.gr.java_conf.uzresk.exconv.sjis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;

import javax.xml.bind.DatatypeConverter;

import jp.gr.java_conf.uzresk.exconv.ConvertRuleHolder;
import jp.gr.java_conf.uzresk.exconv.parser.ConvertRuleParseException;
import jp.gr.java_conf.uzresk.exconv.parser.ConvertRuleParser;
import jp.gr.java_conf.uzresk.exconv.parser.XmlConvertRuleParser;

public class SjisConverter {

	public static void main(String[] args) {

		ConvertRuleParser parser = new XmlConvertRuleParser();
		ConvertRuleHolder holder = null;

		try {
			holder = parser.parse();
		} catch (ConvertRuleParseException e) {
			System.err.println(e);
			System.exit(1);
		}

		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			File file = new File(holder.getInputFile());
			br = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), holder.getInputFileCharasetName()));

			int ch = br.read();

			FileOutputStream fos = new FileOutputStream(new File(holder.getOutputFile()));
			bw = new BufferedWriter(new OutputStreamWriter(fos, holder.getOutputFileCharasetName()));

			while (ch != -1) {

				int target = getSJISByte((char) ch, holder);
				String targetHexStr = Integer.toHexString(target).toUpperCase();
				if (holder.getMappings().containsKey(targetHexStr)) {

					// SJISの16進数
					String destHexStr = holder.getMappings().get(targetHexStr);
					byte[] destBytes = DatatypeConverter.parseHexBinary(destHexStr);
					bw.write(new String(destBytes, holder.getOutputFileCharasetName()));

				} else {
					bw.write(ch);
				}
				ch = br.read();
			}
		} catch (Exception e) {
			System.err.println(e);
			System.exit(2);
		} finally {
			try {
				bw.flush();
				br.close();
				bw.close();
			} catch (IOException e) {
				System.err.println(e);
				System.exit(2);
			}
		}
		System.out.println("Successful completion.");
		System.exit(0);
	}

	public static byte[] fromInt(int value) {
		int arraySize = Integer.SIZE / Byte.SIZE;
		ByteBuffer buffer = ByteBuffer.allocate(arraySize);
		return buffer.putInt(value).array();
	}

	private static int getSJISByte(final char c, final ConvertRuleHolder holder) throws Exception {

		byte[] bArray = String.valueOf(c).getBytes(holder.getInputFileCharasetName());

		int targetChar;
		if (bArray.length == 1) {
			targetChar = bArray[0] & 0xFF;

		} else {
			targetChar = ((bArray[0] & 0xFF) << 8) | (bArray[1] & 0xFF);
		}
		return targetChar;

	}
}

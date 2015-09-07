package jp.gr.java_conf.uzresk.exconv.parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jp.gr.java_conf.uzresk.exconv.ConvertRuleHolder;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlConvertRuleParser implements ConvertRuleParser {

	public ConvertRuleHolder parse() throws ConvertRuleParseException {

		String path = System.getProperty("path");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		Document document;
		Element root = null;
		try {
			documentBuilder = factory.newDocumentBuilder();
			document = documentBuilder.parse(path);
			root = document.getDocumentElement();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConvertRuleParseException("convertRule.xml is illegal.");
		}

		NodeList rootChildren = root.getChildNodes();
		ConvertRuleHolder holder = parse(rootChildren);

		return holder;

	}

	private ConvertRuleHolder parse(NodeList rootChildren)
			throws ConvertRuleParseException {

		ConvertRuleHolder holder = new ConvertRuleHolder();

		for (int i = 0; i < rootChildren.getLength(); i++) {
			Node node = rootChildren.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				if ("input".equals(node.getNodeName())) {
					String inputFile = element.getAttribute("file");
					String inputFileCharasetName = element
							.getAttribute("charasetName");

					if (StringUtils.isEmpty(inputFile)
							|| StringUtils.isEmpty(inputFileCharasetName)) {
						throw new ConvertRuleParseException(
								"<input> file or charaset is empty.");
					}

					holder.setInputFile(inputFile);
					holder.setInputFileCharasetName(inputFileCharasetName);

				} else if ("output".equals(node.getNodeName())) {
					String outputFile = element.getAttribute("file");
					String outputFileCharasetName = element
							.getAttribute("charasetName");

					if (StringUtils.isEmpty(outputFile)
							|| StringUtils.isEmpty(outputFileCharasetName)) {
						throw new ConvertRuleParseException(
								"<output> file or charaset is empty.");
					}

					holder.setOutputFile(outputFile);
					holder.setOutputFileCharasetName(outputFileCharasetName);

				} else if ("mappings".equals(node.getNodeName())) {

					NodeList nodeList = node.getChildNodes();

					for (int j = 0; j < nodeList.getLength(); j++) {
						Node mappingNode = nodeList.item(j);
						if (mappingNode.getNodeType() == Node.ELEMENT_NODE) {

							Element el = (Element) mappingNode;

							String src = el.getAttribute("src");
							String dest = el.getAttribute("dest");

							if (StringUtils.isEmpty(src)
									|| StringUtils.isEmpty(dest)) {
								throw new ConvertRuleParseException(
										"<mapping> src or dest is empty.");
							}

							holder.getMappings().put(src, dest);
						}
					}
				}
			}
		}
		return holder;
	}
}

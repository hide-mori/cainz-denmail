
package com.tcs.denmail.common.msg.builder.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.tcs.denmail.common.msg.MsgProperty;
import com.tcs.denmail.common.msg.MsgStd;
import com.tcs.denmail.common.msg.builder.MsgInfoBuilder;
import com.tcs.denmail.common.msg.exception.MsgIdNotFoundException;
import com.tcs.denmail.common.msg.exception.MsgInitializeException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XMLMsgInfoBuilder implements MsgInfoBuilder {
    private HashMap msgInfoMap;

    public void initialize() throws MsgInitializeException {
        if (this.msgInfoMap == null) {
            this.msgInfoMap = new HashMap<Object, Object>();
        }

        try {
            String msgInfoFileName = MsgProperty.getProperty("MSG_INFO_FILE_NAME");

            String[] msgInfoFiles = msgInfoFileName.trim().split("\\s*,\\s*");

            for (int i = 0; i < msgInfoFiles.length; i++) {
                build(msgInfoFiles[i]);
            }
        } catch (Exception e) {
            MsgStd.err(e);
            new MsgInitializeException("メッセージ情報ファイルの解析中にエラーが発生しました。");
        }
    }

    public Map getMsgInfo(String msgId) throws MsgIdNotFoundException {
        Map msgInfo = (Map) this.msgInfoMap.get(msgId);

        if (msgInfo != null) {
            return msgInfo;
        }

        throw new MsgIdNotFoundException(msgId);
    }

    private void build(String filePath) throws MsgInitializeException {
        Document doc = parseXML(filePath);

        NodeList msgInfoNodeList = doc.getElementsByTagName("msg-info");

        NamedNodeMap attributeList = null;

        NodeList childNodeList = null;

        for (int i = 0; i < msgInfoNodeList.getLength(); i++) {
            Map<Object, Object> msgInfo = new HashMap<Object, Object>();

            attributeList = msgInfoNodeList.item(i).getAttributes();
            for (int j = 0; j < attributeList.getLength(); j++) {
                String key = attributeList.item(j).getNodeName();
                String value = attributeList.item(j).getNodeValue();

                if ("content".equals(key)) {
                    Map<String, String> contents = new HashMap<String, String>();
                    contents.put("", value);
                    msgInfo.put("content", contents);
                } else {
                    msgInfo.put(key, value);
                }
            }

            childNodeList = msgInfoNodeList.item(i).getChildNodes();
            for (int k = 0; k < childNodeList.getLength(); k++) {
                Node childNode = childNodeList.item(k);
                if (childNode.getFirstChild() != null) {
                    String childKey = childNode.getNodeName();
                    if ("content".equals(childKey)) {
                        buildupContentsMap(msgInfo, childNode);
                    } else {
                        buildupElementList(msgInfo, childNode);
                    }
                }
            }

            if (msgInfo.containsKey("id")) {
                this.msgInfoMap.put(msgInfo.get("id"), msgInfo);
            } else {
                throw new MsgInitializeException("メッセージIDが定義されていません");
            }
        }
    }

    private void buildupContentsMap(Map<Object, Object> msgInfo, Node contentNode) {
        Map<String, String> contents;
        Node localeAttribute = findLocaleAttribute(contentNode);
        String locale = (localeAttribute != null) ? localeAttribute.getNodeValue() : "";
        String value = contentNode.getFirstChild().getNodeValue();

        if (msgInfo.containsKey("content")) {
            contents = (Map<String, String>) msgInfo.get("content");
        } else {
            contents = new HashMap<String, String>();
            msgInfo.put("content", contents);
        }

        contents.put(locale, value);
    }

    private Node findLocaleAttribute(Node contentNode) {
        NamedNodeMap attributes = contentNode.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            if ("locale".equals(attributes.item(i).getNodeName())) {
                return attributes.item(i);
            }
        }
        return null;
    }

    private void buildupElementList(Map<Object, Object> msgInfo, Node node) {
        List<String> values;
        String name = node.getNodeName();
        String value = node.getFirstChild().getNodeValue();

        if (msgInfo.containsKey(name)) {
            values = (List<String>) msgInfo.get(name);
        } else {
            values = new ArrayList<String>();
            msgInfo.put(name, values);
        }

        values.add(value);
    }

    private Document parseXML(String filePath) throws MsgInitializeException {
        InputStream inputStream = null;
        Document result = null;

        try {
            inputStream = XMLMsgInfoBuilder.class.getClassLoader().getResourceAsStream(filePath);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();

            xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
            xmlReader.setFeature("http://xml.org/sax/features/validation", true);
            xmlReader.setErrorHandler(new XMLMsgInfoErrorHandler());

            InputSource inputSource = new InputSource(inputStream);
            xmlReader.parse(inputSource);

            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            dbfactory.setValidating(false);
            DocumentBuilder builder = dbfactory.newDocumentBuilder();
            builder.setErrorHandler(new XMLMsgInfoErrorHandler());
            inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

            result = builder.parse(inputStream);

        } catch (ParserConfigurationException e) {
            MsgStd.err(e);
            throw new MsgInitializeException("メッセージ情報ファイルの解析中にエラーが発生しました。", e);

        } catch (SAXException e) {
            MsgStd.err(e);
            throw new MsgInitializeException("メッセージ情報ファイルの解析中にエラーが発生しました。", e);

        } catch (IOException e) {
            MsgStd.err(e);
            throw new MsgInitializeException("メッセージ情報ファイルの解析中にエラーが発生しました。", e);
        } finally {

            try {

                if (inputStream != null) {
                    inputStream.close();

                }
            } catch (IOException e) {
                MsgStd.err(e);
            }
        }
        return result;
    }
}

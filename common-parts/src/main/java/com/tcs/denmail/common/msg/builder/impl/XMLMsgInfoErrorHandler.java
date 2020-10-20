package com.tcs.denmail.common.msg.builder.impl;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLMsgInfoErrorHandler extends DefaultHandler {
  public void warning(SAXParseException e) throws SAXException {
    throw new SAXException(e);
  }

  public void error(SAXParseException e) throws SAXException {
    throw new SAXException(e);
  }

  public void fatalError(SAXParseException e) throws SAXException {
    super.fatalError(e);
  }
}

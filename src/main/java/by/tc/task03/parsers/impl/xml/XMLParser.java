package by.tc.task03.parsers.impl.xml;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.tc.task03.entities.Attribute;
import by.tc.task03.entities.Element;
import by.tc.task03.parsers.Parser;

public class XMLParser implements Parser {
    private BufferedReader reader;
    private Element element;

    public XMLParser(BufferedReader reader) {
        this.reader = reader;
    }

    private String read() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            contentBuilder.append(line).append("\n");
        }
        reader.close();
        return contentBuilder.toString();
    }

    private void parseAttributes(Element elem, String attributeString) {
        Matcher attributeMatcher =
                Pattern.compile(XMLConstants.ATTRIBUTE_PATTERN).matcher(attributeString);
        while (attributeMatcher.find()) {
            elem.addAttribute(
                    new Attribute(attributeMatcher.group(XMLConstants.ATTRIBUTE_NAME_GROUP_NAME),
                            attributeMatcher.group(XMLConstants.ATTRIBUTE_VALUE_GROUP_NAME)));
        }
    }

    public Element parse() throws IOException {
        if (element == null) {
            LinkedList<Element> stack = new LinkedList<Element>();
            stack.add(new Element("pseudo-root"));
            Pattern pattern = Pattern.compile(XMLConstants.TAG_PATTERN);
            String content = read();
            Matcher matcher = pattern.matcher(content);
            boolean isSelfClosingTag;
            boolean isEndOfElement;
            String attributeString;
            int index = 0;
            while (matcher.find()) {
                isSelfClosingTag =
                        (matcher.group(XMLConstants.SELF_CLOSING_SLASH_GROUP_NAME) != null);
                isEndOfElement = (matcher.group(XMLConstants.CLOSING_SLASH_GROUP_NAME) != null);
                if (isSelfClosingTag || !isEndOfElement) {
                    Element elem = new Element(matcher.group(XMLConstants.TAG_NAME_GROUP_NAME));
                    index = matcher.end();
                    attributeString = matcher.group(XMLConstants.ATTRIBUTES_GROUP_NAME);
                    parseAttributes(elem, attributeString);
                    stack.getLast().addChildElement(elem);
                    if (!isSelfClosingTag) {
                        stack.add(elem);
                    }
                } else {
                    Element removedElement = stack.removeLast();
                    if (removedElement.getChildElements().size() == 0) {
                        removedElement.setContent(content.substring(index, matcher.start()).trim());
                    }
                }
            }
            element = stack.getLast().getChildElements().get(0);
        }
        return element;
    }

    public void setReader(BufferedReader reader) throws IOException {
        this.reader.close();
        element = null;
        this.reader = reader;
    }
}

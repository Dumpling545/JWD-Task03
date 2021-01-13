package by.tc.task03.entities;

import java.util.ArrayList;
import java.util.List;

public class Element {
    private String name;
    private List<Attribute> attributes;
    private List<Element> childElements;
    private String content;

    public Element(String name) {
        attributes = new ArrayList<Attribute>();
        childElements = new ArrayList<Element>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public List<Element> getChildElements() {
        return childElements;
    }

    public void addChildElement(Element childElement) {
        childElements.add(childElement);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

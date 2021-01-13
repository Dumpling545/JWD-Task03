package by.tc.task03.formatters;


import java.util.LinkedList;
import by.tc.task03.entities.Attribute;
import by.tc.task03.entities.Element;

class ElementFormattingItem {
    private Element element;
    private int depth;
    private int localId;

    public ElementFormattingItem(int localId, Element element, int depth) {
        this.element = element;
        this.depth = depth;
        this.localId = localId;
    }

    public Element getElement() {
        return element;
    }

    public int getDepth() {
        return depth;
    }

    public int getLocalId() {
        return localId;
    }
}


public class ElementFormatter {
    private static StringBuilder buildFormattedString(Element element) {
        StringBuilder base = new StringBuilder();
        LinkedList<ElementFormattingItem> stack = new LinkedList<ElementFormattingItem>();
        stack.add(new ElementFormattingItem(0, element, 0));
        StringBuilder tabulation = new StringBuilder();
        while (!stack.isEmpty()) {
            ElementFormattingItem elemFormattingItem = stack.removeLast();
            Element elem = elemFormattingItem.getElement();
            for (int i = tabulation.length(); i > elemFormattingItem.getDepth(); i--) {
                tabulation.deleteCharAt(0);
            }
            for (int i = tabulation.length(); i <= elemFormattingItem.getDepth(); i++) {
                tabulation.append('\t');
            }
            base.append('\n').append(tabulation).append(elemFormattingItem.getLocalId())
                    .append(". Element name: ").append(elem.getName());
            if (elem.getAttributes().size() > 0) {
                base.append('\n').append(tabulation).append("Attributes:");
                tabulation.append('\t');
                for (Attribute attribute : elem.getAttributes()) {
                    base.append('\n').append(tabulation).append(attribute.getName()).append(" = ")
                            .append(attribute.getValue());
                }
                tabulation.deleteCharAt(0);
            }
            if (elem.getContent() != null) {
                base.append('\n').append(tabulation).append("Content: ").append(elem.getContent());
            }
            if (elem.getChildElements().size() > 0) {
                base.append('\n').append(tabulation).append("Child elements:");
                for (int i = elem.getChildElements().size() - 1; i >= 0; i--) {
                    stack.add(new ElementFormattingItem(i, elem.getChildElements().get(i),
                            elemFormattingItem.getDepth() + 1));
                }
            }
        }

        return base;
    }

    public static String getFormattedString(Element element) {
        return buildFormattedString(element).toString();
    }
}

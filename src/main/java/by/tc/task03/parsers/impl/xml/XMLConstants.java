package by.tc.task03.parsers.impl.xml;


import java.util.regex.Pattern;

public final class XMLConstants {
  // regex group names
  public static final String SELF_CLOSING_SLASH_GROUP_NAME = "selfClosingSlash";
  public static final String CLOSING_SLASH_GROUP_NAME = "closingSlash";
  public static final String QUOTE_OF_ATTRIBUTE_VALUE_GROUP_NAME = "attrQuote";
  public static final String TAG_NAME_GROUP_NAME = "tagName";
  public static final String TAG_CONTENT_GROUP_NAME = "tagContent";
  public static final String ATTRIBUTES_GROUP_NAME = "attrs";
  public static final String ATTRIBUTE_NAME_GROUP_NAME = "attrName";
  public static final String ATTRIBUTE_VALUE_GROUP_NAME = "attrValue";
  // regex subpatterns
  public static final String SELF_CLOSING_SLASH_PATTERN =
      "(?<" + SELF_CLOSING_SLASH_GROUP_NAME + ">\\s*\\/)?";
  public static final String OPENING_QUOTE_PATTERN = "[\"\']";
  public static final String WORD_PATTERN = "[a-zA-Z_:][\\w:\\-\\.]*";
  public static final String ATTRIBUTE_CONTENT_PATTERN = ".*?";
  public static final String TAG_CONTENT_PATTERN = "(?<" + TAG_CONTENT_GROUP_NAME + ">.*?)";
  public static final String ATTRIBUTE_PATTERN = "(\\s+(?<" + ATTRIBUTE_NAME_GROUP_NAME
      + ">" + WORD_PATTERN + ")\\s*=\\s*(?<" + QUOTE_OF_ATTRIBUTE_VALUE_GROUP_NAME + ">"
      + OPENING_QUOTE_PATTERN + ")(?<" + ATTRIBUTE_VALUE_GROUP_NAME + ">"
      + ATTRIBUTE_CONTENT_PATTERN + ")\\k<" + QUOTE_OF_ATTRIBUTE_VALUE_GROUP_NAME + ">)";
  public static final String ATTRIBUTES_PATTERN =
      "(?<" + ATTRIBUTES_GROUP_NAME + ">" + ATTRIBUTE_PATTERN + "*)";
  public static final String TAG_PATTERN =
      "<(?<" + CLOSING_SLASH_GROUP_NAME + ">\\/)?(?<" + TAG_NAME_GROUP_NAME + ">"
          + WORD_PATTERN + ")" + ATTRIBUTES_PATTERN + SELF_CLOSING_SLASH_PATTERN + ">";
}

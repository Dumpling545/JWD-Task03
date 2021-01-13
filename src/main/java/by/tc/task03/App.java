package by.tc.task03;

import java.io.File;
import java.io.IOException;
import by.tc.task03.entities.Element;
import by.tc.task03.formatters.ElementFormatter;
import by.tc.task03.parsers.Parser;
import by.tc.task03.parsers.ParserFactory;

public class App {
    public static void main(String[] args) throws IOException {
        ParserFactory parserFactory = new ParserFactory();
        Object source = new File("pom.xml");
        Parser parser = parserFactory.create(source);
        Element element = parser.parse();
        String formattedOutput = ElementFormatter.getFormattedString(element);
        System.out.println(formattedOutput);
    }
}

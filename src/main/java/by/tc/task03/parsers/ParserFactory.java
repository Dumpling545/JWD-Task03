package by.tc.task03.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import by.tc.task03.parsers.impl.xml.XMLParser;

public class ParserFactory {
    public Parser create(Object source) throws FileNotFoundException {
        Parser parser = null;
        if (source instanceof File) {
            File file = (File) source;
            String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            switch (extension) {
                case "xml":
                    parser = new XMLParser(new BufferedReader(new FileReader(file)));
                    break;
            }
        }
        return parser;
    }
}

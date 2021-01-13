package by.tc.task03.parsers;

import java.io.IOException;
import by.tc.task03.entities.Element;

public interface Parser {
    public Element parse() throws IOException;
}

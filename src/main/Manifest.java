package main;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Manifest
{
    private static final String FILE_CONFIGURATION = "/WEB-INF/ConfigurationSHS.xml";
    public static String FILE_BLINK;

    static String PATH_BLINK;
    public static int TIME_WORKER;
    static boolean START_WORKER = false;
    public static String API_PASSWORD;
    public static String FILE_ACTIONS;
    public static String FILE_SENSORS;
    public static String FILE_TASKS;
    public static String FILE_REGISTERED_PINS;

    static boolean isParsed = false;

    static void parseDefaultConfiguration(ServletContext context) throws ParserConfigurationException, SAXException, IOException {
        final Document document = getDefaultDocument(context);

        API_PASSWORD = document.getElementsByTagName("apiPassword").item(0).getTextContent();
        Element blink = (Element) document.getElementsByTagName("blink").item(0);
        PATH_BLINK = blink.getElementsByTagName("path").item(0).getTextContent();
        FILE_BLINK = blink.getElementsByTagName("file").item(0).getTextContent();

        Element worker = (Element) document.getElementsByTagName("worker").item(0);
        TIME_WORKER = Integer.parseInt(worker.getElementsByTagName("runEvery").item(0).getTextContent());
        START_WORKER = worker.getElementsByTagName("start").item(0).getTextContent().equals("true");
        Element filePaths = (Element) document.getElementsByTagName("files").item(0);
        FILE_ACTIONS = filePaths.getElementsByTagName("actions").item(0).getTextContent();
        FILE_SENSORS = filePaths.getElementsByTagName("sensors").item(0).getTextContent();
        FILE_TASKS = filePaths.getElementsByTagName("tasks").item(0).getTextContent();
        FILE_REGISTERED_PINS = filePaths.getElementsByTagName("registeredPins").item(0).getTextContent();

        isParsed = true;
    }

    private static Document getDefaultDocument(ServletContext context) throws IOException, SAXException, ParserConfigurationException {
        File file = new File(context.getRealPath(FILE_CONFIGURATION));
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        document.getDocumentElement().normalize();
        return document;
    }
}

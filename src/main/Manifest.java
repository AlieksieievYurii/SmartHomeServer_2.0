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

    public static int TIME_WORKER;
    public static boolean START_WORKER = false;
    public static String PASSWORD_MANAGER_DEVICE;

    public static String PASSWORD_POST_ACTION;
    public static String PASSWORD_POST_TASK;
    public static String PATH_BLINK;
    public static String FILE_ACTIONS;
    public static String FILE_SENSORS;
    public static String FILE_TASKS;

    public static void parseForListenerTask(ServletContext context) throws ParserConfigurationException, IOException, SAXException {
        final Document document = getDefaultDocument(context);

        Element apiKeys = (Element) document.getElementsByTagName("apiKeys").item(0);
        PASSWORD_POST_ACTION= apiKeys.getElementsByTagName("ListenerTaskPostAction").item(0).getTextContent();
        PASSWORD_POST_TASK= apiKeys.getElementsByTagName("ListenerTaskPostTask").item(0).getTextContent();


        PATH_BLINK = document.getElementsByTagName("blink").item(0).getTextContent();

        Element filePaths = (Element) document.getElementsByTagName("files").item(0);
        FILE_ACTIONS = filePaths.getElementsByTagName("actions").item(0).getTextContent();
        FILE_SENSORS = filePaths.getElementsByTagName("sensors").item(0).getTextContent();
        FILE_TASKS = filePaths.getElementsByTagName("tasks").item(0).getTextContent();

    }

    public static void configForManagerDevices(ServletContext context) throws ParserConfigurationException, SAXException, IOException {
        final Document document = getDefaultDocument(context);

        Element worker = (Element) document.getElementsByTagName("worker").item(0);
        TIME_WORKER = Integer.parseInt(worker.getElementsByTagName("runEvery").item(0).getTextContent());
        String startWorker = worker.getElementsByTagName("start").item(0).getTextContent();
        START_WORKER = startWorker.equals("true");

        Element apiKeys = (Element) document.getElementsByTagName("apiKeys").item(0);
        PASSWORD_MANAGER_DEVICE = apiKeys.getElementsByTagName("ManagerDevices").item(0).getTextContent();

        Element filePaths = (Element) document.getElementsByTagName("files").item(0);


        if(FILE_ACTIONS == null)
            FILE_ACTIONS = filePaths.getElementsByTagName("actions").item(0).getTextContent();
        if(FILE_SENSORS == null)
            FILE_SENSORS = filePaths.getElementsByTagName("sensors").item(0).getTextContent();
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

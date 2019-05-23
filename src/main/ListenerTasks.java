package main;

import controllers.ListenerTasks.ControllerPosting;
import request.RequestTypeUtils;
import utils.errors.ErrorLogs;
import exceptions.TypeRequestException;
import org.xml.sax.SAXException;
import controllers.ListenerTasks.ControllerGetting;
import request.post.ResponseStatus;
import interfaces.iResponseStatus;
import utils.PasswordUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "ListenerTasks")
public class ListenerTasks extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        if (!Manifest.isParsed) {
            try {
                Manifest.parseDefaultConfiguration(getServletContext());
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
                destroy();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //executeBlinkBash();
        try {
            if (PasswordUtils.isApiPasswordCorrect(request))
            {
                final ControllerPosting controllerPosting =
                       ControllerPosting.build(getServletContext(),request,response);
                controllerPosting.executeFor(RequestTypeUtils.whatTypeRequest(request));
            }
            else
                wrongPassword(response);
        } catch (TypeRequestException e) {
            e.printStackTrace();
            wrongRequest(response);
        }
    }

    private void executeBlinkBash() {
        String os = System.getProperty("os.name").toLowerCase();
        String executor;
        if (os.contains("win"))
            executor = "cmd.exe /c start";
        else if (os.contains("nix") || os.contains("nux") || os.contains("aix"))
            executor = "/usr/bin/python";
        else
            return;

        try {
            Runtime.getRuntime().exec(executor + " " + Manifest.FILE_BLINK, null, new File(Manifest.PATH_BLINK));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        final ControllerGetting controllerGetting = ControllerGetting.build(getServletContext(), response);
        try {
            controllerGetting.executeFor(RequestTypeUtils.whatTypeRequest(request));
        } catch (TypeRequestException e) {
            e.printStackTrace();
            wrongRequest(response);
        }
    }

    private static void wrongPassword(HttpServletResponse response) {
        final iResponseStatus res = new ResponseStatus(response);
        res.responseWRONG("Wrong password!");
    }

    private void wrongRequest(HttpServletResponse response) {
        ErrorLogs.errorOfTypeRequest();
        final iResponseStatus resp = new ResponseStatus(response);
        resp.responseWRONG("wrong request");
    }
}

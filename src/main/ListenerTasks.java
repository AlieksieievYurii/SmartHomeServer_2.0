package main;

import controllers.request.RequestTypeUtils;
import controllers.errors.ErrorLogs;
import exceptions.TypeRequestException;
import org.xml.sax.SAXException;
import request.get.ControllerGetting;
import request.get.FactoryControllerGetting;
import request.post.Factory;
import request.post.ControllerPOSTAction;
import request.post.Response;
import request.post.interfaises.iResponse;
import request.post.task.ControllerPOSTTask;
import request.post.task.ControllerRemovingTask;
import utils.password.PasswordUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@WebServlet(name = "ListenerTasks")
public class ListenerTasks extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Manifest.parseForListenerTask(getServletContext());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            destroy();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
       /* try {
            Runtime.getRuntime().exec("cmd.exe /c start kek.bat",null,new File("C:\\"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            whatPost(request, response);
        } catch (TypeRequestException e) {
            e.printStackTrace();
            wrongRequest(response);
        }
    }

    private void whatPost(HttpServletRequest request, HttpServletResponse response) throws TypeRequestException {
        switch (RequestTypeUtils.whatTypeRequest(request)) {
            case POST_TASK:
                if(PasswordUtils.isPasswordPostTaskCorrect(request))
                    postTask(request, response);
                else
                    wrongPassword(response);
                break;

            case POST_ACTION:
                if(PasswordUtils.isPasswordPostActionCorrect(request))
                    postAction(request, response);
                else
                    wrongPassword(response);
                break;

            case DELETE_REMOVE_TASK:
                if(PasswordUtils.isPasswordPostTaskCorrect(request))
                    removeTask(request, response);
                else
                    wrongPassword(response);
                break;

            default:
                throw new TypeRequestException("Wrong type request for POST");
        }
    }

    private static void wrongPassword(HttpServletResponse response) {
        final iResponse res = new Response(response);
        res.responseWRONG("Wrong password!");
    }

    private void removeTask(HttpServletRequest request, HttpServletResponse response) {
        final ControllerRemovingTask controllerRemovingTask =
                ControllerRemovingTask.build(getServletContext(), response);
        controllerRemovingTask.execute(request);
    }

    private void wrongRequest(HttpServletResponse response) {
        ErrorLogs.errorOfTypeRequest();
        final iResponse resp = new Response(response);
        resp.responseWRONG("wrong request");
    }

    private void postTask(HttpServletRequest request, HttpServletResponse response) {
        final ControllerPOSTTask controllerPOSTTask = ControllerPOSTTask.build(getServletContext(), response);
        controllerPOSTTask.execute(request);
    }

    private void postAction(HttpServletRequest request, HttpServletResponse response) {
        final ControllerPOSTAction postControllerTCODAction =
                Factory.build(
                        getServletContext(),
                        request,
                        response);

        postControllerTCODAction.execute();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        final ControllerGetting controllerGetting = FactoryControllerGetting.build(getServletContext(), response);
        try {
            controllerGetting.executeFor(RequestTypeUtils.whatTypeRequest(request));
        } catch (TypeRequestException e) {
            e.printStackTrace();
            wrongRequest(response);
        }
    }
}

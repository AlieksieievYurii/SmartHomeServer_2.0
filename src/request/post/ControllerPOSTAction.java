package request.post;

import components.action.Action;
import exceptions.ActionException;
import interfaces.iRequest;
import interfaces.iResponseStatus;
import interfaces.iWriteAction;

public class ControllerPOSTAction {
    private final interfaces.iRequest iRequest;
    private final interfaces.iWriteAction iWriteAction;
    private final iResponseStatus iResponseStatus;

    ControllerPOSTAction(iRequest iRequest,
                                iWriteAction iWriteAction,
                                iResponseStatus iResponseStatus) {
        this.iRequest = iRequest;
        this.iWriteAction = iWriteAction;
        this.iResponseStatus = iResponseStatus;
    }

    public void execute() {
        final Action action;
        try {
             action = iRequest.getAction();
        } catch (ActionException e) {
            iResponseStatus.responseWRONG(e.getMessage());
            return;
        }

        if (iWriteAction.write(action))
            iResponseStatus.responseOk();
        else
            iResponseStatus.responseWRONG("Error with writing Action");
    }
}

package request.post;

import action.Action;
import exceptions.ActionException;
import request.post.interfaises.iRequest;
import request.post.interfaises.iResponse;
import request.post.interfaises.iWriteAction;

public class ControllerPOSTAction {
    private final request.post.interfaises.iRequest iRequest;
    private final request.post.interfaises.iWriteAction iWriteAction;
    private final request.post.interfaises.iResponse iResponse;

    ControllerPOSTAction(iRequest iRequest,
                                iWriteAction iWriteAction,
                                iResponse iResponse) {
        this.iRequest = iRequest;
        this.iWriteAction = iWriteAction;
        this.iResponse = iResponse;
    }

    public void execute() {
        final Action action;
        try {
             action = iRequest.getAction();
        } catch (ActionException e) {
            iResponse.responseWRONG(e.getMessage());
            return;
        }

        if (iWriteAction.write(action))
            iResponse.responseOk();
        else
            iResponse.responseWRONG("Error with writing Action");
    }
}

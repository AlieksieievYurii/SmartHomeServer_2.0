package request.post;

import action.Action;
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
        final Action action = iRequest.getAction();

        if (action == null) {
            iResponse.responseWRONG();
            return;
        }

        if (iWriteAction.write(action))
            iResponse.responseOk();
        else
            iResponse.responseWRONG();

    }
}

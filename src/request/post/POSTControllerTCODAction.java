package request.post;

import action.Action;
import request.post.interfaises.iRequest;
import request.post.interfaises.iResponse;
import request.post.interfaises.iWriteAction;

public class POSTControllerTCODAction
{
    private final request.post.interfaises.iRequest iRequest;
    private final request.post.interfaises.iWriteAction iWriteAction;
    private final request.post.interfaises.iResponse iResponse;

    public POSTControllerTCODAction(iRequest iRequest,
                                    iWriteAction iWriteAction,
                                    iResponse iResponse) {
        this.iRequest = iRequest;
        this.iWriteAction = iWriteAction;
        this.iResponse = iResponse;
    }

    public void execute()
    {
        final Action action = iRequest.getAction();

        if(iWriteAction.write(action))
            iResponse.responseOk();
        else
            iResponse.responseWRONG();

    }
}

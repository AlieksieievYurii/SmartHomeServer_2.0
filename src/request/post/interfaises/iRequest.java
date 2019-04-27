package request.post.interfaises;

import action.Action;
import exceptions.*;

public interface iRequest
{
    Action getAction() throws ActionException;
}

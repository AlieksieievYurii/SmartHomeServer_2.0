package interfaces;

import components.action.Action;
import exceptions.*;

public interface iRequest
{
    Action getAction() throws ActionException;
}

package controllers.tcodtask.request;

public enum TypeRequest
{
    HASH_CODE("hashCode"),TASKS("tasks");

    private String typeRequest;

    TypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public static TypeRequest whatTypeRequest(String s)
    {
        if(s.equals("hashCode"))
            return HASH_CODE;
        else if(s.equals("tasks"))
            return TASKS;
        else return null;
    }
}

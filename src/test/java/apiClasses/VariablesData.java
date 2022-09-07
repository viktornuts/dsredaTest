package apiClasses;

public class VariablesData {

    private String method;
    private String url;

    public VariablesData() {
    }

    public VariablesData(String method, String url) {
        this.method = method;
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}

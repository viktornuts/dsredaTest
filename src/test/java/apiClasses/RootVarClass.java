package apiClasses;

public class RootVarClass {

    private String operationName;
    public VariablesData variables;
    private String query;

    public RootVarClass(String operationName, VariablesData variables, String query) {
        this.operationName = operationName;
        this.variables = variables;
        this.query = query;
    }

    public RootVarClass() {
    }
}

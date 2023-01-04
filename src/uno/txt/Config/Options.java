package uno.txt.Config;

public enum Options {
    DIFFICULTY("DIFFICULTY", "EASY", "String"),
    INFINITEPIOCHE("INFINITEPIOCHE", "false", "boolean"),
    NOMBREIA("NOMBREIA", "1", "int"),
	NUMBRECARTEENPLUS("NUMBRECARTEENPLUS","0","int");
    private String key;
    private String parameters;
    private String type;

    Options(String key, String parameters, String type) {
        this.key = key;
        this.parameters = parameters;
        this.type = type;
    }

    public String getKey(){
        return this.key;
    }

    public String getParameters(){
        return this.parameters;
    }

    public String getType(){
        return this.type;
    }
}

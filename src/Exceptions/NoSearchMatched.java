package Exceptions;

public class NoSearchMatched extends RuntimeException {
    protected String search;

    public NoSearchMatched(String search){
        super("No Results");
        this.search = search;
    }

    public String getSearch() {
        return search;
    }
}

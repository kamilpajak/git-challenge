package setup;

public enum Environment {

    PRODUCTION("https://github.com/login/");

    private final String url;

    Environment(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }

}

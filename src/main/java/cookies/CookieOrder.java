package cookies;

public class CookieOrder {
    private Cookie cookie;
    private int numBoxes;

    public CookieOrder(Cookie cookie, int numBoxes) {
        this.cookie = cookie;
        this.numBoxes = numBoxes;
    }

    public Cookie getCookie() {
        return this.cookie;
    }

    public int getNumBoxes() {
        return this.numBoxes;
    }

    public void setNumBoxes(int numBoxes) {
        this.numBoxes = numBoxes;
    }
}

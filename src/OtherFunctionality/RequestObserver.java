package OtherFunctionality;

import UserObject.Database;

import java.io.Serializable;

public class RequestObserver implements Observer, Serializable {
    private boolean accepted = false;

    public RequestObserver() {}

    private void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void update() {
        setAccepted(true);
    }
}

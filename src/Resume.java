/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    Resume() {}

    public String getUuid(){
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}

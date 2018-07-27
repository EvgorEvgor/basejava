/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid(){
        return uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}

package fr.arinonia.openjupdate.models;

public class Notification {

    private String message;
    private NotificationType type;

    public Notification(final String message, final NotificationType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public NotificationType getType() {
        return this.type;
    }

    public void setType(final NotificationType type) {
        this.type = type;
    }

    public enum NotificationType {
        SUCCESS("success"),
        INFO("info"),
        WARNING("warning"),
        DANGER("danger");

        private final String value;

        NotificationType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}

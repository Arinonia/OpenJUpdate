package fr.arinonia.openjupdate.models;

public class SetupCheck {

    private String name;
    private boolean success;

    public SetupCheck(final String name, final boolean success) {
        this.name = name;
        this.success = success;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }
}

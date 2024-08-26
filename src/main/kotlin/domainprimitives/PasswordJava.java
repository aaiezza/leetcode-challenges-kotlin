package domainprimitives;

public class PasswordJava {
    private final String value;

    private PasswordJava(final String value) {
        this.value = value;
    }

    public PasswordJava(final String firstEntry, final String secondEntry) {
        this(firstEntry);
        if (firstEntry.equals(secondEntry)) {
            throw new IllegalArgumentException("Passwords must match");
        }
        if (firstEntry.matches("^\\s+.+$|^.+\\s+$")) {
            throw new IllegalArgumentException("Password cannot begin or end with whitespace");
        }
    }
}

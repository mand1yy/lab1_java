package hotel.model;

import java.util.Objects;

public class Visitor {
    private String name;
    private String passportId;

    public Visitor(String name, String passportId) {
        this.name = name;
        this.passportId = passportId;
    }

    public String getName() { return name; }
    public String getPassportId() { return passportId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitor)) return false;
        Visitor visitor = (Visitor) o;
        return passportId.equals(visitor.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportId);
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "name='" + name + '\'' +
                ", passportId='" + passportId + '\'' +
                '}';
    }
}

package src.model;

import java.time.OffsetDateTime;
import java.util.Objects;

public class UserModel {
    private long id;
    private String name, email;
    private OffsetDateTime birthDay;

    public UserModel(){}

    public UserModel(long id, OffsetDateTime birthDay, String email, String name) {
        this.id = id;
        this.birthDay = birthDay;
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OffsetDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(OffsetDateTime birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id && Objects.equals(name, userModel.name) && Objects.equals(email, userModel.email) && Objects.equals(birthDay, userModel.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birthDay);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}

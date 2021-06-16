package com.epam.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class Users implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String lastName;
    private String countryOfOrigin;
    private String email;

    public Users(Integer id, String name, String lastName, String countryOfOrigin, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.countryOfOrigin = countryOfOrigin;
        this.email = email;
    }

    public Users() {
    }

    public static UsersBuilder builder() {
        return new UsersBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getCountryOfOrigin() {
        return this.countryOfOrigin;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Users)) return false;
        final Users other = (Users) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$countryOfOrigin = this.getCountryOfOrigin();
        final Object other$countryOfOrigin = other.getCountryOfOrigin();
        if (this$countryOfOrigin == null ? other$countryOfOrigin != null : !this$countryOfOrigin.equals(other$countryOfOrigin))
            return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Users;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $countryOfOrigin = this.getCountryOfOrigin();
        result = result * PRIME + ($countryOfOrigin == null ? 43 : $countryOfOrigin.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "Users(id=" + this.getId() + ", name=" + this.getName() + ", lastName=" + this.getLastName() + ", countryOfOrigin=" + this.getCountryOfOrigin() + ", email=" + this.getEmail() + ")";
    }

    public static class UsersBuilder {
        private Integer id;
        private String name;
        private String lastName;
        private String countryOfOrigin;
        private String email;

        UsersBuilder() {
        }

        public UsersBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UsersBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UsersBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UsersBuilder countryOfOrigin(String countryOfOrigin) {
            this.countryOfOrigin = countryOfOrigin;
            return this;
        }

        public UsersBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Users build() {
            return new Users(id, name, lastName, countryOfOrigin, email);
        }

        public String toString() {
            return "Users.UsersBuilder(id=" + this.id + ", name=" + this.name + ", lastName=" + this.lastName + ", countryOfOrigin=" + this.countryOfOrigin + ", email=" + this.email + ")";
        }
    }
}

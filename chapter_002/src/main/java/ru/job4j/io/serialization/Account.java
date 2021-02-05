package ru.job4j.io.serialization;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.Objects;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

    @XmlAttribute
    private boolean connected;
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String user;
    private Contact contact;
    @XmlElementWrapper(name = "tokens")
    @XmlElement(name = "token")
    private String[] tokens;

    public Account() {
    }

    public Account(boolean connected, int id, String user,
                   Contact contact, String[] tokens) {
        this.connected = connected;
        this.id = id;
        this.user = user;
        this.contact = contact;
        this.tokens = tokens;
    }

    public boolean isConnected() {
        return connected;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return "Account{"
                + "connected=" + connected
                + ", id=" + id
                + ", userName='" + user
                + '\'' + ", contact=" + contact
                + ", tokens=" + Arrays.toString(tokens)
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        final Account other = (Account) o;
        return this.connected == other.connected
                && this.id == other.id
                && Objects.equals(this.user, other.user)
                && Objects.equals(this.contact, other.contact)
                && Arrays.equals(this.tokens, other.tokens);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(connected, id, user, contact);
        result = 31 * result + Arrays.hashCode(tokens);
        return result;
    }
}

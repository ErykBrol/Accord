package org.accord.platform.security;

import java.security.Principal;
import java.util.Objects;

public class AnonymousPrincipal implements Principal {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object another) {
        if (!(another instanceof Principal))
            return false;

        Principal principal = (Principal) another;
        return Objects.equals(principal.getName(), this.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

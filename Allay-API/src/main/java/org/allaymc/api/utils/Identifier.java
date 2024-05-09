package org.allaymc.api.utils;

import org.allaymc.api.utils.exception.InvalidIdentifierException;

import static org.allaymc.api.utils.AllayStringUtils.fastTwoPartSplit;
import static org.allaymc.api.utils.IdentifierUtils.isNamespaceValid;
import static org.allaymc.api.utils.IdentifierUtils.isPathValid;

/**
 * A simple logging class that implements the Identified interface
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public record Identifier(String namespace, String path) implements Cloneable {
    public static final String NAMESPACE_SEPARATOR = ":";
    public static final String DEFAULT_NAMESPACE = "minecraft";

    public Identifier(String[] id) {
        this(id[0], id[1]);
    }

    public Identifier(String id) {
        this(fastTwoPartSplit(id, NAMESPACE_SEPARATOR, DEFAULT_NAMESPACE));
    }

    public Identifier(String namespace, String path) {
        this.namespace = namespace.isEmpty() ? DEFAULT_NAMESPACE : namespace;
        this.path = path;
        if (!isNamespaceValid(this.namespace))
            throw new InvalidIdentifierException("Non [A-Za-z0-9_.-] character in namespace of location: " + this.namespace + NAMESPACE_SEPARATOR + this.path);
        if (!isPathValid(this.path))
            throw new InvalidIdentifierException("Non [A-Za-z0-9/._-] character in path of location: " + this.namespace + NAMESPACE_SEPARATOR + this.path);
    }

    @Override
    public String toString() {
        return this.namespace + NAMESPACE_SEPARATOR + this.path;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Identifier clone() {
        return new Identifier(namespace, path);
    }
}

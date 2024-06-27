package com.swingplus.function;

@FunctionalInterface
public interface ModificationFunction<Target> {

    void execute(Target typeForModification);
}

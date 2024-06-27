package com.swingplus.model;

import com.swingplus.function.ModificationFunction;

public class HoverEffect <Target> {

    private ModificationFunction<Target> whenEnter = target -> {}, whenExit = target -> {};

    public HoverEffect<Target> whenEnter(ModificationFunction<Target> whenEnter) {
        this.whenEnter = whenEnter;

        return this;
    }

    public void whenEnter(Target target){
        whenEnter.execute(target);
    }

    public HoverEffect<Target> whenExit(ModificationFunction<Target> whenExit) {
        this.whenExit = whenExit;

        return this;
    }

    public void whenExit(Target target){
        whenExit.execute(target);
    }
}

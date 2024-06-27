package com.swingplus.component.adapter;

import com.swingplus.model.HoverEffect;
import lombok.RequiredArgsConstructor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@RequiredArgsConstructor
public class MouseHover <Target> extends MouseAdapter {

    private final Target target;
    private final HoverEffect<Target> hoverEffect;

    @Override
    public void mouseEntered(MouseEvent event) {
        hoverEffect.whenEnter(target);
    }

    @Override
    public void mouseExited(MouseEvent event) {
        hoverEffect.whenExit(target);
    }
}

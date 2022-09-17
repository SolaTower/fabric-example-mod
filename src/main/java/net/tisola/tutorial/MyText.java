package net.tisola.tutorial;

import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;

import java.util.List;

public class MyText implements Text {

    private String tooltipKey;

    public MyText(String tooltipKey_) {
        tooltipKey = tooltipKey_;
    }

    @Override
    public Style getStyle() {
        return null;
    }

    @Override
    public TextContent getContent() {
        return null;
    }

    @Override
    public List<Text> getSiblings() {
        return null;
    }

    @Override
    public OrderedText asOrderedText() {
        return null;
    }
}

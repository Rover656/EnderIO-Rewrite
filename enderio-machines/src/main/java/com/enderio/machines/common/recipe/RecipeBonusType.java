package com.enderio.machines.common.recipe;

import java.util.Locale;

// TODO: Implement properly. Currently just present for recipe completeness
public enum RecipeBonusType {
    NONE,
    MULTIPLY_OUTPUT,
    CHANCE_ONLY;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

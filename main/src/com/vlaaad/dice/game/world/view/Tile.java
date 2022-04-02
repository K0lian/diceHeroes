/*
 * Dice heroes is a turn based rpg-strategy game where characters are dice.
 * Copyright (C) 2016 Vladislav Protsenko
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.vlaaad.dice.game.world.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.vlaaad.common.GdxHelper;
import com.vlaaad.dice.Config;

/**
 * Created 09.01.14 by vlaaad
 * Lightweight image without scale/rotation
 */
public class Tile extends Widget {

    private TextureRegion region;

    public Tile(String regionName) {
        this(Config.skin.getRegion(regionName));
    }

    public Tile(TextureRegion region) {
        this.region = region;
        setSize(region.getRegionWidth(), region.getRegionHeight());
    }

    public void setRegion(TextureRegion region) {
        if (region == null)
            throw new IllegalArgumentException();
        this.region = region;
    }

    @Override public void draw(Batch batch, float parentAlpha) {
        GdxHelper.setBatchColor(batch, getColor(), parentAlpha);
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
    }

    @Override public float getPrefWidth() {
        return region.getRegionWidth();
    }

    @Override public float getPrefHeight() {
        return region.getRegionHeight();
    }

    public void setRegion(String name) {
        setRegion(Config.skin.getRegion(name));
    }
}

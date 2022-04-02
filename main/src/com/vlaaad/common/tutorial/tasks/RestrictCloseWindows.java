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

package com.vlaaad.common.tutorial.tasks;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vlaaad.common.tutorial.TutorialTask;
import com.vlaaad.common.ui.WindowListener;

/**
 * Created 12.11.13 by vlaaad
 * requires stage resource
 */
public class RestrictCloseWindows extends TutorialTask {
    @Override public void start(Callback callback) {
        Stage stage = resources.get("stage");
        EventListener listener = resources.getIfExists("restrictCloseWindowsListener");
        if (listener != null) {
            stage.removeCaptureListener(listener);
        }
        listener = new WindowListener() {
            @Override protected void hide(WindowEvent event) {
                event.cancel();
            }
        };
        stage.addCaptureListener(listener);
        resources.put("restrictCloseWindowsListener", listener);
        callback.taskEnded();
    }
}

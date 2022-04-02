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

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.vlaaad.common.util.futures.Future;
import com.vlaaad.common.util.futures.IFuture;
import com.vlaaad.dice.game.actions.results.imp.TransformFromObstacleResult;
import com.vlaaad.dice.game.world.controllers.ViewController;
import com.vlaaad.dice.managers.SoundManager;

/**
 * Created 08.02.14 by vlaaad
 */
public class TransformFromObstacleVisualizer implements IVisualizer<TransformFromObstacleResult> {
    private final ResultVisualizer visualizer;

    public TransformFromObstacleVisualizer(ResultVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    @Override public IFuture<Void> visualize(final TransformFromObstacleResult result) {
        final Future<Void> future = new Future<Void>();
        final WorldObjectView view = ViewController.createView(visualizer.viewController.world.viewer, visualizer.viewController.world.playerColors, result.creature);
        final WorldObjectView obstacle = result.obstacle.world.getController(ViewController.class).getView(result.obstacle);
        visualizer.viewController.scroller.centerOn(result.obstacle);
        visualizer.viewController.effectLayer.addActor(view);
        view.setOffsetY(2);
        view.setPosition(
            result.obstacle.getX() * ViewController.CELL_SIZE,
            result.obstacle.getY() * ViewController.CELL_SIZE
        );
        view.getColor().a = 0f;
        view.addAction(Actions.delay(0.4f, Actions.run(new Runnable() {
            @Override public void run() {
                if (result.ability.name.equals("potion-of-petrification")) {
                    SoundManager.instance.playSound("ability-freeze-hit");
                } else {
                    SoundManager.instance.playSound("transformation");
                }
            }
        })));
        view.addAction(Actions.alpha(1, 0.5f));
        obstacle.addAction(Actions.sequence(
            Actions.alpha(0, 0.5f),
            Actions.run(new Runnable() {
                @Override public void run() {
                    view.remove();
                    future.happen();
                }
            })
        ));
        return future;
    }
}

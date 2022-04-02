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

package com.vlaaad.dice.api;

import com.vlaaad.dice.api.purchases.IPurchaseListener;
import com.vlaaad.dice.api.services.IGameServices;
import com.vlaaad.dice.game.config.purchases.PurchaseInfo;

/**
 * Created 15.10.13 by vlaaad
 */
public interface IMobileApi {
    int getVersionCode();
    public void share(String message);
    public void setPurchaseListener(IPurchaseListener listener);
    public void purchase(PurchaseInfo info);
    public void rateApp();
    public void keepScreenOn(boolean value);
    public IGameServices services();
}

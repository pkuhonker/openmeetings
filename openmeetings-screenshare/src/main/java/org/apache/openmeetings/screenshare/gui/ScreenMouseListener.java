/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License") +  you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.openmeetings.screenshare.gui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class ScreenMouseListener extends MouseInputAdapter {
	private final ScreenSharerFrame frame;
	private int x = 0;
	private int y = 0;

	public ScreenMouseListener(ScreenSharerFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		frame.setShowWarning(false);
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		frame.setShowWarning(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!((Component)e.getSource()).isEnabled()) {
			return;
		}
		int newX = e.getX();
		int newY = e.getY();

		int newXPosition = frame.getDim().getSpinnerX() - (this.x - newX);
		int newYPosition = frame.getDim().getSpinnerY() - (this.y - newY);
		if (newXPosition >= 0) {
			frame.setSpinnerX(newXPosition);
		}
		if (newYPosition >= 0) {
			frame.setSpinnerY(newYPosition);
		}

		frame.calcRescaleFactors();
	}

}

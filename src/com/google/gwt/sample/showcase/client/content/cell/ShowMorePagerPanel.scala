/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.sample.showcase.client.content.cell

import com.google.gwt.event.dom.client.ScrollEvent
import com.google.gwt.user.cellview.client.AbstractPager
import com.google.gwt.view.client.HasRows
import com.google.gwt.sample.showcase.client.Handlers._
import java.lang.AssertionError
import com.google.gwt.user.client.ui.{Widget, ScrollPanel}
import com.google.gwt.sample.showcase.client.ShowcaseAnnotations.ShowcaseSource

/**
 * A scrolling pager that automatically increases the range every time the
 * scroll bar reaches the bottom.
 */
@ShowcaseSource
class ShowMorePagerPanel extends AbstractPager {
  /**
   * The default increment size.
   */
  private[this] val DEFAULT_INCREMENT = 20

  /**
   * The increment size.
   */
  private[this] var incrementSize = DEFAULT_INCREMENT

  /**
   * The last scroll position.
   */
  private[this] var lastScrollPos = 0

  /**
   * The scrollable panel.
   */
  private[this] val scrollable = new ScrollPanel()

  /**
   * Constructor Logic
   */
  initWidget(scrollable)

  // Do not let the scrollable take tab focus.
  scrollable.getElement.setTabIndex(-1)

  // Handle scroll events.
  scrollable.addScrollHandler((_: ScrollEvent) => {
    // If scrolling up, ignore the event.
    val oldScrollPos = lastScrollPos
    lastScrollPos = scrollable.getVerticalScrollPosition
    if (oldScrollPos < lastScrollPos) {
      val display = getDisplay
      if (display != null) {
        val maxScrollTop = scrollable.getWidget.getOffsetHeight - scrollable.getOffsetHeight
        if (lastScrollPos >= maxScrollTop) {
          // We are near the end, so increase the page size.
          val newPageSize = math.min(display.getVisibleRange.getLength + incrementSize, display.getRowCount)
          display.setVisibleRange(0, newPageSize)
        }
      }
    }
  })

  /**
   * Get the number of rows by which the range is increased when the scrollbar
   * reaches the bottom.
   *
   * @return the increment size
   */
  def getIncrementSize: Int = incrementSize

  override def setDisplay(display: HasRows) {
    //TODO: where does this assert command come from?
    if (!display.isInstanceOf[Widget]) throw new AssertionError("display must extend Widget")
    scrollable.setWidget(display.asInstanceOf[Widget])
    super.setDisplay(display)
  }

  /**
   * Set the number of rows by which the range is increased when the scrollbar
   * reaches the bottom.
   *
   * @param incrementSize the incremental number of rows
   */
  def setIncrementSize(incrementSize: Int) {
    this.incrementSize = incrementSize
  }

  override protected def onRangeOrRowCountChanged() {
  }
}
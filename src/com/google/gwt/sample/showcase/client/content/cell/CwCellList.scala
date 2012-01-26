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

import com.google.gwt.sample.showcase.client.ContentWidget
import com.google.gwt.i18n.client.Constants
import com.google.gwt.user.client.rpc.AsyncCallback
import com.google.gwt.core.client.{RunAsyncCallback, GWT}
import com.google.gwt.uibinder.client.UiBinder
import com.google.gwt.sample.showcase.client.ShowcaseAnnotations.{ShowcaseSource, ShowcaseStyle}
import com.google.gwt.cell.client.AbstractCell
import com.google.gwt.sample.showcase.client.content.cell.ContactDatabase.ContactInfo
import com.google.gwt.user.client.ui.{AbstractImagePrototype, Widget}
import com.google.gwt.cell.client.Cell.Context
import com.google.gwt.safehtml.shared.SafeHtmlBuilder
import com.google.gwt.resources.client.{ClientBundle, ImageResource}

/**
 * Companion Object
 */
@ShowcaseSource
object CwCellList {
  /**
   * The constants used in this Content Widget.
   */
  trait CwConstants extends Constants with ContentWidget.CwConstants {
  }

  /**
   * The images used for this example.
   */
  @ShowcaseSource
  trait Images extends ClientBundle {
    def contact: ImageResource
  }

  /**
   * The Cell used to render a {@link ContactInfo}.
   */
  class ContactCell(image: ImageResource) extends AbstractCell[ContactInfo] {
    /**
     * The html of the image used for contacts.
     */
    private[this] val imageHtml = AbstractImagePrototype.create(image).getHTML

    def render(context: Context, value: ContactInfo, sb: SafeHtmlBuilder) {
      // Value can be null, so do a null check..
      if (value == null) {
        return;
      }

      sb.appendHtmlConstant("<table>");

      // Add the contact image.
      sb.appendHtmlConstant("<tr><td rowspan='3'>");
      sb.appendHtmlConstant(imageHtml);
      sb.appendHtmlConstant("</td>");

      // Add the name and address.
      sb.appendHtmlConstant("<td style='font-size:95%;'>");
      sb.appendEscaped(value.fullName);
      sb.appendHtmlConstant("</td></tr><tr><td>");
      sb.appendEscaped(value.address);
      sb.appendHtmlConstant("</td></tr></table>");
    }
  }
}

/**
 * Example file.
 */
@ShowcaseSource
@ShowcaseStyle(value = Array(".gwt-CellList"))
class CwCellList(constants: CwCellList.CwConstants) extends ContentWidget(constants) {
  trait Binder extends UiBinder[Widget, CwCellList] {
  }

  def getDescription(): String = "CwCellList Description"
  def getName(): String = "CwCellList"

  /**
   * Initialize this example.
   */
  def onInitialize(): Widget = {
    val uiBinder: Binder = GWT.create(classOf[Binder]);
    uiBinder.createAndBindUi(this);
  }

  protected def asyncOnInitialize(callback: AsyncCallback[Widget]) {
    GWT.runAsync(classOf[CwCellList], new RunAsyncCallback() {
      def onFailure(caught: Throwable) {
        callback.onFailure(caught)
      }

      def onSuccess() {
        callback.onSuccess(onInitialize())
      }
    })
  }
}

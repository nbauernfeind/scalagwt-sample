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
import com.google.gwt.cell.client.AbstractCell
import com.google.gwt.sample.showcase.client.content.cell.ContactDatabase.ContactInfo
import com.google.gwt.cell.client.Cell.Context
import com.google.gwt.safehtml.shared.SafeHtmlBuilder
import com.google.gwt.resources.client.{ClientBundle, ImageResource}
import com.google.gwt.uibinder.client.{UiField, UiBinder}
import com.google.gwt.sample.showcase.client.ShowcaseAnnotations.{ShowcaseData, ShowcaseSource, ShowcaseStyle}
import com.google.gwt.user.cellview.client.CellList
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy
import com.google.gwt.event.dom.client.ClickEvent
import com.google.gwt.sample.showcase.client.Handlers._
import com.google.gwt.user.client.ui.{HTML, Button, AbstractImagePrototype, Widget}
import beans.BeanProperty
import com.google.gwt.resources.client.ClientBundle.Source
import com.google.gwt.view.client.{SelectionChangeEvent, SingleSelectionModel}

/**
 * Companion Object
 */
@ShowcaseSource
object CwCellListCompanion {
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
class CwCellList(constants: CwCellListCompanion.CwConstants) extends ContentWidget(constants) {

  import CwCellListCompanion._

  trait Binder extends UiBinder[Widget, CwCellList] {
  }

  def getDescription(): String = "CwCellList Description"
  def getName(): String = "CwCellList"

  /**
   * The contact form used to update contacts.
   */
  //  @ShowcaseData
  //  @UiField
//    var contactForm: ContactInfoForm = null

  /**
   * The button used to generate more contacts.
   */
  @ShowcaseData
  @UiField
  var generateButton: Button = null

  /**
   * The pager used to change the range of data.
   */
  @ShowcaseData
  @UiField
  var pagerPanel: ShowMorePagerPanel = null

  /**
   * The pager used to display the current range.
   */
  //  @ShowcaseData
//    @UiField
//    var rangeLabelPager: RangeLabelPager = null

  /**
   * The CellList.
   */
  @ShowcaseData
  private[this] var cellList: CellList[ContactInfo] = null

  /**
   * Initialize this example.
   */
  def onInitialize(): Widget = {
    val images: Images = GWT.create(classOf[Images])

    // Create a CellList.
    val contactCell = new ContactCell(images.contact)

    // Set a key provider that provides a unique key for each contact. If key is
    // used to identify contacts when fields (such as the name and address)
    // change.
//    cellList = new CellList[ContactInfo](contactCell, ContactDatabase.KEY_PROVIDER)
//    cellList.setPageSize(30)
//    cellList.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE)
//    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION)

    // Add a selection model so we can select cells.
//    val selectionModel = new SingleSelectionModel[ContactInfo](ContactDatabase.KEY_PROVIDER)
//    cellList.setSelectionModel(selectionModel);
//        selectionModel.addSelectionChangeHandler((_: SelectionChangeEvent) => {
//            contactForm.setContact(selectionModel.getSelectedObject());
//        })

    // Create the UiBinder.
    val uiBinder: Binder = GWT.create(classOf[Binder])
    val widget = uiBinder.createAndBindUi(this)

    // Add the CellList to the data provider in the database.
//    ContactDatabase.getContactDatabase.addDataDisplay(cellList);

    // Set the cellList as the display of the pagers. This example has two
    // pagers. pagerPanel is a scrollable pager that extends the range when the
    // user scrolls to the bottom. rangeLabelPager is a pager that displays the
    // current range, but does not have any controls to change the range.
//    pagerPanel.setDisplay(cellList);
    //    rangeLabelPager.setDisplay(cellList);

    // Handle events from the generate button.
//    generateButton.addClickHandler((_: ClickEvent) => ContactDatabase.getContactDatabase.generateContacts(50))

    widget
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

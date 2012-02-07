package com.google.gwt.sample.jribble.client

import com.google.gwt.core.client.GWT
import com.google.gwt.event.dom.client.{ClickEvent, ClickHandler}
import com.google.gwt.user.client.Window
import com.google.gwt.user.client.ui.{Widget, LazyPanel, VerticalPanel, Label, Button}
import com.google.gwt.uibinder.client.{UiHandler, UiBinder, UiField}

class HelloWidget extends LazyPanel {

  trait Binder extends UiBinder[VerticalPanel, HelloWidget]

  @UiField
  var titleLabel: Label = null

  @UiField
  var clickMeButton: Button = null

  def createWidget(): Widget = {
    // Create the UiBinder.
    val uiBinder: Binder = GWT.create(classOf[Binder])
    val widget: Widget = uiBinder.createAndBindUi(this);

    if (titleLabel != null) {
      titleLabel.setText("Hello World")
    }

    widget
  }

  @UiHandler(Array("clickMeButton"))
  def onClick(clickEvent: ClickEvent) {
    Window.alert("Hello World from UIBinder")
  }

  implicit def clickHandler(f: => Unit): ClickHandler = new ClickHandler {
    def onClick(event: ClickEvent) {
      f
    }
  }
}

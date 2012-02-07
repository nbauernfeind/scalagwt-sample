package com.google.gwt.sample.jribble.client

import com.google.gwt.core.client.{GWT, EntryPoint}
import com.google.gwt.user.client.ui.RootPanel

class Hello extends EntryPoint {
  def onModuleLoad() {
    val widget: HelloWidget = GWT.create(classOf[HelloWidget])
    RootPanel.get().add(widget.createWidget());
  }
}

package com.example.mywidgetapp

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class WidgetReceiver: GlanceAppWidgetReceiver(){
    override val glanceAppWidget: GlanceAppWidget
        get() = WidgetViewer()
}
package com.glasswidgets.app

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val density = resources.displayMetrics.density
        val pad = (24 * density).toInt()

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(pad, pad, pad, pad)
            setBackgroundColor(Color.parseColor("#FF14213D"))
        }

        val title = TextView(this).apply {
            text = getString(R.string.main_title)
            textSize = 26f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val body = TextView(this).apply {
            text = getString(R.string.main_body)
            textSize = 16f
            setTextColor(Color.parseColor("#CCFFFFFF"))
            gravity = Gravity.CENTER
            setPadding(0, pad, 0, pad)
        }

        val button = Button(this).apply {
            text = getString(R.string.main_add_button)
            setOnClickListener { requestPinWidget() }
        }

        root.addView(title)
        root.addView(body)
        root.addView(button)
        setContentView(root)
    }

    private fun requestPinWidget() {
        val manager = AppWidgetManager.getInstance(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && manager.isRequestPinAppWidgetSupported) {
            manager.requestPinAppWidget(
                ComponentName(this, ClockWidgetProvider::class.java),
                null,
                null
            )
        } else {
            Toast.makeText(this, R.string.main_pin_unsupported, Toast.LENGTH_LONG).show()
        }
    }
}

package com.glasswidgets.app

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.widget.RemoteViews

/**
 * ويدجت الساعة الزجاجية.
 * الساعة والتاريخ بيتحدثوا لوحدهم عن طريق TextClock جوه التصميم،
 * فمش محتاجين أي خدمة شغالة في الخلفية.
 */
class ClockWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (id in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_clock)

            // الضغط على الويدجت بيفتح المنبهات
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)

            appWidgetManager.updateAppWidget(id, views)
        }
    }
}

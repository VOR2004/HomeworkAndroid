package ru.ucheba.hw1.adapter.spinner

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ru.ucheba.hw1.R
import ru.ucheba.hw1.model.NotificationImportance

class AdapterImportanceSpinner(
    context: Context,
    items: List<NotificationImportance>
) : ArrayAdapter<NotificationImportance>(context, android.R.layout.simple_spinner_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        val item = getItem(position)!!
        view.text = getNotificationImportanceString(context, item)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        val item = getItem(position)!!
        view.text = getNotificationImportanceString(context, item)
        return view
    }

    private fun getNotificationImportanceString(context: Context, importance: NotificationImportance): String {
        return when (importance) {
            NotificationImportance.MAX -> context.getString(R.string.max_importance)
            NotificationImportance.HIGH -> context.getString(R.string.high_importance)
            NotificationImportance.DEFAULT -> context.getString(R.string.default_importance)
            NotificationImportance.LOW -> context.getString(R.string.low_importance)
        }
    }
}
package com.limitless.circuitcalulator.utilities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class Utils {

    fun sendFeedback(activity: FragmentActivity) {
        val message = """
                Please drop your feedback""".trimIndent()
        val emailIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("charlyco835@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Developer Feedback")
            putExtra(Intent.EXTRA_TEXT, message)
        }
        try {
            val title = "Choose an email app"
            val chooser: Intent = Intent.createChooser(emailIntent, title)
            if (emailIntent.resolveActivity(activity.packageManager) != null) {
                activity.startActivity(chooser)
            }
        }catch (e: ActivityNotFoundException) {
            Toast.makeText(activity, "Only email applications are allowed", Toast.LENGTH_LONG).show()
        }
    }
}
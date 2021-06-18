package com.akbar.photosapi.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.akbar.photosapi.R
import com.google.android.material.snackbar.Snackbar

class GeneralSnackbar {
    companion object {
        fun showErrorSnackBar(view: View, msg: String, context: Context) {
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).apply {
                val tv =
                    this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                tv?.setTextColor(Color.WHITE)
                this.view.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorBrightRed
                    )
                )
            }.show()
        }
    }
}
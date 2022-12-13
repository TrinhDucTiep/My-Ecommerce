package com.example.myecommerce.helper

import android.app.AlertDialog
import android.content.Context
import com.example.myecommerce.R
import com.example.myecommerce.callback.VoidCallBack

class AlertDialogHelper {

    companion object {

        fun showAlertDialog(conText: Context, title: String, message: String, icon: Int, okCallBack: (() -> Unit) ? = null){
            val builder = AlertDialog.Builder(conText)
            builder.setTitle(title)
                .setMessage(message)
                .setIcon(icon)
                .setPositiveButton(R.string.yes){
                        dialog, which ->
                    run {
                        if (okCallBack != null){
                            okCallBack.invoke()
                        }
                    }
                }
                .setNegativeButton(R.string.cancel){
                        dialog, which ->
                    run {
                        dialog.dismiss()
                    }
                }
                .show()
        }

    }

}
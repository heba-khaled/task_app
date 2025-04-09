package com.heba.task_app.core.presentation.delegation

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.heba.task_app.R


interface IHandlingLoading {
    fun showLoadingDialog(context: Context): Dialog
    fun onLoadingDialog(isLoading: Boolean, context: Context)
}

class HandleLoadingImpl : IHandlingLoading {
    private var dialog: AlertDialog? = null
    override fun showLoadingDialog(context: Context): AlertDialog {
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_progress, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        return if (dialog == null){
            dialog = dialogBuilder.create()
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!
        } else {
            dialog!!
        }
    }

    override fun onLoadingDialog(isLoading: Boolean, context: Context) {
        val progressbar =  showLoadingDialog(context)
        if (isLoading) {
            progressbar.show()
        }else{
            progressbar.dismiss()
        }
    }
}
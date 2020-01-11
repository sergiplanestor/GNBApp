package bemobile.splanes.com.gnbapp.commons.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import bemobile.splanes.com.gnbapp.R
import kotlinx.android.synthetic.main.popup_view.*

class PopUp(context: Context, private val model: PopUpModel) : Dialog(context) {

    companion object {
        const val TAG = "PopUp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_view)

        titleTextView.text = model.title
        bodyTextView.text = model.body
        positiveButton.text = model.positiveButton
        if (model.negativeButton != null) {
            negativeButton.text = model.negativeButton
        } else {
            separator.visibility = View.GONE
            negativeButton.visibility = View.GONE
        }

        positiveButton.setOnClickListener {
            dismiss()
            model.listener?.onPositiveClick()
        }
        negativeButton.setOnClickListener {
            dismiss()
            model.listener?.onNegativeClick()
        }

        setCancelable(false)
    }
}
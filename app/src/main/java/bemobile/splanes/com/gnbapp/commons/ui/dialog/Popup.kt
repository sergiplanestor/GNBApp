package bemobile.splanes.com.gnbapp.commons.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class Popup(private val title: String,
            private val subtitle: String,
            private val positiveButton: String,
            private val negativeButton: String,
            private val onPopupButtonClickListener: OnPopupButtonClickListener) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}
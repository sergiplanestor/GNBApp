package bemobile.splanes.com.gnbapp.commons.ui.dialog

/**
 * Dialog model data class
 */
data class PopUpModel(val title: String,
                      val body: String,
                      val positiveButton: String,
                      val negativeButton: String? = null,
                      val listener: OnPopupButtonClickListener? = null)
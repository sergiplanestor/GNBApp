package bemobile.splanes.com.gnbapp.commons.extension

import android.app.Activity
import bemobile.splanes.com.gnbapp.app.GNBApplication

/**
 * Extension for {@link Activity} class to can get application casted to {@link GNBApplication}.
 */
val Activity.app : GNBApplication get() = application as GNBApplication
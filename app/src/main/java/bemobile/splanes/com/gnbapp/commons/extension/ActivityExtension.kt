package bemobile.splanes.com.gnbapp.commons.extension

import android.app.Activity
import bemobile.splanes.com.gnbapp.app.GNBApplication

val Activity.app : GNBApplication get() = application as GNBApplication
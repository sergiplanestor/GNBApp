package bemobile.splanes.com.gnbapp.commons.ui.component

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import bemobile.splanes.com.gnbapp.R
import com.airbnb.lottie.LottieAnimationView

class AppLoader @JvmOverloads constructor(context: Context,
                                          attrs: AttributeSet? = null,
                                          defStyle: Int = 0,
                                          defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private lateinit var mAnimationView : LottieAnimationView
    private val mAnimationListener : Animator.AnimatorListener = object : Animator.AnimatorListener {

        override fun onAnimationRepeat(animation: Animator?) {
            hideLoader(true)
            mAnimationView.removeAllAnimatorListeners()
        }
        override fun onAnimationEnd(animation: Animator?) {
            // Nothing to do here
        }

        override fun onAnimationCancel(animation: Animator?) {
            // Nothing to do here
        }
        override fun onAnimationStart(animation: Animator?) {
            // Nothing to do here
        }
    }

    init {
        bindViews()
    }

    private fun bindViews() {
        View.inflate(context, R.layout.component_app_loader, this)
        mAnimationView = findViewById(R.id.lottie_animation_view)
    }

    fun showLoader() {
        mAnimationView.visibility = View.VISIBLE
        mAnimationView.playAnimation()
    }

    fun hideLoader() {
        hideLoader(false)
    }

    fun hideLoader(force: Boolean) {
        if (force) {
            mAnimationView.visibility = View.GONE
            mAnimationView.cancelAnimation()
        } else {
            mAnimationView.addAnimatorListener(mAnimationListener)
        }
    }
}
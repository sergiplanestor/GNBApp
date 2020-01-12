package bemobile.splanes.com.gnbapp.commons.ui.component

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import bemobile.splanes.com.gnbapp.R
import com.airbnb.lottie.LottieAnimationView

/**
 * Loader component
 */
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

    /**
     * Shows loader and start animation
     */
    fun showLoader() {
        this.visibility = View.VISIBLE
        mAnimationView.visibility = View.VISIBLE
        mAnimationView.playAnimation()
    }

    /**
     * Hides loader when animation ends.
     */
    fun hideLoader() {
        hideLoader(false)
    }

    /**
     * Hides loader. If loader should be hidden immediately param should be true, in that case, will
     * not wait to finish its animation to be hidden.
     */
    fun hideLoader(force: Boolean) {
        if (force) {
            this.visibility = View.GONE
            mAnimationView.visibility = View.GONE
            mAnimationView.cancelAnimation()
        } else {
            mAnimationView.addAnimatorListener(mAnimationListener)
        }
    }
}
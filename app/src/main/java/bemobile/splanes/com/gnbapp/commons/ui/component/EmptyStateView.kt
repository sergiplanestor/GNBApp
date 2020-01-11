package bemobile.splanes.com.gnbapp.commons.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import bemobile.splanes.com.gnbapp.R
import kotlinx.android.synthetic.main.component_empty_state.view.*

class EmptyStateView @JvmOverloads constructor(context: Context,
                                               attrs: AttributeSet? = null,
                                               defStyle: Int = 0,
                                               defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        bindViews()
        if (attrs != null) {
            bindAttrs(attrs)
        }
    }

    private fun bindViews() {
        View.inflate(context, R.layout.component_empty_state, this)
        emptyStateImage.setImageResource(R.drawable.ic_empty)
    }

    private fun bindAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyStateView)
        var ref = typedArray.getResourceId(typedArray.getIndex(R.styleable.EmptyStateView_emptyStateTitle), -1)
        if (ref != -1) setTitle(context.getString(ref))
        ref = typedArray.getResourceId(typedArray.getIndex(R.styleable.EmptyStateView_emptyStateSubtitle), -1)
        if (ref != -1) setSubtitle(context.getString(ref))
        ref = typedArray.getResourceId(typedArray.getIndex(R.styleable.EmptyStateView_emptyStateButtonText), -1)
        if (ref != -1) setButtonText(context.getString(ref))
        typedArray.recycle()
    }

    fun setTitle(title: String) {
        emptyStateTitleTextView.text = title
    }

    fun setSubtitle(subtitle: String) {
        emptyStateSubtitleTextView.text = subtitle
    }

    fun setButtonText(buttonText: String) {
        emptyStateButton.text = buttonText
    }

    fun setButtonClickListener(listener: OnClickListener) {
        emptyStateButton.setOnClickListener(listener)
    }
}
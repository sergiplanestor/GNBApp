package bemobile.splanes.com.gnbapp.commons.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import bemobile.splanes.com.gnbapp.R
import kotlinx.android.synthetic.main.component_product_view.view.*

class ProductView @JvmOverloads constructor(context: Context,
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
        View.inflate(context, R.layout.component_product_view, this)
        productImage.setImageResource(R.drawable.ic_product)
    }

    private fun bindAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProductView)
        setProductName(typedArray.getString(R.styleable.ProductView_productName))
        typedArray.recycle()
    }

    fun setProductName(name: String?) {
        productName.text = name
    }

    fun setCardClickListener(onClickListener: OnClickListener) {
        productCardView.setOnClickListener(onClickListener)
    }
}
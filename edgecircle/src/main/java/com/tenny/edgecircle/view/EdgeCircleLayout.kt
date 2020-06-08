package com.tenny.edgecircle.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.tenny.edgecircle.R
import com.tenny.edgecircle.drawable.EdgeCircleDrawable
import com.tenny.edgecircle.utils.dp

/**
 * @author QTenny
 * @time 2020/5/26
 */
class EdgeCircleLayout(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var drawable: EdgeCircleDrawable

    init {
        val typedArray = context!!.obtainStyledAttributes(attrs!!, R.styleable.EdgeCircleLayout)

        val verticalOffset = typedArray.getDimension(R.styleable.EdgeCircleLayout_verticalOffset, 50.dp)
        val cornerRadius = typedArray.getDimension(R.styleable.EdgeCircleLayout_cornerRadius, 3.dp)
        val innerCircleRadius = typedArray.getDimension(R.styleable.EdgeCircleLayout_innerCircleRadius, 5.dp)
        val backgroundColor = typedArray.getColor(R.styleable.EdgeCircleLayout_backgroundColor, Color.WHITE)
        val shadowColor = typedArray.getColor(R.styleable.EdgeCircleLayout_shadowColor, Color.BLUE)
        val dashColor = typedArray.getColor(R.styleable.EdgeCircleLayout_dashColor, Color.DKGRAY)

        typedArray.recycle()

        drawable = EdgeCircleDrawable().apply {
            this.cornerRadius = cornerRadius
            this.innerCircleRadius = innerCircleRadius
            this.verticalOffset = verticalOffset
            this.backgroundColor = backgroundColor
            this.shadowColor = shadowColor
            this.dashColor = dashColor
        }

        background = drawable
    }

}
package com.tenny.edgecircle.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import com.tenny.edgecircle.utils.dp
import com.tenny.edgecircle.utils.toRectF

/**
 * EdgeCircleDrawable
 * @author QTenny
 * @time 2020/5/26
 */
class EdgeCircleDrawable : Drawable() {

    var cornerRadius = 3.dp
    var innerCircleRadius = 5.dp
    var verticalOffset = 50.dp

    var backgroundColor = Color.WHITE
    var shadowColor = Color.GRAY
    var dashColor = Color.DKGRAY

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val pathEffect = DashPathEffect(floatArrayOf(3.dp, 1.dp), 0f)

    private val path = Path()
    private lateinit var rectF: RectF

    override fun onBoundsChange(bounds: Rect) {
        path.reset()

        rectF = bounds.toRectF(cornerRadius.toFloat())

        path.moveTo(rectF.right - cornerRadius, rectF.top)
        path.lineTo(rectF.left + cornerRadius, rectF.top)
        path.arcTo(
            rectF.left,
            rectF.top,
            rectF.left + 2 * cornerRadius,
            rectF.top + 2 * cornerRadius,
            270f,
            -90f,
            false
        )

        path.lineTo(rectF.left, rectF.top + verticalOffset - innerCircleRadius)
        path.arcTo(
            rectF.left - innerCircleRadius,
            rectF.top + verticalOffset - innerCircleRadius,
            rectF.left + innerCircleRadius,
            rectF.top + verticalOffset + innerCircleRadius,
            270f,
            180f,
            false
        )
        path.lineTo(rectF.left, rectF.bottom - cornerRadius)
        path.arcTo(
            rectF.left,
            rectF.bottom - cornerRadius * 2,
            rectF.left + cornerRadius * 2,
            rectF.bottom,
            180f,
            -90f,
            false
        )
        path.lineTo(rectF.right - cornerRadius, rectF.bottom)
        path.arcTo(
            rectF.right - 2 * cornerRadius,
            rectF.bottom - 2 * cornerRadius,
            rectF.right,
            rectF.bottom,
            90f,
            -90f,
            false
        )
        path.lineTo(rectF.right, rectF.top + verticalOffset + innerCircleRadius)
        path.arcTo(
            rectF.right - innerCircleRadius,
            rectF.top + verticalOffset - innerCircleRadius,
            rectF.right + innerCircleRadius,
            rectF.top + verticalOffset + innerCircleRadius,
            90f,
            180f,
            false
        )
        path.lineTo(rectF.right, rectF.top + cornerRadius)
        path.arcTo(
            rectF.right - 2 * cornerRadius,
            rectF.top,
            rectF.right,
            rectF.top + 2 * cornerRadius,
            0f,
            -90f,
            false
        )

    }

    override fun draw(canvas: Canvas) {

        canvas.drawPath(path, paint.also {
            it.setShadowLayer(cornerRadius.toFloat(), 0f, 0f, shadowColor)
            it.pathEffect = null
            it.color = backgroundColor
        })

        canvas.drawLine(
            rectF.left + innerCircleRadius,
            rectF.top + verticalOffset,
            rectF.right - innerCircleRadius,
            rectF.top + verticalOffset,
            paint.also {
                it.pathEffect = pathEffect
                it.color = dashColor
            }
        )
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getAlpha(): Int {
        return paint.alpha
    }

    override fun getOpacity(): Int {
        return when (paint.alpha) {
            0 -> PixelFormat.TRANSPARENT
            0xff -> PixelFormat.OPAQUE
            else -> PixelFormat.TRANSLUCENT
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getColorFilter(): ColorFilter? {
        return paint.colorFilter
    }
}
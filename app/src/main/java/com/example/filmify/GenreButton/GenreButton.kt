package com.example.filmify.GenreButton
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.filmify.R
import java.lang.Integer.min


class GenreButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var isSelected: Boolean = false;
    init {
        setBackgroundColor(Color.TRANSPARENT)
        setTextColor(Color.WHITE)

      //  val customTypeface = Typeface.createFromAsset(context.assets, "montserrat_alternates_regular.ttf")
     //   setTypeface(customTypeface)

        val shape = GradientDrawable()
        shape.setStroke(2, Color.WHITE)
        val cornerRadius = 100f
        shape.cornerRadius = cornerRadius
        background = shape
        // You can customize other properties here

        setOnClickListener {
            val shape = GradientDrawable()
            shape.setStroke(2, Color.WHITE)
            shape.cornerRadius = min(width, height) / 2f
            background = shape
            if (isSelected) {
                isSelected = false
                shape.setColor(Color.TRANSPARENT)
            }
            else {
                isSelected = true;
                shape.setColor(ContextCompat.getColor(context, R.color.pink))
            }
            background = shape
        }
    }
}
package com.voda.presentation.ui.main.nav.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.voda.presentation.R

class TabButton(
    context: Context,
    attrs: AttributeSet? = null
): ConstraintLayout(context, attrs) {

    private val unselectedImgId: Int
    private val selectedImgId: Int
    private val imageView: ImageView

    init {
        val arr = context.obtainStyledAttributes(
            attrs, R.styleable.BottomTabButton
        )

        unselectedImgId = arr.getResourceId(R.styleable.BottomTabButton_unselectedImage, R.drawable.ic_bottom_tab_noti_unselected)
        selectedImgId = arr.getResourceId(R.styleable.BottomTabButton_selectedImage, R.drawable.ic_bottom_tab_noti)

        arr.recycle()

        LayoutInflater.from(context).inflate(
            R.layout.custom_bottom_tab_button,
            this,
            true
        )

        imageView = findViewById(R.id.image_view)

        imageView.setBackgroundResource(unselectedImgId)
    }

    fun activate() {
        imageView.setBackgroundResource(selectedImgId)
    }

    fun inActivate() {
        imageView.setBackgroundResource(unselectedImgId)
    }

}
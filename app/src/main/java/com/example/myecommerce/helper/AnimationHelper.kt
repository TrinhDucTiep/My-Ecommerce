package com.example.myecommerce.helper

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.animation.*
import android.view.animation.Animation.AnimationListener
import android.widget.ImageView
import androidx.appcompat.view.menu.ActionMenuItemView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AnimationHelper {
    companion object {
        val ANIMATION_DURATION = 1000L
        var isAnimationStart = false

        fun translateAnimation(viewAnimation: FloatingActionButton, startView: ImageView, endView: ActionMenuItemView, animationListener: AnimationListener) {

            //draw startView into viewAnimation
//            val bitmap = Bitmap.createBitmap(
//                startView.width, startView.height, Bitmap.Config.ARGB_8888
//            )
//            val canvas = Canvas(bitmap)
//            viewAnimation.draw(canvas)
//            startView.isDrawingCacheEnabled = true
//            val cache = startView.getDrawingCache() ?: return
//            val bitmap = Bitmap.createBitmap(cache)
//            startView.isDrawingCacheEnabled = false
//            viewAnimation.setImageBitmap(bitmap)

            val startViewWidthCenter = startView.width / 2f
            val startViewHeightCenter = startView.height / 2f
            val endViewWidthCenter = endView.width * 0.75f
            val endViewHeightCenter = endView.height / 2f

            val startPos = IntArray(2)
            startView.getLocationOnScreen(startPos)
            val endPos = IntArray(2)
            endView.getLocationOnScreen(endPos)

            val fromX = startPos[0]
            val toX = endPos[0] + endViewWidthCenter / 2 //+ startViewWidthCenter
            val fromY = startPos[1] - startViewWidthCenter
            val toY = endPos[1] - endViewHeightCenter + startViewHeightCenter

            val animationSet = AnimationSet(true)
            animationSet.setInterpolator(AccelerateInterpolator())

            val animationDuration = 200L

            val startScaleAnimation = ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, startViewWidthCenter, startViewHeightCenter)
            startScaleAnimation.duration = animationDuration

            val translateAnimation = TranslateAnimation(fromX.toFloat(), toX.toFloat(), fromY, toY)
            translateAnimation.startOffset = animationDuration
            translateAnimation.duration = ANIMATION_DURATION

            val translateScaleAnimation = ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, toX.toFloat(), toY)
            translateScaleAnimation.startOffset = animationDuration
            translateScaleAnimation.duration = ANIMATION_DURATION

            animationSet.addAnimation(startScaleAnimation)
            animationSet.addAnimation(translateAnimation)
            animationSet.addAnimation(translateScaleAnimation)

            if (isAnimationStart) {
                viewAnimation.clearAnimation()

                animationListener.onAnimationEnd(null)
            }

            viewAnimation.startAnimation(animationSet)

            animationSet.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {
                    isAnimationStart = true

                    viewAnimation.visibility = View.VISIBLE
                    startView.visibility = View.INVISIBLE

                    animationListener.onAnimationStart(animation)
                }

                override fun onAnimationEnd(animation: Animation?) {
                    viewAnimation.visibility = View.GONE
                    startView.visibility = View.VISIBLE

                    animationListener.onAnimationEnd(animation)

                    isAnimationStart = false
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    animationListener.onAnimationRepeat(animation)
                }

            })
        }
    }
}
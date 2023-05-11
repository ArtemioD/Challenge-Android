package com.artemiod.challengeandroidssr.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.artemiod.challengeandroidssr.R

class MainActivity : AppCompatActivity(), SelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun OnSelected(img: String) {
        val bundle = Bundle()
        bundle.putString("img", img)
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, detailFragment)
            .addToBackStack(null).commit()
    }
}

interface SelectedListener {
    fun OnSelected(img: String)
}
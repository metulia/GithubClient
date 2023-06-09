package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.githubclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        val listener = View.OnClickListener {
            presenter.buttonClick(it.id)
        }

        binding?.btnCounter1?.setOnClickListener(listener)
        binding?.btnCounter2?.setOnClickListener(listener)
        binding?.btnCounter3?.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            FIRST_INDEX -> binding?.btnCounter1?.text = text
            SECOND_INDEX -> binding?.btnCounter2?.text = text
            THIRD_INDEX -> binding?.btnCounter3?.text = text
        }
    }

    companion object {
        const val FIRST_INDEX = 0
        const val SECOND_INDEX = 1
        const val THIRD_INDEX = 2
    }
}
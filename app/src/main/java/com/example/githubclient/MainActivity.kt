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
            presenter.counterClick(it.id)
        }

        binding?.btnCounter1?.setOnClickListener(listener)
        binding?.btnCounter2?.setOnClickListener(listener)
        binding?.btnCounter3?.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> binding?.btnCounter1?.text = text
            1 -> binding?.btnCounter2?.text = text
            2 -> binding?.btnCounter3?.text = text
        }
    }
}
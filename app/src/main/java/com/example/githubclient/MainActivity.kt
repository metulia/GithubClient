package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.githubclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnCounter1.setOnClickListener(View.OnClickListener {
            presenter.buttonClick(MainPresenter.FIRST_BUTTON)
        })
        binding.btnCounter2.setOnClickListener(View.OnClickListener {
            presenter.buttonClick(MainPresenter.SECOND_BUTTON)
        })
        binding.btnCounter3.setOnClickListener(View.OnClickListener {
            presenter.buttonClick(MainPresenter.THIRD_BUTTON)
        })
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            MainPresenter.FIRST_BUTTON -> binding.btnCounter1.text = text
            MainPresenter.SECOND_BUTTON -> binding.btnCounter2.text = text
            MainPresenter.THIRD_BUTTON -> binding.btnCounter3.text = text
        }
    }
}
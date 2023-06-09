package com.example.githubclient

import com.example.githubclient.CountersModel.Companion.FIRST_INDEX
import com.example.githubclient.CountersModel.Companion.SECOND_INDEX
import com.example.githubclient.CountersModel.Companion.THIRD_INDEX

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun buttonClick(id: Int) {
        when (id) {
            R.id.btn_counter1 -> {
                val nextValue = model.next(FIRST_INDEX)
                view.setButtonText(FIRST_INDEX, nextValue.toString())
            }
            R.id.btn_counter2 -> {
                val nextValue = model.next(SECOND_INDEX)
                view.setButtonText(SECOND_INDEX, nextValue.toString())
            }
            R.id.btn_counter3 -> {
                val nextValue = model.next(THIRD_INDEX)
                view.setButtonText(THIRD_INDEX, nextValue.toString())
            }
        }
    }
}
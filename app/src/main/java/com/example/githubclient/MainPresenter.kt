package com.example.githubclient

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun buttonClick(id: Int) {
        when (id) {
            FIRST_BUTTON -> {
                val nextValue = model.next(CountersModel.FIRST_INDEX)
                view.setButtonText(CountersModel.FIRST_INDEX, nextValue.toString())
            }
            SECOND_BUTTON -> {
                val nextValue = model.next(CountersModel.SECOND_INDEX)
                view.setButtonText(CountersModel.SECOND_INDEX, nextValue.toString())
            }
            THIRD_BUTTON -> {
                val nextValue = model.next(CountersModel.THIRD_INDEX)
                view.setButtonText(CountersModel.THIRD_INDEX, nextValue.toString())
            }
        }
    }

    companion object {
        const val FIRST_BUTTON = 0
        const val SECOND_BUTTON = 1
        const val THIRD_BUTTON = 2
    }
}
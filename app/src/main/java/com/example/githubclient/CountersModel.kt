package com.example.githubclient

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    private fun getCurrent(index: Int): Int {
        return counters[index]
    }

    private fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

    private fun set(index: Int, value: Int) {
        counters[index] = value
    }
}
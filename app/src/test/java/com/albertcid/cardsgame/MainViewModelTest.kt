package com.albertcid.cardsgame

import com.albertcid.cardsgame.presentation.MainViewModelImpl
import org.junit.Before

class MainViewModelTest {

    private lateinit var sut: MainViewModelImpl

    @Before
    fun setUp() {
        sut = MainViewModelImpl()
    }
}
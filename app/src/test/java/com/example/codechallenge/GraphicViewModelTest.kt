package com.example.codechallenge

import com.example.codechallenge.data.dao.GraphicDao
import com.example.codechallenge.data.entities.GraphicItem
import com.example.codechallenge.presentation.viewmodel.GraphicViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GraphicViewModelTest {

    private val dao: GraphicDao = mock()

    private val underTest: GraphicViewModel by lazy {
        GraphicViewModel(dao)
    }

    @Test
    fun insertTest() = runTest {
        // Given
        val item = GraphicItem(
            "bbb001",
            30,
            45,
            35,
            80,
            2.5,
            "20/12/2022"
        )

        // When
        underTest.insertData(item)

        // Then
        verify(dao).insert(item)
    }

    @Test
    fun getTotalLitrosTest() = runTest {
        underTest.totalLitros
        verify(dao).getTotalLitros()
    }

    @Test
    fun getTotalAnimaisTest() = runTest {
        underTest.totalAnimais
        verify(dao).getTotalAnimais()
    }

    @Test
    fun getMediaPrimeiraTest() = runTest {
        underTest.mediaPrimeira
        verify(dao).getMediaPrimeira()
    }

    @Test
    fun getMediaSegundaTest() = runTest {
        underTest.mediaSegunda
        verify(dao).getMediaSegunda()
    }
}
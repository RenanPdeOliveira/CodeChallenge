package com.example.codechallenge

import android.widget.TextView
import com.example.codechallenge.home.data.dao.TableDao
import com.example.codechallenge.home.data.entities.TableItem
import com.example.codechallenge.home.presentation.HomeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class HomeViewModelTest {

    private val dao: TableDao = mock()

    private val textView: TextView = mock()

    private val underTest: HomeViewModel by lazy {
        HomeViewModel(dao)
    }

    @Test
    fun insertTest() = runTest {
        // Given
        val item = TableItem(
            "ddd0001",
            12345678,
            12345,
            "ANIMAL",
            "12/03/2022",
            123,
            1.20,
            1.30,
            2.50,
            "25/04/2022",
            123,
            ""
        )

        // When
        underTest.insertData(item)

        // Then
        verify(dao).insert(item)
    }

    @Test
    fun getTotalTest() = runTest {
        underTest.getTotal(textView)
        verify(dao).getTotal()
    }

    @Test
    fun getPrimeiraMaxTest() = runTest {
        underTest.getPrimeiraMax(textView)
        verify(dao).getPrimeiraMax()
    }

    @Test
    fun getPrimeiraMinTest() = runTest {
        underTest.getPrimeiraMin(textView)
        verify(dao).getPrimeiraMin()
    }

    @Test
    fun getSegundaMaxTest() = runTest {
        underTest.getSegundaMax(textView)
        verify(dao).getSegundaMax()
    }

    @Test
    fun getSegundaMinTest() = runTest {
        underTest.getSegundaMin(textView)
        verify(dao).getSegundaMin()
    }
}
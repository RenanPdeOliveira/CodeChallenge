package com.example.codechallenge.presentation.viewmodel

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.data.ActionType
import com.example.codechallenge.TableApplication
import com.example.codechallenge.data.TableType
import com.example.codechallenge.data.dao.TableDao
import com.example.codechallenge.data.entities.TableItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val tableDao: TableDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val listLiveData: LiveData<List<TableItem>> = tableDao.getAll()

    fun getTotal(view: TextView) {
        viewModelScope.launch(dispatcher) {
            val total = tableDao.getTotal()
            view.text = total.toString()
        }
    }

    fun getPrimeiraMax(view: TextView) {
        viewModelScope.launch(dispatcher) {
            val primeiraMax = tableDao.getPrimeiraMax()
            view.text = primeiraMax.toString()
        }
    }

    fun getPrimeiraMin(view: TextView) {
        viewModelScope.launch(dispatcher) {
            val primeiraMix = tableDao.getPrimeiraMin()
            view.text = primeiraMix.toString()
        }
    }

    fun getSegundaMax(view: TextView) {
        viewModelScope.launch(dispatcher) {
            val segundaMax = tableDao.getSegundaMax()
            view.text = segundaMax.toString()
        }
    }

    fun getSegundaMin(view: TextView) {
        viewModelScope.launch(dispatcher) {
            val segundaMix = tableDao.getSegundaMin()
            view.text = segundaMix.toString()
        }
    }

    fun execute(tableType: TableType) {
        when (tableType.actionType) {
            ActionType.CREATE.name -> {
                insertData(tableType.item)
            }
        }
    }

    fun insertData(item: TableItem) {
        viewModelScope.launch(dispatcher) {
            tableDao.insert(item)
        }
    }

    companion object {
        fun dataBaseInstance(application: Application): ViewModelProvider.Factory {
            val dataBase = (application as TableApplication).getDataBase()
            val dao = dataBase.tableDao()
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(dao) as T
                }
            }
        }
    }
}
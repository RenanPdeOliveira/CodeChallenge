package com.example.codechallenge.home.presentation

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.main.data.ActionType
import com.example.codechallenge.TableApplication
import com.example.codechallenge.home.data.TableType
import com.example.codechallenge.home.data.dao.TableDao
import com.example.codechallenge.home.data.entities.TableItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val tableDao: TableDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val listLiveData: LiveData<List<TableItem>> = tableDao.getAll()
    val getTotal: LiveData<Float> = tableDao.getTotal()
    val getPrimeiraMax: LiveData<Float> = tableDao.getPrimeiraMax()
    val getPrimeiraMin: LiveData<Float> = tableDao.getPrimeiraMin()
    val getSegundaMax: LiveData<Float> = tableDao.getSegundaMax()
    val getSegundaMin: LiveData<Float> = tableDao.getSegundaMin()

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
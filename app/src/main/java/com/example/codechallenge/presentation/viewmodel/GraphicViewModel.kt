package com.example.codechallenge.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.TableApplication
import com.example.codechallenge.data.ActionType
import com.example.codechallenge.data.GraphicType
import com.example.codechallenge.data.dao.GraphicDao
import com.example.codechallenge.data.entities.GraphicItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphicViewModel(
    private val graphicDao: GraphicDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val listLiveData: LiveData<List<GraphicItem>> = graphicDao.getAll()

    var totalLitros: Double = 0.0
    var totalAnimais: Int = 0
    var mediaPrimeira: Int = 0
    var mediaSegunda: Int = 0

    init {
        viewModelScope.launch(dispatcher) {
            totalLitros = graphicDao.getTotalLitros()
        }
        viewModelScope.launch(dispatcher) {
            totalAnimais = graphicDao.getTotalAnimais()
        }
        viewModelScope.launch(dispatcher) {
            mediaPrimeira = graphicDao.getMediaPrimeira()
        }
        viewModelScope.launch(dispatcher) {
            mediaSegunda = graphicDao.getMediaSegunda()
        }
    }

    fun execute(graphicType: GraphicType) {
        when (graphicType.actionType) {
            ActionType.CREATE.name -> {
                insertData(graphicType.item)
            }
        }
    }

    fun insertData(item: GraphicItem) {
        viewModelScope.launch(dispatcher) {
            graphicDao.insert(item)
        }
    }

    companion object {
        fun dataBaseInstance(application: Application): ViewModelProvider.Factory {
            val dataBase = (application as TableApplication).getDataBase()
            val dao = dataBase.graphicDao()
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return GraphicViewModel(dao) as T
                }
            }
        }
    }
}
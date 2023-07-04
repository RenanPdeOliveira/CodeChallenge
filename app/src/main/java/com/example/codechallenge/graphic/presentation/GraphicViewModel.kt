package com.example.codechallenge.graphic.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.TableApplication
import com.example.codechallenge.main.data.ActionType
import com.example.codechallenge.graphic.data.GraphicType
import com.example.codechallenge.graphic.data.dao.GraphicDao
import com.example.codechallenge.graphic.data.entities.GraphicItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphicViewModel(
    private val graphicDao: GraphicDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val listLiveData: LiveData<List<GraphicItem>> = graphicDao.getAll()
    val getTotalLitros: LiveData<Double> = graphicDao.getTotalLitros()
    val getTotalAnimais: LiveData<Int> = graphicDao.getTotalAnimais()
    val getMediaPrimeira: LiveData<Int> = graphicDao.getMediaPrimeira()
    val getMediaSegunda: LiveData<Int> = graphicDao.getMediaSegunda()

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
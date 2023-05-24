package com.example.codechallenge.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.db.williamchart.view.HorizontalBarChartView
import com.example.codechallenge.R
import com.example.codechallenge.data.ActionType
import com.example.codechallenge.data.GraphicType
import com.example.codechallenge.data.entities.CardItem
import com.example.codechallenge.data.entities.GraphicItem
import com.example.codechallenge.presentation.adapter.CardViewListAdapter
import com.example.codechallenge.presentation.adapter.GraphicItemListAdapter
import com.example.codechallenge.presentation.viewmodel.GraphicViewModel
import com.google.android.material.button.MaterialButtonToggleGroup
import org.json.JSONException

class GraphicFragment : Fragment() {

    private lateinit var horizontalBarChart: HorizontalBarChartView
    private lateinit var toggleButton: MaterialButtonToggleGroup
    private lateinit var rvCardView: RecyclerView
    private lateinit var progressBarGraphic: ProgressBar

    private val graphicAdapter = GraphicItemListAdapter()
    private val cardAdapter = CardViewListAdapter()

    private val viewModel: GraphicViewModel by viewModels {
        GraphicViewModel.dataBaseInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graphic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        horizontalBarChart = view.findViewById(R.id.horizontalBarChart)
        toggleButton = view.findViewById(R.id.toggleButtonGroup)
        rvCardView = view.findViewById(R.id.recyclerViewGraphic)
        progressBarGraphic = view.findViewById(R.id.progressBarGraphic)
        progressBarGraphic.visibility = View.VISIBLE

        getDataFromAPI()

        val totalLitros = viewModel.totalLitros
        val totalAnimais = viewModel.totalAnimais
        val mediaPrimeiro = viewModel.mediaPrimeira
        val mediaSegunda = viewModel.mediaSegunda

        val cardViewItem = listOf(
            CardItem("Total de Litros", totalLitros.toString()),
            CardItem("Total de Animais", totalAnimais.toString()),
            CardItem("Média da 1ª Ordenha", mediaPrimeiro.toString()),
            CardItem("Média da 2ª Ordenha", mediaSegunda.toString())
        )
        cardAdapter.submitList(cardViewItem)

        val animationDuration = 1000L

        val barChartCre = listOf(
            "18042" to 3.56f,
            "18015" to 3.57f,
            "18016" to 3.57f,
            "18032" to 3.61f,
            "18022" to 3.78f,
            "18031" to 3.96f,
            "18010" to 3.98f,
            "18034" to 4.28f,
            "18020" to 4.58f,
            "18012" to 4.68f
        )

        val barChartDec = listOf(
            "17005" to 1.13f,
            "19002" to 1.13f,
            "18014" to 1.12f,
            "19005" to 1.05f,
            "22001" to 1.01f,
            "18020" to 0.90f,
            "17005" to 0.88f,
            "18035" to 0.84f,
            "18048" to 0.80f,
            "18048" to 0.80f
        )

        horizontalBarChart.animation.duration = animationDuration
        horizontalBarChart.animate(barChartCre)

        toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.buttonCrescente -> {
                        horizontalBarChart.animate(barChartCre)
                    }

                    R.id.buttonDecrescente -> {
                        horizontalBarChart.animate(barChartDec)
                    }
                }
            } else {
                (toggleButton.checkedButtonId == View.NO_ID)
            }
        }
    }

    private fun getDataFromAPI() {
        val url =
            "https://script.googleusercontent.com/macros/echo?user_content_key=cEvLhSS6hyPK9wx8JrUhmKuVrUnZuPCFekF8uWc-kRKb43TFwCEaucPom08AhSgCEqqG8ICzssV6aapODkbKFabf8Z3dqAy_m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnPR729Gduikelqy-_uciOb0DR-JlxIoxPi19wFwAMS9LRuY5JCTixOX6zZtCzgz6tdUvEl9Ysi1CTB4Chx80mnow-ND8xhbJ2w&lib=MEdP25_Td9hxq5S-h0qpfggD72ntfRLNG"
        val queue = Volley.newRequestQueue(requireContext())

        val jsonObjectRequest =
            JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    progressBarGraphic.visibility = View.GONE
                    try {
                        val data = response.getJSONArray("user")
                        for (i in 1 until data.length()) {
                            val itemJson = data.getJSONObject(i)
                            val getItem = GraphicItem(
                                itemJson.getString("itemId"),
                                itemJson.getInt("itemTotalAnimais"),
                                itemJson.getInt("itemPrimeira"),
                                itemJson.getInt("itemSegunda"),
                                itemJson.getInt("itemTotalLitros"),
                                itemJson.getDouble("itemMedia"),
                                itemJson.getString("itemData")
                            )

                            val graphicType = GraphicType(getItem, ActionType.CREATE.name)
                            viewModel.execute(graphicType)

                            rvCardView.adapter = cardAdapter
                            rvCardView.layoutManager = LinearLayoutManager(
                                requireContext(),
                                RecyclerView.HORIZONTAL,
                                false
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            ) {
                Toast.makeText(requireContext(), "Fail to get data..", Toast.LENGTH_SHORT).show()
            }
        queue.add(jsonObjectRequest)
    }

    override fun onStart() {
        super.onStart()
        listUpdate()
    }

    private fun listUpdate() {
        val observer = Observer<List<GraphicItem>> { list ->
            graphicAdapter.submitList(list)
        }
        viewModel.listLiveData.observe(this, observer)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance() = GraphicFragment()
    }
}
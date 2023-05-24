package com.example.codechallenge.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.codechallenge.data.ActionType
import org.json.JSONException
import com.example.codechallenge.R
import com.example.codechallenge.data.TableType
import com.example.codechallenge.data.entities.TableItem
import com.example.codechallenge.presentation.adapter.TableItemListAdapter
import com.example.codechallenge.presentation.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var rvTable: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvTotal: TextView
    private lateinit var tvPrimeiraMax: TextView
    private lateinit var tvPrimeiraMin: TextView
    private lateinit var tvSegundaMax: TextView
    private lateinit var tvSegundaMin: TextView

    private val adapter = TableItemListAdapter()

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.dataBaseInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTotal = view.findViewById(R.id.textViewTotalNum)
        tvPrimeiraMax = view.findViewById(R.id.textViewPrimeiraMaxNum)
        tvPrimeiraMin = view.findViewById(R.id.textViewPrimeiraMinNum)
        tvSegundaMax = view.findViewById(R.id.textViewSegundaMaxNum)
        tvSegundaMin = view.findViewById(R.id.textViewSegundaMinNum)
        rvTable = view.findViewById(R.id.recyclerViewTable)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        viewModel.getTotal(tvTotal)
        viewModel.getPrimeiraMax(tvPrimeiraMax)
        viewModel.getPrimeiraMin(tvPrimeiraMin)
        viewModel.getSegundaMax(tvSegundaMax)
        viewModel.getSegundaMin(tvSegundaMin)

        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        val url =
            "https://script.googleusercontent.com/macros/echo?user_content_key=8gMm6FH8DvsaSt-rwGf-R6NZW0uStvHC4kvNjUPgBCxWOjKxEC-kxxrKNAmvaQN8x2Smm6pNc3SQp3BxLL42O0ACTbYwHsJpm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnApN5vw-0gvSP10ZY_SurYy407SWiO4PZcxJI7Xzyajc9W6N6ks6rIw-3Bdi8F4ire5KutbgYPVr7Cn3T08B8PvlL-9oR1QOyQ&lib=M2SVShSucy4x3bmN7HbdgvwD72ntfRLNG"
        val queue = Volley.newRequestQueue(requireContext())

        val jsonObjectRequest =
            JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    progressBar.visibility = View.GONE
                    try {
                        val data = response.getJSONArray("user")
                        for (i in 1 until data.length()) {
                            val itemJson = data.getJSONObject(i)
                            val getItem = TableItem(
                                itemJson.getString("itemId"),
                                itemJson.getLong("itemMicrochip"),
                                itemJson.getInt("itemAnimal"),
                                itemJson.getString("itemNome"),
                                itemJson.getString("itemParto"),
                                itemJson.getInt("itemBaia"),
                                itemJson.getDouble("itemPrimeiro"),
                                itemJson.getDouble("itemSegundo"),
                                itemJson.getDouble("itemTotal"),
                                itemJson.getString("itemControle"),
                                itemJson.getInt("itemDel"),
                                itemJson.getString("itemObs")
                            )

                            val tableType = TableType(getItem, ActionType.CREATE.name)
                            viewModel.execute(tableType)

                            rvTable.adapter = adapter
                            rvTable.layoutManager = LinearLayoutManager(requireContext())
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
        val observer = Observer<List<TableItem>> { list ->
            adapter.submitList(list)
        }
        viewModel.listLiveData.observe(this, observer)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
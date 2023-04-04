package com.example.earthquakes.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakes.R
import com.example.earthquakes.adapter.AdapterClass
import com.example.earthquakes.helperClass.properties
import com.example.earthquakes.networkConnection.Connection
import com.example.earthquakes.retrofit_Instances.InstanceForMonth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LastMonthFragment : Fragment() {

    private var mList : List<properties> = ArrayList()
    private lateinit var mAdapter : AdapterClass
    private lateinit var mRecycler : RecyclerView
    private lateinit var mConnect : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_last_month_, container, false)

        mRecycler = view.findViewById(R.id.recyclerView1)

        mConnect = view.findViewById(R.id.connection)

        val mNetworkConnection = Connection(requireContext())

        mNetworkConnection.observe(viewLifecycleOwner) { isConnected ->
            if(isConnected){

                mConnect.visibility = View.GONE
                setAdapter()
                fetchData()
            }
            else{
                mConnect.visibility = View.VISIBLE
            }
        }
        return view
    }

    private fun setAdapter(){

        mRecycler.layoutManager = LinearLayoutManager(requireContext()
            , LinearLayoutManager.VERTICAL,false)
        mAdapter = AdapterClass(requireContext() , mList)
        mRecycler.adapter = mAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchData(){

        CoroutineScope(Dispatchers.IO).launch {
            val response = InstanceForMonth.retrofit.getMonthlyUpdate()

            if (response.isSuccessful){
                val data = response.body()!!

                (mList as ArrayList).clear()
                data.features.let {
                    (mList as ArrayList).addAll(it)
                }

                withContext(Dispatchers.Main){
                    mAdapter.notifyDataSetChanged()
                }
            }
            else{
                Toast.makeText(requireContext(),
                    "Error in fetching data",
                    Toast.LENGTH_SHORT)
                    .show()

                Log.d("res","Getting data : $response")
            }
        }
    }
}
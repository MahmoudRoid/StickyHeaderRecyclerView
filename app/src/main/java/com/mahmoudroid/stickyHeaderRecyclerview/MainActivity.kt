package com.mahmoudroid.stickyHeaderRecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudroid.stickyHeaderRecyclerview.adapter.NameAdapter
import com.mahmoudroid.stickyHeaderRecyclerview.adapter.StickyHeaderDecoration
import com.mahmoudroid.stickyHeaderRecyclerview.databinding.ActivityMainBinding
import com.mahmoudroid.stickyHeaderRecyclerview.ext.setDivider
import com.mahmoudroid.stickyHeaderRecyclerview.model.DummyModel
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val dummyList = prepareDummyData()
    private val nameAdapter by lazy { NameAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
    }

    private fun initRecycler() {

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setDivider(R.drawable.list_divider)
            addItemDecoration(StickyHeaderDecoration(nameAdapter, binding.root))
            nameAdapter.dummyData = dummyList.groupBy { it.name.first().toUpperCase() }.toSortedMap()
            adapter = nameAdapter
        }

    }

    private fun prepareDummyData(): MutableList<DummyModel> {
        val list = mutableListOf<DummyModel>()
        list.add(DummyModel("Ali", Random.nextInt(17,50)))
        list.add(DummyModel("Mohammad", Random.nextInt(17,50)))
        list.add(DummyModel("Reza", Random.nextInt(17,50)))
        list.add(DummyModel("Hossein", Random.nextInt(17,50)))
        list.add(DummyModel("Farhad", Random.nextInt(17,50)))
        list.add(DummyModel("Felix", Random.nextInt(17,50)))
        list.add(DummyModel("Mahmoudreza", Random.nextInt(17,50)))
        list.add(DummyModel("Hooman", Random.nextInt(17,50)))
        list.add(DummyModel("Jack", Random.nextInt(17,50)))
        list.add(DummyModel("John", Random.nextInt(17,50)))
        list.add(DummyModel("Ashley", Random.nextInt(17,50)))
        list.add(DummyModel("Mason", Random.nextInt(17,50)))
        list.add(DummyModel("Rahim", Random.nextInt(17,50)))
        list.add(DummyModel("Kai", Random.nextInt(17,50)))
        list.add(DummyModel("Reece", Random.nextInt(17,50)))
        list.add(DummyModel("Rio", Random.nextInt(17,50)))
        list.add(DummyModel("Ben", Random.nextInt(17,50)))
        list.add(DummyModel("Bernard", Random.nextInt(17,50)))
        list.add(DummyModel("Tammy", Random.nextInt(17,50)))
        list.add(DummyModel("Adam", Random.nextInt(17,50)))
        return list
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
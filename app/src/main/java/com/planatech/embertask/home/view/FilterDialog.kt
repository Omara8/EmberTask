package com.planatech.embertask.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.planatech.embertask.R
import com.planatech.embertask.databinding.DialogFilterBinding
import com.planatech.embertask.home.model.Source
import kotlinx.android.synthetic.main.dialog_filter.*

class FilterDialog(
    private val sources: List<Source>,
    private val filterCallback: (country: String?, source: String) -> Unit
) : DialogFragment() {

    private var binding: DialogFilterBinding? = null
    private var sourcesList: MutableList<String>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.filterDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_filter, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (source in sources)
            sourcesList?.add(source.name)

        val sourcesAdapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                sourcesList as MutableList<String>
            )
        binding?.sourcesAdapter = sourcesAdapter

        val countriesAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.countries_array,
            android.R.layout.simple_spinner_item
        )
        binding?.countriesAdapter = countriesAdapter

        cancel_button?.setOnClickListener {
            cancelClicked()
        }
        filter_button?.setOnClickListener {
            filterClicked()
        }
    }

    private fun filterClicked() {
        var country: String?
        var source: String
        if (countries_toggle?.isChecked == true) {
            source = ""
            country = countries_text_view?.text.toString()
            searchWithFilters(country, source)
        } else if (sources_toggle?.isChecked == true) {
            val selectedSourcePosition = sourcesList?.indexOf(sources_text_view?.text.toString())
            source = sources[selectedSourcePosition!!].id
            country = null
            searchWithFilters(country, source)
        }
    }

    private fun searchWithFilters(country: String?, source: String) {
        filterCallback(country, source)
        dismiss()
    }

    private fun cancelClicked() {
        dismiss()
    }

}
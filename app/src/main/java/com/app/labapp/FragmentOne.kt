package com.app.labapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class FragmentOne : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_one, container, false)
        return rootView
    }

    override fun onPause() {
        super.onPause()
        Log.d("DEBUG", "this is paused")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DEBUG", "this is resumed")
    }
}

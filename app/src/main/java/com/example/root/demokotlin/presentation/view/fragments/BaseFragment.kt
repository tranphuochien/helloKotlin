package com.example.root.demokotlin.presentation.view.fragments

import android.app.Fragment
import android.widget.Toast

/**
 * Created by root on 04/08/2017.
 */
abstract class BaseFragment : Fragment() {
    protected fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}

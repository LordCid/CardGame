package com.albertcid.cardsgame.presentation.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.albertcid.cardsgame.R

class EndGameDialogFragment : DialogFragment() {

    companion object{
        fun newInstance(isWinner: Boolean): EndGameDialogFragment {
            val instance = EndGameDialogFragment()
            val args = Bundle()
            args.putBoolean(ARG_WIN_INDICATOR, isWinner)
            instance.arguments = args
            return instance
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val wonGame = arguments?.getBoolean(ARG_WIN_INDICATOR) ?:false
        val messageRes = if(wonGame)R.string.win_game else R.string.lose_game
        return activity?.let {
            return AlertDialog.Builder(it)
                .setMessage(messageRes)
                .setPositiveButton(R.string.restart) { _, _ -> dismiss() }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
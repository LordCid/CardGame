package com.albertcid.cardsgame.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.albertcid.cardsgame.R
import com.albertcid.cardsgame.domain.toLiteral
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.NewInstanceFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()
        actionButtons()
    }

    private fun actionButtons(){
        restart_button.setOnClickListener { viewModel.restartGame() }
        play_card_button.setOnClickListener { viewModel.playRound() }
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[MainViewModelImpl::class.java]
        viewModel.viewState.observe(::getLifecycle, ::updateUI)
        viewModel.restartGame()
    }

    private fun updateUI(screenState: MainViewState) {
        with((screenState as MainViewState.ShowGameStatus).gameStatus) {
            round_number_tv.text = currentRound.toString()
            pilecards_value_tv.text = totalOpponentDiscardPile.toString()
            discardpile_opponents_value_tv.text = totalOpponentDiscardPile.toString()
            discardpile_value_tv.text = totalUsersDiscardPile.toString()
            opponents_card_tv.text = opponentCardPlayed.toLiteral()
            users_card_tv.text = userCardPlayed.toLiteral()
            winner_tv.text = if (isUserWinnerOfRound) {
                getString(R.string.user_winner)
            } else {
                getString(R.string.user_lose)
            }
        }
    }

}
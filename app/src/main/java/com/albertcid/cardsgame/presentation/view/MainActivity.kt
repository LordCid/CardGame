package com.albertcid.cardsgame.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.albertcid.cardsgame.R
import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.toLiteral
import com.albertcid.cardsgame.presentation.state.MainViewState
import com.albertcid.cardsgame.presentation.viewmodel.MainViewModel
import com.albertcid.cardsgame.presentation.viewmodel.MainViewModelImpl
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

const val ARG_WIN_INDICATOR = "win_indicator"

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

    private fun actionButtons() {
        restart_btn.setOnClickListener { viewModel.restartGame() }
        play_btn.setOnClickListener { viewModel.playRound() }
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
        when (screenState) {
            is MainViewState.ShowGameStatus -> {
                setDataToUI(screenState.gameStatus)
            }
            is MainViewState.GameFinished -> {
                with(screenState) {
                    setDataToUI(gameStatus)
                    showFinishDialog(isPlayerWinner)
                }

            }
        }
    }

    private fun setDataToUI(gameStatus: GameStatus) {
        with(gameStatus) {
            round_val_tv.text = currentRound.toString()
            pile_val_tv.text = totalUsersCardPile.toString()
            dis_op_val_tv.text = totalOpponentDiscardPile.toString()
            discard_val_tv.text = totalUsersDiscardPile.toString()
            opponents_card_tv.text = opponentCardPlayed.toLiteral()
            users_card_tv.text = userCardPlayed.toLiteral()
            winner_tv.text = getWinRoundMessage(currentRound > 0, isUserWinnerOfRound)
        }
    }

    private fun getWinRoundMessage(notFirstRound: Boolean, userWinner: Boolean): String {
        return if (notFirstRound) {
            if (userWinner) {
                getString(R.string.user_winner)
            } else {
                getString(R.string.user_lose)
            }
        } else ""
    }

    private fun showFinishDialog(playerWinner: Boolean) {
        val dialog = EndGameDialogFragment.newInstance(playerWinner)
        dialog.show(supportFragmentManager, "end")
    }

}
package com.albertcid.cardsgame.presentation.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.albertcid.cardsgame.R
import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.model.CardSuit
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
            winner_round_tv.text = getWinRoundMessage(currentRound > 0, isUserWinnerOfRound)
            showSuitPriority(suitPriority)
        }
    }

    private fun getSuitImageDrawableId(suit: CardSuit): Int{
        return when(suit){
            CardSuit.SPADES -> R.drawable.ic_pieza_de_poquer_de_espadas
            CardSuit.DIAMONDS -> R.drawable.ic_pieza_de_poquer_de_diamantes
            CardSuit.CLUBS -> R.drawable.ic_chip_con_club
            CardSuit.HEARTS -> R.drawable.ic_pieza_de_poquer_de_corazon
        }
    }

    private fun showSuitPriority(suitPriority: List<CardSuit>){
        prio1.setImageDrawable(
            ContextCompat.getDrawable(this, getSuitImageDrawableId(suitPriority[0])))
        prio2.setImageDrawable(
            ContextCompat.getDrawable(this, getSuitImageDrawableId(suitPriority[1])))
        prio3.setImageDrawable(
            ContextCompat.getDrawable(this, getSuitImageDrawableId(suitPriority[2])))
        prio4.setImageDrawable(
            ContextCompat.getDrawable(this, getSuitImageDrawableId(suitPriority[3])))
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
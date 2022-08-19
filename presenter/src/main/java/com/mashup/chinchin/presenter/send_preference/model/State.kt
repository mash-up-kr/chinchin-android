package com.mashup.chinchin.presenter.send_preference.model

class StateManager {
    var currentState: State
    val editState: State
    val visibleState: State
    fun onBackButton() {
        currentState.onBackButton()
    }

    fun onClickButton() {
        (currentState as? State.EditMode)?.let {
            it.onClickButton()
        }
    }

    fun onDeleteButton() {
        (currentState as? State.VisibleState)?.let {
            it.onDeleteButton()
        }
    }

    init {
        visibleState = State.VisibleState(this)
        editState = State.EditMode(this)
        currentState = visibleState
    }
}

sealed class State {
    abstract fun onBackButton()
    class EditMode(private val stateManager: StateManager) : State() {
        override fun onBackButton() {
            stateManager.currentState = stateManager.visibleState
        }

        fun onClickButton() {

        }


    }

    class VisibleState(private val stateManager: StateManager) : State() {
        override fun onBackButton() {
            stateManager.currentState = stateManager.editState
        }

        fun onDeleteButton() {

        }
    }
}

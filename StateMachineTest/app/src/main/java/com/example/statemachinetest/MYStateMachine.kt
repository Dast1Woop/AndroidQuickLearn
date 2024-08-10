package com.example.statemachinetest

import android.util.Log
import android.widget.Toast
import com.tinder.StateMachine

sealed class WaterState {
    object Solid : WaterState()
    object Liquid : WaterState()
    object Gas : WaterState()
}

sealed class WaterEvent {
    object RongHua : WaterEvent()
    object QiHua : WaterEvent()
    object YeHua : WaterEvent()
}

sealed class WaterEventSideEffect {
    object RongHuaSideEffect : WaterEventSideEffect()
    object QiHuaSideEffect : WaterEventSideEffect()
    object YeHuaSideEffect : WaterEventSideEffect()
}

class MyStateMachine {

    val stateM = StateMachine.create<WaterState, WaterEvent, WaterEventSideEffect> {
        initialState(WaterState.Solid)
        state<WaterState.Solid> {

            on<WaterEvent.RongHua> {
                transitionTo(WaterState.Liquid, WaterEventSideEffect.RongHuaSideEffect)
            }
        }

        state<WaterState.Liquid> {
            on<WaterEvent.QiHua> {
                transitionTo(WaterState.Gas, WaterEventSideEffect.QiHuaSideEffect)
            }
        }

        state<WaterState.Gas> {
            on<WaterEvent.YeHua> {
                transitionTo(WaterState.Liquid, WaterEventSideEffect.YeHuaSideEffect)
            }
        }

        onTransition {
            val validTransition = it as? StateMachine.Transition.Valid ?: return@onTransition
            when(validTransition.sideEffect) {
                 WaterEventSideEffect.RongHuaSideEffect -> {
                    Log.d("MyStateMachine", "融化了")
                }
                 WaterEventSideEffect.QiHuaSideEffect -> {
                    Log.d("MyStateMachine", "蒸发了")
                }
                 WaterEventSideEffect.YeHuaSideEffect -> {
                    Log.d("MyStateMachine", "液化了")

                }
                else -> {
                    Log.d("MyStateMachine", "els")
                }
            }
        }
    }

}
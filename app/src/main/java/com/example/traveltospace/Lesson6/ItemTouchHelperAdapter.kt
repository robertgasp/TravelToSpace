package com.example.traveltospace.Lesson6


interface ItemTouchHelperAdapter {
    fun onNoteMove(fromPosition:Int,toPosition:Int)
    fun onNoteDismiss(position: Int)
}
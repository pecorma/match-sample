package com.mjpecora.app.domain.model

data class MarsRover(
    val roverName: String = UNKNOWN,
    val imgSrc: String,
    val date: String = UNKNOWN,
    val sol: Int = 0
) {
    companion object {
        private const val UNKNOWN = "unknown"
    }
}
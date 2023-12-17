package entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Seans(var _movie: String, var _date: LocalDate, var _time: LocalTime, var _places: MutableMap<String,String>){
        var cinema: String
                get() {
                        return _movie
                }
                set(value) {
                        _movie = value
                }
        var date: LocalDate
                get() {
                        return _date
                }
                set(value) {
                        _date = value
                }
        var time: LocalTime
                get() {
                        return _time
                }
                set(value) {
                        _time = value
                }




        override fun toString(): String {
                return "Seans(movie=$_movie, date=$_date, time=$_time)"
        }
}
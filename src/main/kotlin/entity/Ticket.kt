package entity

import java.time.LocalDate
import java.time.LocalTime

class Ticket(var _movie: String, var _date: LocalDate, var _time: LocalTime, var place: Int) {



    override fun toString(): String {
        return "Ticket(movie=$_movie, date=$_date, time=$_time, place=${place})"
    }
}
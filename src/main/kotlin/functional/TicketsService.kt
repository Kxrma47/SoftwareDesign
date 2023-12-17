package functional

import app.StartApp
import app.StartApp.Companion.data
import entity.Movie
import entity.Seans
import entity.Ticket
import util.Util
import java.lang.Exception

class TicketsService {
    data class Transfer(val remainTickets: List<Ticket>, val soldTickets: List<Ticket>, val movies: List<Movie>, val changedSeans: List<Seans>)
    companion object{

        private var buyTickets:  MutableList<Ticket> = mutableListOf()
        fun buyTicket() {
            println("Напишите цифру фильма, билет на который хотят купить:")
            var count = 0
            for (sean in data.seans) {
                println("${++count} ${sean._movie}")
            }
            var buyTicket : Ticket? = null
            var movieChoose: Int?
            do {
                try {
                    val movieChooseInput = readLine()
                    movieChoose = movieChooseInput?.toIntOrNull()

                    if (movieChoose == null || movieChooseInput.isNullOrBlank() || movieChooseInput.toIntOrNull() == null || movieChoose < 1 || movieChoose > 10) {
                        println("Некорректный ввод. Пожалуйста, введите снова.")
                        movieChoose = null
                        continue
                    }
                } catch (e: Exception) {
                    println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                    movieChoose = null
                }
            } while (movieChoose == null)
            var time = 0
            var chooseSeans : Seans? = null
            for (sean in data.seans) {
                ++time
                if (time == movieChoose) {
                    chooseSeans = sean
                    break
                }
            }
            if (chooseSeans != null) {
                for (place in chooseSeans._places) {
                    println(place)
                }
                println("Напишите цифру места, который хотят купить:")
                var choosePlace : Int?
                do {
                    try {
                        val choosePlaceInput = readLine()
                        choosePlace = choosePlaceInput?.toIntOrNull()

                        if ((choosePlace == null) || choosePlaceInput.isNullOrBlank() || (choosePlaceInput.toIntOrNull() == null) || (choosePlace < 1) || (choosePlace > 10) || (chooseSeans._places[choosePlace.toString()] == "Booked")
                        ) {
                            println("Некорректный ввод. Пожалуйста, введите снова.")
                            choosePlace = null
                            continue
                        }
                    } catch (e: Exception) {
                        println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                        choosePlace = null
                    }
                } while (choosePlace == null)

                for (ticket in data.tickets) {
                    if (ticket._movie == chooseSeans._movie && ticket._time == chooseSeans._time && choosePlace == ticket.place) {
                        buyTicket = ticket
                        data.tickets.remove(ticket)
                        break
                    }
                }
                for (sean in data.seans) {
                    if (buyTicket != null) {
                        if (sean._movie == buyTicket._movie && sean._time == buyTicket._time && sean._date == buyTicket._date) {
                            sean._places.replace(buyTicket.place.toString(), "Booked")
                            break
                        }
                    }
                }
                if (buyTicket != null) {
                    buyTickets.add(buyTicket)
                }

            }
            Util.saveData()
        }

        fun returnTicket() {
            buyTickets = mutableListOf()
            Util.readSoldTicketsFromCsv(buyTickets)
            if (buyTickets.isEmpty()) {
                println("Нет купленых билетов")
            } else {
                var count  = 0
                println("Напишите номер билета, который хотят вернуть")
                for (ticket in buyTickets) {
                    println("${++count} $ticket")
                }
                var ticketChoose: Int?
                var retTicket: Ticket?
                do {
                    try {
                        val ticketChooseInput = readLine()
                        ticketChoose = ticketChooseInput?.toIntOrNull()

                        if (ticketChoose == null || ticketChooseInput.isNullOrBlank() || ticketChooseInput.toIntOrNull() == null || ticketChoose < 1 || ticketChoose > buyTickets.size) {
                            println("Некорректный ввод. Пожалуйста, введите снова.")
                            ticketChoose = null
                            continue
                        }
                    } catch (e: Exception) {
                        println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                        ticketChoose = null
                    }
                } while (ticketChoose == null)
                for (i in 1..buyTickets.size) {
                    if (i == ticketChoose) {
                        retTicket = buyTickets[ticketChoose - 1]
                        for (sean in data.seans) {
                            if (sean._movie == retTicket._movie && sean._time == retTicket._time && sean._date == retTicket._date) {
                                sean._places.replace(retTicket.place.toString(), "FREE")
                                buyTickets.remove(retTicket)
                                data.tickets.add(retTicket)
                                break
                            }
                        }
                    }
                }
            }
            Util.saveData()
        }

        fun showPlaces() {
            println("Напишите цифру сеанса, для просмотра его мест:")
            var count = 0
            var seansChoose: Int?
            for (sean in data.seans) {
                println("${++count} $sean")
            }
            do {
                try {
                    val seansChooseInput = readLine()
                    seansChoose = seansChooseInput?.toIntOrNull()

                    if (seansChoose == null || seansChooseInput.isNullOrBlank() || seansChooseInput.toIntOrNull() == null || seansChoose < 1 || seansChoose > data.seans.size) {
                        println("Некорректный ввод. Пожалуйста, введите снова.")
                        seansChoose = null
                        continue
                    }
                } catch (e: Exception) {
                    println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                    seansChoose = null
                }
            } while (seansChoose == null)
            count = 0

            for (sean in data.seans) {
                ++count
                if (count == seansChoose) {
                    println(sean._places)
                }
            }
        }

        fun sendDataToSave() : Transfer {
            return Transfer(data.tickets, buyTickets, data.movies, data.seans)
        }
    }

}
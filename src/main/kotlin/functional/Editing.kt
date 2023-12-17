package functional

import entity.Movie
import entity.Seans
import util.Util
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Editing {
    companion object{
        val transfer : TicketsService.Transfer = TicketsService.sendDataToSave()
        fun edit(data : Util.DataLists) {
            println("Что вы хотите изменить?")
            println("1.Фильм")
            println("2.Сеанс")
            var choice: Int?
            do {
                try {
                    val choiceInput = readLine()
                    choice = choiceInput?.toIntOrNull()

                    if (choice == null || choiceInput.isNullOrBlank() || choiceInput.toIntOrNull() == null || choice < 1 || choice > 2) {
                        println("Некорректный ввод. Пожалуйста, введите снова.")
                        choice = null
                        continue
                    }
                } catch (e: Exception) {
                    println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                    choice = null
                }
            } while (choice == null)

            when(choice) {
                1 -> {
                    println("1.Название фильма")
                    println("2.Режиссёр")
                    println("3.Год выпуска")
                    var choiceMovie: Int?
                    do {
                        try {
                            val choiceMovieInput = readLine()
                            choiceMovie = choiceMovieInput?.toIntOrNull()

                            if (choiceMovie == null || choiceMovieInput.isNullOrBlank() || choiceMovieInput.toIntOrNull() == null || choiceMovie < 1 || choiceMovie > 3) {
                                println("Некорректный ввод. Пожалуйста, введите снова.")
                                choiceMovie = null
                                continue
                            }
                        } catch (e: Exception) {
                            println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                            choiceMovie = null
                        }
                    } while (choiceMovie == null)
                    var count: Int = 0
                    for (movie in data.movies) {
                        println("${++count}. ${movie}")
                    }
                    var movieChoose: Int?
                    do {
                        try {
                            val movieChooseInput = readLine()
                            movieChoose = movieChooseInput?.toIntOrNull()

                            if (movieChoose == null || movieChooseInput.isNullOrBlank() || movieChooseInput.toIntOrNull() == null || movieChoose < 1 || movieChoose > data.movies.size) {
                                println("Некорректный ввод. Пожалуйста, введите снова.")
                                movieChoose = null
                                continue
                            }
                        } catch (e: Exception) {
                            println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                            movieChoose = null
                        }
                    } while (movieChoose == null)
                    count = 0
                    for (movie in data.movies) {
                        ++count
                        if (count == movieChoose) {
                            editMovies(movie, choiceMovie, data)
                            break
                        }
                    }
                }
                2 -> {
                    println("1.Название фильма сеанса")
                    println("2.Дата сеанса")
                    println("3.Время сеанса")

                    var choiceSeans: Int?
                    do {
                        try {
                            val choiceMovieInput = readLine()
                            choiceSeans = choiceMovieInput?.toIntOrNull()

                            if (choiceSeans == null || choiceMovieInput.isNullOrBlank() || choiceMovieInput.toIntOrNull() == null || choiceSeans < 1 || choiceSeans > 3) {
                                println("Некорректный ввод. Пожалуйста, введите снова.")
                                choiceSeans = null
                                continue
                            }
                        } catch (e: Exception) {
                            println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                            choiceSeans = null
                        }
                    } while (choiceSeans == null)
                    var count: Int = 0
                    for (sean in data.seans) {
                        println("${++count}. ${sean}")
                    }
                    var seansChoose: Int?
                    do {
                        try {
                            val movieChooseInput = readLine()
                            seansChoose = movieChooseInput?.toIntOrNull()

                            if (seansChoose == null || movieChooseInput.isNullOrBlank() || movieChooseInput.toIntOrNull() == null || seansChoose < 1 || seansChoose > data.seans.size) {
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
                            editSeans(sean, choiceSeans, data)
                            break
                        }
                    }


                }
            }

        }
        private fun editMovies(movie: Movie, choice: Int, data: Util.DataLists) {
            var title: String?
            var director: String?
            var year: Int?
            when(choice) {
                1 -> {
                    println("Напишите название фильма, на который вы хотите поменять название этого: \n")
                    do {
                        try {
                            title = readLine()
                            if (title.isNullOrEmpty()) {
                                println("Название фильма не может быть пустым. Пожалуйста, введите снова.")
                            }
                        } catch (e: Exception) {
                            println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                            title = null
                        }
                    } while (title.isNullOrEmpty())
                    val temp = movie._title
                    movie._title = title
                    for (sean in data.seans) {
                        if (sean._movie == temp) {
                            sean._movie = title
                        }
                    }
                    for (ticket in data.tickets) {
                        if (ticket._movie == temp) {
                            ticket._movie = title
                        }
                    }
                }
                2 -> {
                    println("Напишите режиссёра, на которого вы хотите поменять этого: \n")
                    do {
                        try {
                            director = readLine()
                            if (director.isNullOrEmpty()) {
                                println("Имя режиссёра не может быть пустым. Пожалуйста, введите снова.")
                            }
                        } catch (e: Exception) {
                            println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                            director = null
                        }
                    } while (director.isNullOrEmpty())

                    movie._director = director
                }
                3 -> {
                    println("Введите год выпуска фильма:")
                    do {
                        try {
                            val yearInput = readLine()
                            year = yearInput?.toIntOrNull()

                            if (year == null || yearInput.isNullOrBlank() || yearInput.toIntOrNull() == null || year < 1900 || year > 2023) {
                                println("Некорректный ввод года. Год должен быть числом от 1900 до 2023. Пожалуйста, введите снова.")
                                year = null
                                continue
                            }
                        } catch (e: Exception) {
                            println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                            year = null
                        }
                    } while (year == null)

                    movie._year = year.toString()
                }
            }

        }

        private fun editSeans(seans: Seans, choice: Int, data: Util.DataLists) {
            var movie: String
            var date: LocalDate
            var time: LocalTime
            when(choice) {
                1 -> {
                    println("Введите название фильма:")
                    do {
                        try {
                            movie = readLine()?.takeIf { it.isNotBlank() }
                                ?: throw IllegalArgumentException("Название фильма не может быть пустым. Пожалуйста, введите снова.")
                        } catch (e: Exception) {
                            println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                            movie = ""
                        }
                    } while (movie.isBlank())
                    val temp = seans._movie
                    seans._movie = movie
                    for (ticket in data.tickets) {
                        if (ticket._movie == temp) {
                            ticket._movie = movie
                        }
                    }
                    for (mov in data.movies) {
                        if (mov._title == temp) {
                            mov._title = movie
                        }
                    }
                    for (sold in transfer.soldTickets) {
                        if (sold._movie == temp) {
                            sold._movie = movie
                        }
                    }

                }
                2 -> {
                    println("Введите дату сеанса в формате dd-MM-yyyy:")

                    do {
                        date = try {
                            val dateInput = readLine()?.takeIf { it.isNotBlank() }
                                ?: throw IllegalArgumentException("Дата сеанса не может быть пустой. Пожалуйста, введите снова.")
                            LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        } catch (e: Exception) {
                            println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                            LocalDate.MIN
                        }
                    } while (date == LocalDate.MIN)
                    val temp = seans._movie
                    seans._date = date
                    for (ticket in data.tickets) {
                        if (ticket._movie == temp) {
                            ticket._date = date
                        }
                    }
                    for (sold in transfer.soldTickets) {
                        if (sold._movie == temp) {
                            sold._date = date
                        }
                    }
                }
                3 -> {
                    println("Введите время сеанса в формате HH:mm:ss")
                    do {
                        try {
                            val timeInput = readLine()?.takeIf { it.isNotBlank() }
                                ?: throw IllegalArgumentException("Время сеанса не может быть пустым. Пожалуйста, введите снова.")
                            time = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm:ss"))
                        } catch (e: Exception) {
                            println("Произошла ошибка: ${e.message}. Пожалуйста, введите снова.")
                            time = LocalTime.MIN
                        }
                    } while (time == LocalTime.MIN)
                    val temp = seans._movie
                    seans._time = time
                    for (ticket in data.tickets) {
                        if (ticket._movie == temp) {
                            ticket._time = time
                        }
                    }
                    for (sold in transfer.soldTickets) {
                        if (sold._movie == temp) {
                            sold._time = time
                        }
                    }
                }
            }
        }
    }
}
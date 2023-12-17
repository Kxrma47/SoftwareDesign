package util

import entity.Movie
import entity.Seans
import entity.Ticket
import functional.TicketsService
import java.io.File
import java.io.FileWriter
import java.nio.charset.StandardCharsets
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Util {
    data class DataLists(val tickets: MutableList<Ticket>, val movies: MutableList<Movie>, val seans: MutableList<Seans>)
    companion object {
        private const val FILEPATHTICKET = "data/tickets.csv"
        private const val FILEPATHMOVIE = "data/movie.csv"
        private const val FILEPATHSEANS = "data/seans.csv"
        private const val FILE_PATH_SOLD_TICKETS = "data/soldTickets.csv"
        private var seansesDates : List<String> = emptyList()
        private val movieTitles = listOf("The Godfather", "Inception", "Titanic", "The Shawshank Redemption",
            "Pulp Fiction", "The Dark Knight", "Forrest Gump", "Fight Club", "The Matrix", "The Lord of the Rings")
        private val seansList = mutableListOf<Seans>()
        private val ticketsList = mutableListOf<Ticket>()
        private val moviesList = mutableListOf<Movie>()
        private var soldTickets = mutableListOf<Ticket>()
        fun checkBeforeStart() {
            val fileTicket = File(FILEPATHTICKET)
            val fileMovie = File(FILEPATHMOVIE);
            val fileSeans = File(FILEPATHSEANS)
            val fileSoldTickets = File(FILE_PATH_SOLD_TICKETS)
            if (!fileSeans.exists()) {
                createAndWriteFile(FILEPATHSEANS, fileSeans)
                createStartData(FILEPATHSEANS, fileSeans)
            }
            if (!fileMovie.exists()) {
                createAndWriteFile(FILEPATHMOVIE, fileMovie)
                createStartData(FILEPATHMOVIE, fileMovie)
            }
            if (!fileTicket.exists()) {
                createAndWriteFile(FILEPATHTICKET, fileTicket)
                createStartData(FILEPATHTICKET, fileTicket)
            }
            if (!fileSoldTickets.exists()) {
                createAndWriteFile(FILEPATHTICKET, fileSoldTickets)
            }

        }
        fun readDataFromCsv() : DataLists {
            File(FILEPATHSEANS).bufferedReader().use { reader ->
                val headers = reader.readLine().split(";")
                reader.forEachLine { line ->
                    val columns = line.split(";")
                    val formattedDateTimeFirst = columns[1]
                    val formattedDateTimeSecond = columns[2]
                    val formatterFirst = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val formatterSecond = DateTimeFormatter.ofPattern("HH:mm:ss")
                    val year : Int = LocalDateTime.now().year
                    val localDateTimeFirst: LocalDate = if (formattedDateTimeFirst.length <= 5) {
                        LocalDate.parse("${year}-$formattedDateTimeFirst", formatterFirst)
                    } else {
                        LocalDate.parse(formattedDateTimeFirst, formatterFirst)
                    }

                    val localDateTimeSecond = LocalTime.parse("$formattedDateTimeSecond:00", formatterSecond)

                    var plString = columns[3]
                    var plStringMas = plString.split(", ")

                    var pl = mutableMapOf<String, String>()
                    for (place in plStringMas) {
                        var temp = place.split(":")
                        pl[temp[0]] = temp[1]
                    }

                    val seans = Seans(columns[0], localDateTimeFirst, localDateTimeSecond, pl)
                    seansList.add(seans)
                }
            }
            File(FILEPATHTICKET).bufferedReader().use { reader ->
                val headers = reader.readLine().split(";")
                reader.forEachLine { line ->
                    val columns = line.split(";")
                    val movie = columns[0]
                    val formattedDate = columns[1]
                    val formattedTime = columns[2]
                    val place = columns[3].toInt()

                    val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")

                    val year : Int = LocalDateTime.now().year
                    val date : LocalDate = if (formattedDate.length <= 5) {
                        LocalDate.parse("${year}-${formattedDate}", formatterDate)
                    } else {
                        LocalDate.parse(formattedDate, formatterDate)
                    }

                    val time = LocalTime.parse("$formattedTime:00", formatterTime)

                    val ticket = Ticket(movie, date, time, place)
                    ticketsList.add(ticket)
                }
            }
            File(FILEPATHMOVIE).bufferedReader().use { reader ->
                val headers = reader.readLine().split(";")
                reader.forEachLine { line ->
                    val columns = line.split(";")
                    var title = columns[0]
                    var director = columns[1]
                    var year = columns[2]

                    var movie = Movie(title, director, year)
                    moviesList.add(movie)
                }
            }
            return DataLists(ticketsList, moviesList, seansList)
        }
        fun readSoldTicketsFromCsv(list: MutableList<Ticket>) {
            File(FILE_PATH_SOLD_TICKETS).bufferedReader().use { reader ->
                val headers = reader.readLine().split(";")
                reader.forEachLine { line ->
                    val columns = line.split(";")
                    val movie = columns[0]
                    val formattedDate = columns[1]
                    val formattedTime = columns[2]
                    val place = columns[3].toInt()

                    val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")

                    val year : Int = LocalDateTime.now().year
                    val date : LocalDate = if (formattedDate.length <= 5) {
                        LocalDate.parse("${year}-${formattedDate}", formatterDate)
                    } else {
                        LocalDate.parse(formattedDate, formatterDate)
                    }

                    val time = LocalTime.parse("$formattedTime:00", formatterTime)

                    val ticket = Ticket(movie, date, time, place)
                    list.add(ticket)
                }
            }
        }
        private fun createAndWriteFile(path: String, file: File){
            var initialData : List<String> = emptyList()
            if (path == "data/tickets.csv" || path == "data/soldTickets.csv") {
                initialData = listOf("Movie;Date;Time;Place")
            }
            if (path == "data/movie.csv") {
                initialData = listOf("Title;Director;Year")
            }
            if (path == "data/seans.csv") {
                initialData = listOf("Movie;Date;Time;Places")
            }
            FileWriter(file, StandardCharsets.UTF_8, true).use { writer ->
                for (line in initialData) {
                    writer.append(line).append('\n')
                }
            }
        }
        private fun createStartData(path : String, file : File) {
            if (path == "data/seans.csv") {
                createSenses(file)
            }
            if (path == "data/movie.csv") {
                createMovies(file)
            }
            if (path == "data/tickets.csv") {
                createTickets(file)
            }

        }
        private fun createTickets(file: File) {

            var initialData : List<String> = emptyList()
            for (seans in seansesDates) {
                for (i in 1..10) {

                    initialData = initialData.plus("${seans};${i}")
                }
            }

            FileWriter(file, StandardCharsets.UTF_8, true).use { writer ->
                for (line in initialData) {
                    writer.append(line).append('\n')
                }
            }

        }
        private fun createMovies(file: File) {
            val directors = listOf("Francis Ford Coppola", "Christopher Nolan", "James Cameron", "Frank Darabont",
                "Quentin Tarantino", "Christopher Nolan", "Robert Zemeckis", "David Fincher", "Lana and Lilly Wachowski",
                "Peter Jackson")
            val years = listOf(1972, 2010, 1997, 1994, 1994, 2008, 1994, 1999, 1999, 2001, 2002, 2003)

            var initialData : List<String> = emptyList()
            for (i in 0..9) {
                initialData = initialData.plus("${movieTitles[i]};${directors[i]};${years[i]}")
            }
            FileWriter(file, StandardCharsets.UTF_8, true).use { writer ->
                for (line in initialData) {
                    writer.append(line).append('\n')
                }
            }

        }
        private fun createSenses(file : File) {
            val map : Map<String, String> = mapOf(
                "1" to "FREE",
                "2" to "FREE",
                "3" to "FREE",
                "4" to "FREE",
                "5" to "FREE",
                "6" to "FREE",
                "7" to "FREE",
                "8" to "FREE",
                "9" to "FREE",
                "10" to "FREE",
            )
            var initialData : List<String> = emptyList()
            for (i in 1..10) {
                val currentDateTime = LocalDateTime.now()
                val futureDateTime = currentDateTime.plusMinutes((i * 3).toLong())

                val dateFormatter = DateTimeFormatter.ofPattern("MM-dd")
                val formattedDate = futureDateTime.format(dateFormatter)

                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                val formattedTime = futureDateTime.format(timeFormatter)

                val movieTitle = movieTitles[i % movieTitles.size]
                seansesDates = seansesDates.plus("$movieTitle;$formattedDate;$formattedTime")
                initialData = initialData.plus("$movieTitle;$formattedDate;$formattedTime;${mapToString(map)}")
            }


            FileWriter(file, StandardCharsets.UTF_8, true).use { writer ->
                for (line in initialData) {
                    writer.append(line).append('\n')
                }
            }
        }
        private fun mapToString(map: Map<String, String>): String {
            return map.entries.joinToString(", ") { "${it.key}:${it.value}" }
        }

        fun saveData() {
            val transfer : TicketsService.Transfer = TicketsService.sendDataToSave()
            val fileTicket = File(FILEPATHTICKET)
            val fileMovie = File(FILEPATHMOVIE);
            val fileSeans = File(FILEPATHSEANS)
            val fileSoldTickets = File(FILE_PATH_SOLD_TICKETS)
            soldTickets = transfer.soldTickets.toMutableList()
            fileTicket.delete()
            fileMovie.delete()
            fileSeans.delete()
            fileSoldTickets.delete()
            var initialData : List<String>
            seansesDates = emptyList()
            if (!fileSeans.exists()) {
                createAndWriteFile(FILEPATHSEANS, fileSeans)
                initialData = emptyList()
                for (i in 1..10) {
                    val dateFormatter = DateTimeFormatter.ofPattern("MM-dd")
                    val formattedDate = transfer.changedSeans[i - 1]._date.format(dateFormatter)

                    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                    val formattedTime = transfer.changedSeans[i - 1]._time.format(timeFormatter)

                    val movieTitle = transfer.changedSeans[i - 1]._movie
                    seansesDates = seansesDates.plus("$movieTitle;$formattedDate;$formattedTime")
                    initialData = initialData.plus("$movieTitle;$formattedDate;$formattedTime;${mapToString(transfer.changedSeans[i - 1]._places)}")
                }

                FileWriter(fileSeans, StandardCharsets.UTF_8, true).use { writer ->
                    for (line in initialData) {
                        writer.append(line).append('\n')
                    }
                }
            }
            if (!fileMovie.exists()) {
                createAndWriteFile(FILEPATHMOVIE, fileMovie)
                initialData = emptyList()
                for (i in 0..9) {
                    initialData = initialData.plus("${transfer.movies[i]._title};${transfer.movies[i]._director};${transfer.movies[i]._year}")
                }
                FileWriter(fileMovie, StandardCharsets.UTF_8, true).use { writer ->
                    for (line in initialData) {
                        writer.append(line).append('\n')
                    }
                }
            }
            if (!fileTicket.exists()) {
                createAndWriteFile(FILEPATHTICKET, fileTicket)
                initialData = emptyList()
                for (ticket in transfer.remainTickets) {

                    initialData = initialData.plus("${ticket._movie};${ticket._date};${ticket._time};${ticket.place}")

                }

                FileWriter(fileTicket, StandardCharsets.UTF_8, true).use { writer ->
                    for (line in initialData) {
                        writer.append(line).append('\n')
                    }
                }

            }
            if (!fileSoldTickets.exists()) {
                createAndWriteFile(FILE_PATH_SOLD_TICKETS, fileSoldTickets)
                initialData = emptyList()
                for (ticket in soldTickets) {
                    initialData = initialData.plus("${ticket._movie};${ticket._date};${ticket._time};${ticket.place}")
                }

                FileWriter(fileSoldTickets, StandardCharsets.UTF_8, true).use { writer ->
                    for (line in initialData) {
                        writer.append(line).append('\n')
                    }
                }
            }

        }

    }



}
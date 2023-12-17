package app

import entity.Movie
import entity.Seans
import entity.Ticket
import functional.Editing
import functional.TicketsService
import util.Util
import java.lang.Exception
import kotlin.system.exitProcess

class StartApp {
    companion object {
        private const val EDIT_PASSWORD = "pleasegive8points"  // Replace with your desired password
        lateinit var data: Util.DataLists
        fun start() {
            Util.checkBeforeStart()
            data = Util.readDataFromCsv()
            way()
        }

        private fun checkPassword(): Boolean {
            print("Введите пароль для сотрудника: ")
            val inputPassword = readLine()
            return inputPassword == EDIT_PASSWORD
        }


        private fun way() {
            val map = mutableMapOf<Int, () -> Unit>()
            map[1] = { TicketsService.buyTicket() }
            map[2] = { TicketsService.returnTicket() }
            map[3] = { TicketsService.showPlaces() }
            map[4] = { Editing.edit(data) }
            map[5] = { Util.saveData() }
            map[6] = { showExitMenu() }
            map[4] = {
                if (checkPassword()) {
                    Editing.edit(data)
                } else {
                    println("Incorrect password.")
                }
            }

            println("Добро пожаловать в Приложение для работников кинотеатра!")
            println("Напишите цифру функции, которую хотите использовать:")
            println("После работы не забудьте сохранить данные при помощи 5 функции, иначе, данные станут начальными")
            while (true) {
                showMenu()
                var choice: Int
                while (true) {
                    try {
                        print("Введите цифру функции: ")
                        choice = readLine()?.toInt() ?: throw NumberFormatException()
                        if (choice in 1..6) {
                            break
                        } else {
                            println("Пожалуйста, введите число от 1 до 6.")
                        }
                    } catch (e: NumberFormatException) {
                        println("Пожалуйста, введите корректное число.")
                    }
                }
                map[choice]?.invoke()
            }
        }

        private fun showMenu() {
            println("1. Покупка билета.")
            println("2. Возврат билета.")
            println("3. Показ мест сеанса.")
            println("4. Редактирование.")
            println("5. Сохранение.")
            println("\n")
            println("6. Выход")
        }

        private fun showExitMenu() {
            var choiceExit: Int
            println("Хотите ли выйти из приложения?")
            println("Если да, приложение завершится и произойдет автоматическое сохраниние")
            println("\n")
            println("1. Да")
            println("2. Нет")
            while (true) {
                try {
                    print("Введите цифру: ")
                    choiceExit = readLine()?.toInt() ?: throw NumberFormatException()
                    if (choiceExit in 1..2) {
                        break
                    } else {
                        println("Пожалуйста, введите число от 1 до 2.")
                    }
                } catch (e: NumberFormatException) {
                    println("Пожалуйста, введите корректное число.")
                }
            }
            if (choiceExit == 1) {
                Util.saveData()
                exitProcess(0)
            }
        }
    }
}
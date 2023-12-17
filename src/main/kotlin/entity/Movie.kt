package entity

class Movie(var _title: String, var _director: String, var _year: String) {
    var title: String
        get() {
            return _title
        }
        set(value) {
            _title = value
        }
    var director: String
        get() {
            return _director
        }
        set(value) {
            _director = value
        }
    var year: String
        get() {
            return _year
        }
        set(value) {
            _year = value
        }

    override fun toString(): String {
        return "Movie(Title=$_title, director=$_director, year=$_year)"
    }


}
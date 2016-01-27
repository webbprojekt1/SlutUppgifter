package ju15.book.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Availability {
private LocalDate date;
private String bookad;

public Availability(String date, String bookad){
	this.date = LocalDate.parse(date,DateTimeFormatter.ofPattern("uuuu-MM-dd"));
	this.bookad = bookad;
}

@Override
public String toString() {
	return "Availability [date=" + date + ", bookad=" + bookad + "]";
}

public LocalDate getDate() {
	return date;
}

public void setDate(LocalDate date) {
	this.date = date;
}

public String getBookad() {
	return bookad;
}

public void setBookad(String bookad) {
	this.bookad = bookad;
}

}

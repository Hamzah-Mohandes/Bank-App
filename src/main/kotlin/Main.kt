data class Konto(val benutzername: String, val passwort: String, var kontostand: Double = 0.0)

fun main() {
    val konten = mutableListOf<Konto>()
    var aktivesKonto: Konto? = null

    while (true) {
        if (aktivesKonto == null) {
            println("Willkommen! Wählen Sie eine Option:")
            println("1. Anmelden")
            println("2. Neues Konto erstellen")
            println("3. Beenden")
            println("4. Hilfe")

            val auswahl = readLine()?.toIntOrNull()

            when (auswahl) {
                1 -> {
                    val benutzername = getInput("Benutzername: ")
                    val passwort = getInput("Passwort: ")
                    val konto = konten.find { it.benutzername == benutzername && it.passwort == passwort }

                    if (konto != null) {
                        aktivesKonto = konto
                        println("Anmeldung erfolgreich! Willkommen, ${aktivesKonto.benutzername}!")
                    } else {
                        println("Falscher Benutzername oder Passwort. Bitte versuchen Sie es erneut.")
                    }
                }
                2 -> {
                    val benutzername = getInput("Neuer Benutzername: ")
                    val passwort = getInput("Neues Passwort: ")
                    val konto = Konto(benutzername, passwort)
                    konten.add(konto)
                    println("Neues Konto erstellt! Sie können sich jetzt anmelden.")
                }
                3 -> {
                    println("Programm wird beendet.")
                    return
                }
                4 -> {
                    zeigeHilfe()
                }
                else -> {
                    println("Ungültige Option. Bitte wählen Sie eine der verfügbaren Optionen.")
                }
            }
        } else {
            println("Willkommen, ${aktivesKonto.benutzername}!")
            println("Wählen Sie eine Option:")
            println("1. Kontostand anzeigen")
            println("2. Geld überweisen")
            println("3. Ausloggen")
            println("4. Hilfe")

            val auswahl = readLine()?.toIntOrNull()

            when (auswahl) {
                1 -> {
                    println("Ihr aktueller Kontostand beträgt: ${aktivesKonto.kontostand} EUR")
                }
                2 -> {
                    val betrag = getInput("Betrag zum Überweisen: ").toDoubleOrNull()
                    if (betrag != null && betrag > 0) {
                        val zielBenutzername = getInput("Ziel-Benutzername: ")
                        val zielkonto = konten.find { it.benutzername == zielBenutzername }
                        if (zielkonto != null) {
                            if (aktivesKonto.kontostand >= betrag) {
                                aktivesKonto.kontostand -= betrag
                                zielkonto.kontostand += betrag
                                println("Überweisung erfolgreich!")
                            } else {
                                println("Nicht genügend Guthaben auf Ihrem Konto.")
                            }
                        } else {
                            println("Ziel-Benutzername nicht gefunden.")
                        }
                    } else {
                        println("Ungültiger Betrag.")
                    }
                }
                3 -> {
                    aktivesKonto = null
                    println("Sie wurden ausgeloggt.")
                }
                4 -> {
                    zeigeHilfe()
                }
                else -> {
                    println("Ungültige Option. Bitte wählen Sie eine der verfügbaren Optionen.")
                }
            }
        }
    }
}

fun getInput(prompt: String): String {
    print(prompt)
    return readLine() ?: ""
}

fun zeigeHilfe() {
    println("Hilfe:")
    println("Für die Anmeldung geben Sie Benutzername und Passwort ein.")
    println("Um ein neues Konto zu erstellen, wählen Sie 'Neues Konto erstellen'.")
    println("Um das Programm zu beenden, wählen Sie 'Beenden'.")
    println("Nach der Anmeldung haben Sie folgende Optionen:")
    println("- Kontostand anzeigen")
    println("- Geld überweisen")
    println("- Ausloggen")
}

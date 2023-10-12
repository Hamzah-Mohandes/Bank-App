data class Konto(val benutzername: String, val passwort: String, var kontostand: Double = 0.0)

fun getInput(prompt: String): String? {
    print(prompt)
    val input = readLine()
    if (input.isNullOrBlank()) {
        println("Bitte geben Sie eine Eingabe ein.")
        return null
    }
    return input
}

fun zeigeHilfe() {
    println("** Hilfe **")
    println("1. Anmelden")
    println("2. Neues Konto erstellen")
    println("3. Beenden")
    println("4. Kontostand anzeigen")
    println("5. Geld überweisen")
    println("6. Geld einzahlen")
    println("7. Ausloggen")
}

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
                    val benutzername = getInput("Benutzername: ") ?: return
                    val passwort = getInput("Passwort: ") ?: return

                    val konto = Konto(benutzername, passwort)
                    konten.add(konto)
                    println("Anmeldung erfolgreich! Willkommen, ${konto.benutzername}!")
                    aktivesKonto = konto
                }

                2 -> {
                    val benutzername = getInput("Neuer Benutzername: ") ?: return
                    val passwort = getInput("Neues Passwort: ") ?: return

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
            println("4. Geld einzahlen")
            println("5. Hilfe")

            val auswahl = readLine()?.toIntOrNull()

            when (auswahl) {
                1 -> {
                    println("Ihr aktueller Kontostand beträgt: ${aktivesKonto.kontostand} EUR")
                }

                2 -> {
                    // Implementierung der Geldüberweisungsoption (wie zuvor)
                }

                3 -> {
                    // Implementierung der Ausloggen-Option (wie zuvor)
                }

                4 -> {
                    val einzuzahlenderBetragString = getInput("Betrag zum Einzahlen: ")
                    if (einzuzahlenderBetragString != null) {
                        try {
                            val einzuzahlenderBetrag = einzuzahlenderBetragString.toDouble()
                            if (einzuzahlenderBetrag > 0) {
                                aktivesKonto.kontostand += einzuzahlenderBetrag
                                println("Einzahlung erfolgreich! Ihr neuer Kontostand beträgt: ${aktivesKonto.kontostand} EUR")
                            } else {
                                println("Ungültiger Betrag für die Einzahlung.")
                            }
                        } catch (e: NumberFormatException) {
                            println("Ungültiger Betrag für die Einzahlung.")
                        }
                    }
                }

                5 -> {
                    zeigeHilfe()
                }
            }
        }
    }
}

// Import des ganzen Swing Moduls
import module java.desktop;

// keine Klasse, vereinfachte main-Methode
void main() {
    IO.println("Hallo, Welt!");
    methode();
}

void methode() {
    final int antwort = JOptionPane.showConfirmDialog(null, "Passt das?", "Frage", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    IO.println( antwort == JOptionPane.YES_OPTION ? "Passt" : "Passt nicht");
}

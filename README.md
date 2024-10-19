# Prayer Times App

Eine JavaFX-Anwendung, die die Gebetszeiten für eine bestimmte Stadt von der **Aladhan Prayer Times API** abruft und anzeigt. Die Anwendung visualisiert die Gebetszeiten in einer benutzerfreundlichen grafischen Oberfläche.

## Funktionen

- **Gebetszeiten abrufen**: Abrufen der Gebetszeiten für Fajr, Sunrise, Dhuhr, Asr, Maghrib, Isha sowie das erste, mittlere und letzte Drittel der Nacht.
- **Automatisches Ausfüllen**: Die Felder für die Gebetszeiten werden automatisch ausgefüllt, wenn die Daten von der API abgerufen werden.
- **Benutzerfreundliche Oberfläche**: Gebetszeiten werden in einer klar strukturierten Oberfläche in einem Raster-Layout angezeigt.

## Technologien

- **JavaFX**: Für die grafische Benutzeroberfläche.
- **org.json**: Zum Parsen der JSON-Daten der API-Antwort.
- **HTTP**: Zum Abrufen der Gebetszeiten von der **Aladhan Prayer Times API**.

## Voraussetzungen

- Java 8 oder höher
- Maven (für das Projekt-Management und Abhängigkeiten)

## Installation

1. Klone dieses Repository:
   ```bash
   git clone https://github.com/besimshala03/prayertimes.git
   ```
2. Wechsle in das Projektverzeichnis
   ```bash
    cd <project-directory>
   ```
3. Starte die Anwendung mit Maven:
  ```bash
   mvn javafx:run
   ```
## Verwendung 
1. Beim Start der Anwendung werden die Gebetszeiten für den aktuellen Monat und die Stadt München automatisch abgerufen und angezeigt.
2. Die Gebetszeiten für Fajr, Sunrise, Dhuhr, Asr, Maghrib, Isha sowie die Zeiträume für das erste, mittlere und letzte Drittel der Nacht werden auf der Benutzeroberfläche angezeigt.

## API 
Die Anwendung verwendet die Aladhan Prayer Times API:

- Endpunkt: http://api.aladhan.com/v1/calendarByCity/{year}/{month}?city=Munich&country=Germany&method=2
- Der Endpunkt gibt die Gebetszeiten für den angegebenen Monat und das Jahr zurück.

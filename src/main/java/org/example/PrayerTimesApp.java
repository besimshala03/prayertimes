package org.example;

    import javafx.application.Application;
import javafx.scene.Scene;
    import javafx.scene.control.Label;
    import javafx.scene.control.TextField;
    import javafx.scene.layout.GridPane;

import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

    import java.time.LocalDate;

public class PrayerTimesApp extends Application {

    private final Label datumLabel = new Label("Datum:");
    private final Label fajrLabel = new Label("Fajr Zeit:");
    private final Label sunriseLabel = new Label("Sonnenaufgang:");
    private final Label dhuhrLabel = new Label("Dhuhr Zeit:");
    private final Label asrLabel = new Label("Asr Zeit:");
    private final Label maghribLabel = new Label("Maghrib Zeit:");
    private final Label ishaLabel = new Label("Isha Zeit:");
    private final Label erstesDrittelLabel = new Label("Erstes Drittel der Nacht:");
    private final Label mitternachtDrittelLabel = new Label("Islamische Mitternacht:");
    private final Label letztesDrittellLabel = new Label("Letztes Drittel der Nacht:");



    private final TextField datumTextfield = new TextField();
  private final TextField fajrTextfield = new TextField();
  private final TextField sunriseTextfield = new TextField();
  private final TextField dhuhrTextfield = new TextField();
  private final TextField asrTextfield = new TextField();
  private final TextField maghribTextfield = new TextField();
  private final TextField ishaTextfield = new TextField();

  private final TextField erstesDrittesTxt = new TextField();

    private final TextField mitterNachtText = new TextField();

    private final TextField letztesDrittesTxt = new TextField();




    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prayer Times App");
        ishaTextfield.setEditable(false);
        fajrTextfield.setEditable(false);
        dhuhrTextfield.setEditable(false);
        asrTextfield.setEditable(false);
        maghribTextfield.setEditable(false);
        sunriseTextfield.setEditable(false);
        datumTextfield.setEditable(false);
        erstesDrittesTxt.setEditable(false);
        mitterNachtText.setEditable(false);
        letztesDrittesTxt.setEditable(false);
        // Gebetszeiten beim Start abrufen
        fetchPrayerTimes();

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Labels hinzufügen
        gridPane.add(datumLabel, 0, 0);
        gridPane.add(fajrLabel, 0, 1);
        gridPane.add(sunriseLabel, 0, 2);
        gridPane.add(dhuhrLabel, 0, 3);
        gridPane.add(asrLabel, 0, 4);
        gridPane.add(maghribLabel, 0, 5);
        gridPane.add(ishaLabel, 0, 6);
        gridPane.add(erstesDrittelLabel,0,7);
        gridPane.add(mitternachtDrittelLabel,0,8);
        gridPane.add(letztesDrittellLabel,0,9);




        // TextFields hinzufügen
        gridPane.add(datumTextfield, 1, 0);
        gridPane.add(fajrTextfield, 1, 1);
        gridPane.add(sunriseTextfield, 1, 2);
        gridPane.add(dhuhrTextfield, 1, 3);
        gridPane.add(asrTextfield, 1, 4);
        gridPane.add(maghribTextfield, 1, 5);
        gridPane.add(ishaTextfield, 1, 6);
        gridPane.add(erstesDrittesTxt, 1, 7);
        gridPane.add(mitterNachtText, 1, 8);
        gridPane.add(letztesDrittesTxt, 1, 9);




        gridPane.getStyleClass().add("grid-pane");

        Scene scene = new Scene(gridPane, 475, 500);
        // Lade die CSS-Datei
        scene.getStylesheets().add("file:src/styles.css");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    static void startApp() {
            launch();
        }

    private void fetchPrayerTimes() {
        try {
            // Aktuelles Datum
            LocalDate currentDate = LocalDate.now();
            int year = currentDate.getYear();
            int month = currentDate.getMonthValue();

            // Beispiel-URL für den Endpunkt "Prayer Times Calendar"
            String endpointUrl = "http://api.aladhan.com/v1/calendarByCity/" + year + "/" + month +
                    "?city=Munich&country=Germany&method=2";

            // HTTP-Anfrage erstellen
            URL url = new URL(endpointUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Antwort lesen
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line).append(System.lineSeparator());
                }

                reader.close();

                // Hier kannst du die Antwort verarbeiten (z.B., JSON-Parsing)
                String jsonResponse = response.toString();
                displayPrayerTimes(jsonResponse);
            } else {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


// ...a

    private void displayPrayerTimes(String jsonResponse) {
        try {
            JSONObject responseObject = new JSONObject(jsonResponse);

            if (responseObject.has("data") && responseObject.get("data") instanceof JSONArray) {
                JSONArray dataArray = responseObject.getJSONArray("data");

                // Gebetszeiten für den heutigen Tag
                JSONObject todayObject = dataArray.getJSONObject(LocalDate.now().getDayOfMonth() - 1);
                JSONObject dateObject = todayObject.getJSONObject("date");

                String gregorianDate = extractGregorianDate(dateObject);
                JSONObject timingsObject = todayObject.getJSONObject("timings");

                // Gebetszeiten anzeigen


                datumTextfield.setText(gregorianDate);
                fajrTextfield.setText(timingsObject.getString("Fajr"));
                sunriseTextfield.setText(timingsObject.getString("Sunrise"));
                dhuhrTextfield.setText(timingsObject.getString("Dhuhr"));
                asrTextfield.setText(timingsObject.getString("Asr"));
                maghribTextfield.setText(timingsObject.getString("Maghrib"));
                ishaTextfield.setText(timingsObject.getString("Isha"));
                ishaTextfield.setText(timingsObject.getString("Isha"));
                erstesDrittesTxt.setText(timingsObject.getString("Firstthird"));
                mitterNachtText.setText(timingsObject.getString("Midnight"));
                letztesDrittesTxt.setText(timingsObject.getString("Lastthird"));


            } else {
        System.out.println();
      }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String extractGregorianDate(JSONObject dateObject) throws JSONException {
            JSONObject gregorianObject = dateObject.getJSONObject("gregorian");
            return gregorianObject.getString("date");
        }
    }



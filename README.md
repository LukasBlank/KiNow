# kiNOW
Unser Kino-Reservierungstool
<br>
Take a look: https://developer.android.com/training/basics/firstapp <br><br>
# Besprechung 12.09.2018

## Vorgehen

- Aufgabenteilung in Frontend, Backend, Datenbank und Dokumentation
  - Ansprechpartner: Datenbanken - Arthur, Backend - Nils, Tobias, Lukas, Frontend - Dennis, Julie
- Terminplan unter 'Projekt'
- Einzelne Aufgaben werden als Issues bearbeitet - Label ob es sich um Frontend, Backend oder Datenbanken handelt
- Pushen nur mit gruppeninterner Absprache
- Versionierung durch Branches
- Code und Dokumentation im Git
- Code wird dokumentiert, Nutzen des Codeabschnittes

## Technologien

- Datenbank: Firebase Firestore
- Front-/ Backend: Android Studio
- Dokumentation: JSP Wiki / Git

## Erste Version bis 23.09

- Anmeldebildschirm, Login oder Gastzugriff
- Main-Activity
- Filmdetailansicht
- Datenbank mit mindestens Tabellen für Nutzer und Filmen
- Nutzer: Admin, Gruppenmitglieder ( Email oder Benutzername , Passwort: Nachname des Gruppenmitglieds)
- Filtern

## Meilensteine: 
- Datenbank - 17.09.2019
- Frontend - 19.09.2019
- Backend - 20.09.2019


# Erste Schritte stand 23.09.2019
## Frontend 
  - Ladebildschirm Vorhanden 
  - NavigationsBar ermöglicht das Wechseln zwischen verschiedenen Ansichten 
    - Suchen, Allgemeine Filmansicht, Ticketansicht und einer Profilansicht

## Backend 
  - Fachkonzeptschicht erstellt, Backend über Spring-App
  - Gehostet wird über eigenen Server, Kommunikation über HttpsRequests
  - Liefert immer Json-Dateien
    
## Database
   - Die Datenbank ist erstellt und konfiguriert

## To Do's
  - Suchen 
    - Suchfunktion im SuchScreen einfügen 
  - Filmansicht:
    - Darstellung aller Filme einführen mit Daten aus der Datenbank
    - Sortierfunktion für die Filme -> Genre, FSK, etc
    - Filterfunktion für die Filme -> Vorstellungsdatum, Uhrzeit, Genre, etc.
    - Detailansicht zu jedem Film erstellen mit Ticketbestellung
  - Ticketansicht:
    - Gekaufte Tickets sollen in einer Liste angezeigt werden
  - Profil
    - Name, Adresse usw verändern bzw. Anmeldung
    

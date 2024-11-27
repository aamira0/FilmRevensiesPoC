# FilmRecensies - Proof of Concept (PoC)

## Overzicht
Deze Proof of Concept (PoC) demonstreert een Machine Learning-toepassing voor het classificeren van filmrecensies op basis van hun sentiment (bijvoorbeeld positief of negatief). Het project gebruikt de Weka-bibliotheek in een programmeeromgeving (Java) om classificatie te implementeren en te evalueren.

---

## Functionaliteiten
- Classificatie van filmrecensies via twee modellen:
    - **NaiveBayes**: Simpel, snel, en accuraat voor tekstuele data.
    - **RandomForest**: Een krachtiger model voor complexe datasets.
- Vergelijking van modelprestaties met metriek zoals nauwkeurigheid, Kappa-statistiek en fouten.

---

## Resultaten
Hieronder staan de prestaties van beide modellen op de dataset:

| **Metriek**               | **NaiveBayes**     | **RandomForest**    |
|---------------------------|--------------------|---------------------|
| Correct geclassificeerde voorbeelden | 92.73%            | 78.18%             |
| Kappa-statistiek          | 0.8533            | 0.5417             |
| Gemiddelde absolute fout  | 0.0684            | 0.2676             |
| Root Mean Squared Error   | 0.2532            | 0.3525             |

### Conclusie
- **NaiveBayes presteert beter** in termen van nauwkeurigheid en fouten, en wordt daarom aanbevolen voor dit project.
- **RandomForest** biedt ruimte voor verbetering, bijvoorbeeld door hyperparameter tuning en datasetvergroting.

---

## Installatie & Vereisten

### Vereisten
- **IDE**: IntelliJ IDEA.
- **Weka-bibliotheek (JAR-bestand)**, toegevoegen aan het project. Dit download je via: [Weka Download](https://sourceforge.net/projects/weka/files/) of heb je als zip-bestand ontvangen en zet je in een library map zoals `lib/`.

### Installatiestappen en hoe het werkt
1. Download of gebruik de Weka JAR die is meegegeven en voeg deze toe aan je Java-project in een library map zoals `lib/`.
2. Ga naar project structure en voeg de Weka JAR toe en voeg de weka.jar toe in je libraries die je in een map hebt gezet.
3. Nu alles geïnstalleerd is kan je naar StartApplication gaan en de applicatie starten.
4. Je krijgt in de console de keuze tussen NaiveBayes en RandomForest. Kies een model om te beginnen.
5. Je kan nu film recensies in de console invoeren en de classificatie resultaten bekijken (positief of negatief).
6. Je geeft daarbij feedback of het model de recensie goed of fout heeft geclassificeerd. Is het fout dan kan je aangeven of het een positieve of negatieve recensie is.
7. Het model wordt opnieuw getraind met de feedback. Dit kan bij sommige recensies sneller zijn dan bij andere. Lange recensies werken op de huidige dataset beter en sneller.
8. Je kan na 10 recensies van model wisselen. Dit wordt ook gevraagd in de console.
9. Als je wilt stoppen met de applicatie kan je dit aangeven in de console met 'stop'. De sessie wordt dan afgesloten en alles dat het model geleerd heeft wordt niet opgeslagen.
10. De applicatie stopt. Als je de applicatie opnieuw start, wordt er een nieuwe sessie gestart.

### Training van Modellen
- De modellen zijn getraind op basis van de filmrecensies in de dataset (in code geïmplementeerd van StartApplication).
- Tijdens het gebruik van de applicatie worden de modellen getraind op basis van de feedback van de gebruiker. Dit werkt binnen een sessie.

### Evaluatie
- Resultaten van de recensies worden getoond in de console en kunnen gecorrigeerd worden door de gebruiker in de console van StartApplication. Dezelfde recensie kan weer ingevoerd worden om te zien of het model de recensie nu goed classificeert. Dit werkt alleen sneller bij lange recensies.
- De prestaties van beide modellen worden vergeleken op basis van nauwkeurigheid etc. in de console van CheckModels.

---

## Toekomstige Verbeteringen
1. **Hyperparameter Tuning**: Optimaliseer de RandomForest-parameters om de prestaties te verbeteren.
2. **Meer Data**: Verhoog de omvang van de trainingsdataset om de nauwkeurigheid van beide modellen te verbeteren.
3. **Alternatieve Modellen**: Experimenteer met andere classifiers zoals SVM of Logistic Regression.
4. **Data opslaan**: Sla de feedback van de gebruiker op en gebruik deze om de modellen te verbeteren.

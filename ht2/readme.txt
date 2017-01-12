Eduquest är en applikation för att göra det flexiblare att studera för personer i rörelse
genom att förse möjligheten att göra quizfrågor inför specifika prov på mobiltelefonen.

Eduquest består av två delar: en klient i form av en Android-applikation, och en server i form
av en webbserver. Via webbservern kan lärare skapa nya kategorier av frågor för kurser, och sedan populera
dessa med par av frågor och svar. Tanken är att Eduquest även ska kunna förse potentiella med en färdig
hemsida med ett interaktivt gränssnitt för att sköta detta, men det har inte implementerats som en del av kursen.
Istället finns en primitiv server tillgänglig för testning.

När användaren startar applikationen för första gången kommer de att bli tillfrågade att ange
en adress till den tilltänkta servern de ska använda.
Denna adress kommer sedan att sparas på telefonen så att användaren ej behöver upprepa detta varje gång,
och en möjlighet att ändra denna i efterhand finns. Användaren kommer sedan att presenteras med en meny av möjliga
kurser/kategorier, där antingen en eller flera kategorier kan väljas. När kategorier är valda
kommer en fråga att presenteras. Här kan användaren fundera över sitt svar, och sedan trycka
på en knapp som visar det givna svaret som läraren valt. Därefter fortsätter det till nästa fråga.


För att testa applikationen krävs att en serverbackend är igång. I projektet finns simpelt
exempel för testsyften. Servern baserad på node.js. För att starta servern krävs först
att node.js är installerat. Starta sedan en terminal som navigerar till serverns katalog
och kör kommandonda "npm install" för att ladda ner alla dependencies, följt av
"npm start" för att starta servern. Servern använder sig av port 3000. För att ansluta
till denna via en emulator anges adressen http://10.0.2.2:3000.


Länk till screencast på youtube.

Användningsmanual
https://www.youtube.com/watch?v=y2S7dEvfVT4

Teknisk genomgång
https://www.youtube.com/watch?v=Jq2q02FtKqQ

Instruktioner till källfilerna (nödvändiga bibliotek), etc.

Eduquest består av tre primära aktiviteter: MainActivity.java, CategorySelection.java samt QuizActivity.java.

MainActivity.java är där applikationen börjar, och dess ansvar är att tillåta användaren att ange
adressen till en server. Felhantering i form av tomt fält, ogiltig URL eller adress till en
ogiltig server sker här. Om en godkänd adress anges sparas denna. Därefter slussas
användaren vidare till CategorySelectionActivity. Om en adress finns sparad sedan tidigare sker
detta omedelbart.

CategorySelection.java ansvarar för att hämta de tillgängliga kategorierna från servern,
samt att låta användaren få välja vilka kategorier som ska användas. Felhantering i form av
att försöka gå vidare utan att välja någon kategori, eller om kategorin inte skulle innehålla
några frågor sker här. Om allt är okej, slussas användaren vidare till QuizActivity.

QuizActivity.java ansvarar för att slumpa fram frågor från den tillgängliga poolen, samt
att visa frågorna och dess svar.

Utöver dessa finns fem stycken olika dialogfragment: InvalidUpstreamDialog.java, InvalidUrlDialog.java,
NoCategorySelectedDialog.java, NoInternetConnectionDialog.java samt NoQuestionsDialog.java.
Samtliga fragment används för att notifiera användaren om fel som kan uppstå under appens körning.

Både MainActivity and CategorySelectionActivity använder sig av Google's externa
bibliotek Volley för att hantera alla HTTP-requests, samt Firebase för att hantera
loggning av användarnas aktivitet som utgör en grund för att kunna analysera
användarnas utnyttjande av applikationen.

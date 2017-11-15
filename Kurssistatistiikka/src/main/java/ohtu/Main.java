package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String url2 = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String url3 = "https://studies.cs.helsinki.fi/ohtustats/stats";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();
        String statsResponse = Request.Get(url3).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println("1: " + bodyText);
        System.out.println("2: " + bodyText2);
        System.out.println("3: " + statsResponse);
        System.out.println();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Kurssi kurssi = mapper.fromJson(bodyText2, Kurssi.class);
        
        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();
        
        int palautukset = 0;
        int maara = 0;
        for (int i = 1; i <= parsittuData.size(); i++) {
            String sana = String.valueOf(i);
            palautukset = palautukset + parsittuData.getAsJsonObject(String.valueOf(i)).get("students").getAsInt();
            maara = maara + parsittuData.getAsJsonObject(String.valueOf(i)).get("exercise_total").getAsInt();
        }
        
        
        
        System.out.println("Kurssi: " + kurssi.getKurssi() + ", " + kurssi.getTerm());
        System.out.println();
        System.out.println("opiskelijanumero: " + studentNr);
        System.out.println();
        int tehtävätYhteensä = 0;
        int tunnitYhteensä = 0;
        for (Submission submission : subs) {
            System.out.println("viikko "+ submission.getWeek() + ": tehtyjä tehtäviä yhteensä: " + submission.getExercises().length + " (maksimi " + kurssi.harjoitustenMaara(submission.getWeek() - 1) + "), aikaa kului " + submission.getHours() + " tuntia, tehdyt tehtävät: " + submission.tehtävät());
            tehtävätYhteensä = tehtävätYhteensä + submission.getExercises().length;
            tunnitYhteensä = tunnitYhteensä + submission.getHours();
        }
        System.out.println();
        System.out.println("yhteensä: " + tehtävätYhteensä + " tehtävää " + tunnitYhteensä + " tuntia");
        System.out.println();
        System.out.println("kurssilla yhteensä " + palautukset + " palautusta, palautettuja tehtäviä " + maara + " kpl");
    }
}

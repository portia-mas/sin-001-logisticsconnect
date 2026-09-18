package co.wethinkcode.logisticsconnect;

import io.javalin.Javalin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IngestionServiceApp {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7050);

        app.get("/health", ctx -> ctx.result("OK"));

        // TODO: read and clean src/main/resources/hubs-global.csv (hubs, sorting centers, regional districts data —
        // trim whitespace, fix casing, normalize dates/booleans) and expose the
        // cleaned records here for the other services to consume.

        InputStream in = IngestionServiceApp.class.getClassLoader().getResourceAsStream("hubs-global.csv");
        System.out.println(in);

        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String header = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
//
                String[] fields = line.split(",");

                for(int i = 0; i < fields.length; i++) {
                    System.out.println("[" + i + "] raw=[" + fields[i] + "] trimmed=[" + fields[i].trim().replaceAll("\\s+", " ") + "]");                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package dev.thorvi;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) throws IOException, InterruptedException {
    //    fazer conexão e buscar dados dos TOP 250 filmes
    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    URI address = URI.create(url);
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(address).GET().build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String body = response.body();
    System.out.println(body);

    //    extrair os dados de interesse: título; poster; rating;
    JsonParser parser = new JsonParser();
    List<Map<String, String>> moviesList = parser.parse(body);
    System.out.println(moviesList.size());
    System.out.println(moviesList.get(0));

    //    manipular e exibir os dados
    for (Map<String, String> movie : moviesList) {
      System.out.println(movie.get("title"));
      System.out.println(movie.get("image"));
      System.out.println(movie.get("imDbRating"));
      System.out.println("");
    }
  }
}
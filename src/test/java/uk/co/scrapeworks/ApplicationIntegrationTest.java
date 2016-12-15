package uk.co.scrapeworks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationIntegrationTest {

    /**
     * To ensure that this test isnt brittle i have only checked that
     * json is returned.  More can be done to at least check if the correct
     * fields are returned in json (not their values) but this can be built
     * upon later
     */
    @Test
    public void shouldReturnValidJson() throws InterruptedException {
        Gson gson = new GsonBuilder().create();
        Application application = new Application();

        String result = application.run();

        assertThat(gson.toJson(result)).isNotNull();
    }

}

package com.ingconti;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void godTest()
    {
        God god = new God( "Neptune", "God of Sea", God.PowerType.CAN_RAISE_WAVES);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(god);
        assertTrue( result.contains(  "\"powerType\": \"CAN_RAISE_WAVES\"") );

        String jsonStr = god.JSONString();
        assertTrue( result.contains(  "\"powerType\": \"CAN_RAISE_WAVES\"") );

    }


}

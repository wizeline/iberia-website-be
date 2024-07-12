package com.wizeline.demoiberia.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wizeline.demoiberia.exception.ImageGenerationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FreePikService {

    public String getImageUrl(String prompt, HttpServletRequest httpServletRequest) {
        RestClient customClient = RestClient.builder()
                .defaultHeader("x-freepik-api-key", "FPSXb5983a148f6c4c67b8aa048d53b5f97e")
                .build();

        String  result= customClient.post().uri("https://api.freepik.com/v1/ai/text-to-image")
                .body("{\"prompt\":\""+prompt+"\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve().body(String.class);

        JsonObject jsonObject = JsonParser.parseString(result)
                .getAsJsonObject();

        String base64String = jsonObject.getAsJsonArray("data").get(0).getAsJsonObject().get("base64").getAsString();
        byte base64Byte[] = Base64Coder.decode(base64String);
        String fileName="freepik"+System.nanoTime()+".jpg";
        FileOutputStream fos = null;
        String pathOutputFile= File.separator+"tmp"+File.separator+fileName;
        try {
            fos = new FileOutputStream(pathOutputFile);
            fos.write(base64Byte);
            
        } catch (Exception e) {
            throw new ImageGenerationException("Error generating images",e);
        }
        finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println("Unable to close "+pathOutputFile);
                }
            }
        }
        
        return httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+"/resources/freepik/"+fileName;
    }


}

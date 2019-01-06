package com.example.lpiem.coderproprementprojet.dataSource;

import android.content.Context;
import android.os.AsyncTask;

import com.example.lpiem.coderproprementprojet.models.Comic;
import com.example.lpiem.coderproprementprojet.models.Creator;
import com.example.lpiem.coderproprementprojet.models.MyTaskParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class ParsingFileTask extends AsyncTask<MyTaskParams, Integer, ArrayList> {

    @Override
    protected ArrayList doInBackground(MyTaskParams... params) {
        String filePath = params[0].getFilePath();
        Context context = params[0].getContext();
        String jsonFile = null;
        ArrayList<Comic> comics = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonFile = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonFile);
            int code = jsonObject.getInt("code");

            JSONArray jsonArray = jsonObject.getJSONArray("results");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonComic = jsonArray.getJSONObject(i);
                JSONArray jsonComicCreators = jsonComic.getJSONArray("creators");

                ArrayList creators = new ArrayList<Creator>();
                JSONObject creator = null;
                for (int j = 0; j < jsonComicCreators.length(); j++) {

                    creator = jsonComicCreators.getJSONObject(j);
                    creators.add(new Creator(creator.getString("name"),
                            creator.getString("role")));
                }

                comics.add(new Comic(jsonComic.getInt("id"), jsonComic.getString("title"),
                        jsonComic.getInt("issueNumber"), jsonComic.getString("description"),
                        jsonComic.getString("diamondCode"), jsonComic.getString("date"),
                        jsonComic.getDouble("price"), jsonComic.getInt("pageCount"),
                        jsonComic.getString("image"),
                        creators));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comics;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList comics) {
        super.onPostExecute(comics);
    }

}

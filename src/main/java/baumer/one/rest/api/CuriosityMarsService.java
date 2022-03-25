package baumer.one.rest.api;

import baumer.one.rest.api.config.LocalDateSerializer;
import baumer.one.rest.api.exceptions.CuriosityMarsNotFoundException;
import baumer.one.rest.api.model.CuriosityMars;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.Resources;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CuriosityMarsService {

    @Autowired
    private CuriosityMarsRepository curiosityMarsRepository;

    @PostConstruct
    public void init() throws IOException {
        loadData();
    }

    public CuriosityMars getCuriosityMarsIdDetail(Integer curiosityMarsId) {
        return curiosityMarsRepository.findById(curiosityMarsId).orElseThrow(CuriosityMarsNotFoundException::new);
    }

    public List<CuriosityMars> listAll() {
        List<CuriosityMars> list = new ArrayList<CuriosityMars>();
        curiosityMarsRepository.findAll().iterator().forEachRemaining(list::add);;
        return list;
    }
    /**
     * Load mock data to H2 database
     */
    private void loadData() throws IOException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        InputStream inputStream = Resources.getResource("data.json").openStream();

        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<CuriosityMars>>() {
        }.getType();

        curiosityMarsRepository.saveAll(gson.fromJson(json, listType));
    }
}
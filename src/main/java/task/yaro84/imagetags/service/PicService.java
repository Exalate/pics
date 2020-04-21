package task.yaro84.imagetags.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PicService {

    public String addPic(byte[] img) {
        VisualInfo visInf = new VisualInfo();
        List<String> tagList = new ArrayList<>();
        try {
            tagList = visInf.getImageTags(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(tagList);
        return tagList.toString();
    }
}

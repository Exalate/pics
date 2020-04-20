package task.yaro84.imagetags.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.yaro84.imagetags.service.PicService;

@RestController
@RequestMapping("/pictags")
public class MainController {

    private PicService picService;

    @Autowired
    public MainController(PicService picService) {
        this.picService = picService;
    }

    @PostMapping("/start")
    @ResponseBody
    public String addPic(@RequestBody byte[] img) {
        return picService.addPic(img);
    }
}

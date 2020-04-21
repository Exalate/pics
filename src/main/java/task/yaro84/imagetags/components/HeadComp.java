package task.yaro84.imagetags.components;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import task.yaro84.imagetags.domain.ImageEnt;
import task.yaro84.imagetags.repo.ImageRepo;
import task.yaro84.imagetags.rest.MainController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@SpringComponent
@UIScope
public class HeadComp extends VerticalLayout implements KeyNotifier {
    private final ImageRepo imageRepo;
    private ImageEnt image;
    byte [] img;
    String tags;
    private TextField imageUrl = new TextField("", "Image URL");
    private final Button button = new Button("Add");
    private HorizontalLayout toolbar = new HorizontalLayout(button);
    private Binder<ImageEnt> binder = new Binder<>(ImageEnt.class);
    private MainController mainController;

    @Autowired
    public HeadComp(ImageRepo imageRepo, MainController mainController) {
        this.imageRepo = imageRepo;
        this.mainController = mainController;
        imageUrl.setLabel("Image URL:");
        imageUrl.setWidth("100%");
        toolbar.setWidth("100%");
        add(imageUrl, toolbar);
        binder.bindInstanceFields(this);
        button.addClickListener(i -> runJob());
    }

    private void runJob() {
        img = getImageIntoByte(imageUrl.getValue());
        tags = mainController.addPic(img);
        image = new ImageEnt(imageUrl.getValue(), tags, img);
        imageRepo.save(image);
    }

    private byte[] getImageIntoByte(String curURL) {
        try {
            final URLConnection hpCon = new URL(curURL).openConnection();
            final int len = hpCon.getContentLength();
            if (len > 0) {
                final byte[] imageData = new byte[len];
                final InputStream inputStream = hpCon.getInputStream();
                try {
                    int readed = 0;
                    do {
                        readed += inputStream.read(imageData, readed, len - readed);
                    } while (readed < len);
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return imageData;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new byte[1];
    }
}

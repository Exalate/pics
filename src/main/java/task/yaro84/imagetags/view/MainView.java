package task.yaro84.imagetags.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import task.yaro84.imagetags.components.HeadComp;
import task.yaro84.imagetags.domain.ImageEnt;
import task.yaro84.imagetags.repo.ImageRepo;

import java.io.ByteArrayInputStream;

@Route
public class MainView extends VerticalLayout {
    private final HeadComp headComp;
    private final ImageRepo imageRepo;
    private final Grid<ImageEnt> grid = new Grid<>(ImageEnt.class);
    private Binder<ImageEnt> binder = new Binder<>(ImageEnt.class);

    @Autowired
    public MainView(ImageRepo imageRepo, HeadComp headComp) {
        this.imageRepo = imageRepo;
        this.headComp = headComp;
        add(this.headComp, grid);
        setupGridDefault();
        super.setHeight("100%");
    }
    private void setupGridDefault() {
        grid.setItems(this.imageRepo.findAll());
        grid.getColumnByKey("id")
                .setWidth("0.5px")
                .setResizable(true);
        grid.addComponentColumn(i -> getImageForGrid(i.getImg()));
        grid.addColumn(new NativeButtonRenderer<>("delete", clickItem -> delete(clickItem)))
                .setWidth("1px")
                .onEnabledStateChanged(true);
        grid.getColumnByKey("img").setVisible(false);
    }
    private void delete(ImageEnt i){
        imageRepo.delete(i);
        grid.setItems(this.imageRepo.findAll());
    }
    private Image getImageForGrid(byte[] b){
        StreamResource resource = new StreamResource("dummyImageName.jpg", () -> new ByteArrayInputStream(b));
        Image image = new Image(resource, "dummy image");
        image.setWidth("150px");
        return image;
    }
}

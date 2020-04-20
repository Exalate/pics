package task.yaro84.imagetags.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ImageEnt {
    @Id
    @GeneratedValue
    private Long id;
    private String imageUrl;
    private String tags;
    private byte[] img;

    public ImageEnt(){
    }

    public ImageEnt(String imageUrl, String tags, byte[] img)  {
        this.imageUrl = imageUrl;
        this.tags = tags;
        this.img = img;
    }

}

package petrenko;

import java.io.Serializable;
//сериализация класса со ссылкой
/**
 * Created by grpetr189853 on 01.02.2017.
 */
public class House  implements Serializable {
    private int count=2;
    private Color color = new Color();
    @Override
    public String toString() {
        return "House{" + "count=" + count + ", color=" + color + '}';
    }
}
class Color implements Serializable{
    private int count=145;
    private String name="Write";
    @Override
    public String toString() {
        return "Color{" +
                "count=" + count +
                ", name='" + name + '\'' +
                '}';
    }
}

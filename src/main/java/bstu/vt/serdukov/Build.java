package bstu.vt.serdukov;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Build {
    String type;
    String builddate;
    String material;
    float square;
}

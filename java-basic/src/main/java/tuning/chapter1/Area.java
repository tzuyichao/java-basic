package tuning.chapter1;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Area {
    private Integer provinceId;
    private Integer cityId;

    public Area(Integer provinceId, Integer cityId) {
        this.provinceId = provinceId;
        this.cityId = cityId;
    }

    public CityKey buildKey() {
        return new CityKey(provinceId, cityId);
    }
}

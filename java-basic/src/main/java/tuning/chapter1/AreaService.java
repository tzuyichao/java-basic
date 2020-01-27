package tuning.chapter1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaService {
    public Map buildArea(List<Area> areas) {
        if(areas.isEmpty()) {
            return new HashMap();
        }
        Map<String, Area> map = new HashMap<>();
        for(Area area: areas) {
            String key = area.getProvinceId() + "#" + area.getCityId();
            map.put(key, area);
        }
        return map;
    }
}

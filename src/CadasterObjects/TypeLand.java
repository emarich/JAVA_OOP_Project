package CadasterObjects;

import Owners.City;
import Owners.Owner;

public enum TypeLand {
    PUBLIC, PRIVATE;

    public static TypeLand getTypeLand (Owner owner) {
        return PRIVATE;
    }

    public static TypeLand getTypeLand (City city) {
        return PUBLIC;
    }
}

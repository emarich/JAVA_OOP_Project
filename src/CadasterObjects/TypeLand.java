package CadasterObjects;

import Owners.City;
import Owners.Owner;

public enum TypeLand {
    PUBLIC, PRIVATE;

    public TypeLand getTypeLand (Owner owner) {
        return PRIVATE;
    }

    public TypeLand getTypeLand (City city) {
        return PUBLIC;
    }
}

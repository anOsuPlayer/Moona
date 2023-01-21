#include "../moona.h"

namespace moona {

    static long id = 0;

    long Moona::generateId() {
        return ++id;
    }
}
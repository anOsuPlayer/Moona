#include "../moona.h"

namespace {
    static long id = 0;
}

namespace moona {

    long Moona::generateId() {
        return ++id;
    }
}
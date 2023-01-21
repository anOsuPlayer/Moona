#include "../moona.h"

using std::ostream;

namespace moona {

    Object::Object() {
        this->id = Moona::generateId();
    }

    Object::~Object() {
    }

    Object* Object::clone() const {
        return new Object();
    }
}
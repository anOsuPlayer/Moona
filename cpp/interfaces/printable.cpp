#include "printable.hpp"

namespace moona {

    std::ostream& operator << (std::ostream& os, const Printable& obj) {
        os << obj.toString();
        return os;
    }
}
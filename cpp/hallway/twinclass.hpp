#include "javanotation.hpp"
#include "javaclass.hpp"
#include "../exceptions/illegalexception.hpp"

namespace moona {

    class TwinClass {
        private:
            JavaClass clazz;

        public:
            TwinClass() = default;
            TwinClass(const JavaClass& clazz);
            TwinClass(const TwinClass& clazz);
    };
}
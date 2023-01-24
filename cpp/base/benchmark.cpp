#include "../moona.hpp"

namespace moona {

    void Benchmark::printAssertion(bool condition) {
        std::cout << std::boolalpha << condition << std::noboolalpha;
    }
}
#include "../moona.hpp"

namespace moona {

    void Benchmark::displayAssertion(bool condition) {
        std::cout << std::boolalpha << condition << std::noboolalpha;
    }
}
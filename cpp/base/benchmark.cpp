#ifndef MOONA
    #include <iostream>
    #include "benchmark.hpp"
#endif

namespace moona {

    void Benchmark::printAssertion(bool condition) {
        std::cout << std::boolalpha << condition << std::noboolalpha;
    }
}
#include <iostream>
#include "benchmark.hpp"

namespace moona {

    Benchmark::Benchmark() {
    }

    Benchmark::~Benchmark() {
    }

    void Benchmark::printAssertion(bool value) {
        std::cout << std::boolalpha << value << std::noboolalpha;
    }
}
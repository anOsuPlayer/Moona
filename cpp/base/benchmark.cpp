#include "benchmark.hpp"

namespace moona {

    Benchmark::Benchmark() {
    }

    Benchmark::~Benchmark() {
    }

    void Benchmark::printAssertion(const bool& value) {
        std::cout << std::boolalpha << value << std::noboolalpha;
    }
}
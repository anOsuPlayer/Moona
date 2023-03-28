#include "benchmark.hpp"

namespace moona {

    void Benchmark::printAssertion(const bool& value) noexcept {
        std::cout << std::boolalpha << value << std::noboolalpha;
    }
}
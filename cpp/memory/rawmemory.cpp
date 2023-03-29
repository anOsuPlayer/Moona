#include "rawmemory.hpp"

namespace moona {

    ChainedPointer::ChainedPointer(void* value, ChainedPointer* next) {
        this->value = value;
        this->next = next;
    }

    ChainedPointer::ChainedPointer(ChainedPointer* next) : ChainedPointer(nullptr, next) {
    }

    ChainedPointer::ChainedPointer() : ChainedPointer(nullptr, nullptr) {
    }

    ChainedPointer::~ChainedPointer() {
        if (next != nullptr) {
            delete this->next;
        }
        std::cout << "deleted\n";
    }

    RawMemory::~RawMemory() {
        if (this->begin != nullptr) {
            delete this->begin;
        }
    }

    int RawMemory::size() const noexcept {
        return this->elements;
    }

    const unsigned short int RawMemory::thisSize() const noexcept {
        short totalSize = sizeof(*this); const ChainedPointer* ptr = this->begin->next;
        for (int i = 0; i < this->size(); i++) {
            totalSize += sizeof(*ptr);
            ptr = ptr->next;
        }
        return totalSize;
    }
}
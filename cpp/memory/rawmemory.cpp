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
        std::cout << "deleted\n";
    }

    void ChainedPointer::setNext(ChainedPointer* next) const {
        this->next = next;
    }

    const ChainedPointer* ChainedPointer::getNext() const {
        return this->next;
    }

    RawMemory::RawMemory(ChainedPointer* begin, ChainedPointer* end) {
        this->begin = begin; this->end = end;
    }

    RawMemory::RawMemory() {
        this->begin = new ChainedPointer();
        this->end = new ChainedPointer();

        this->begin->setNext(end);
        this->end->setNext(nullptr);
    }

    RawMemory::~RawMemory() {
        ChainedPointer* first = this->begin; ChainedPointer* later = this->begin->next;
        for (int i = 0; i <= this->elements; i++) {
            delete first;
            first = later;
            later = later->next;
        }
        delete this->end;
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
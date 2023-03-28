#include "rawmemory.hpp"

namespace moona {

    ChainedPointer::ChainedPointer(void* value, ChainedPointer* next, ChainedPointer* prev) {
        this->value = value;
        this->next = next;
        this->prev = prev;
    }

    ChainedPointer::ChainedPointer(ChainedPointer* next, ChainedPointer* prev) : ChainedPointer(nullptr, next, prev) {
    }

    ChainedPointer::ChainedPointer() : ChainedPointer(nullptr, nullptr, nullptr) {
    }

    ChainedPointer::~ChainedPointer() {
        delete this->next;
    }

    void ChainedPointer::setPrev(ChainedPointer* prev) const {
        this->prev = prev;
    }
    void ChainedPointer::setNext(ChainedPointer* next) const {
        this->next = next;
    }

    const ChainedPointer* ChainedPointer::getPrev() const {
        return this->prev;
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

        this->begin->setNext(end); this->begin->setPrev(nullptr);
        this->end->setNext(nullptr); this->end->setPrev(begin);
    }

    RawMemory::~RawMemory() {
        delete this->begin;
    }

    int RawMemory::size() const noexcept {
        return this->elements;
    }

    const unsigned short int RawMemory::thisSize() const noexcept {
        short totalSize = sizeof(*this); const ChainedPointer* ptr = this->begin;
        for (int i = 0; i < this->size(); i++) {
            totalSize += sizeof(*ptr);
            ptr = ptr->next;
        }
        return totalSize;
    }
}
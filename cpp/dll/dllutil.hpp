#pragma once

#ifndef MOONA_DLLUTIL
    #define MOONA_DLLUTIL

    #include <windows.h>

    #define DLL HANDLE

    #define DLLExport __declspec(dllexport)
    #define DLLInport __declspec(dllinport)

    #define DLLEntryPoint int WINAPI DllEntryPoint

    #define WinMain int WINAPI WinMain

#endif